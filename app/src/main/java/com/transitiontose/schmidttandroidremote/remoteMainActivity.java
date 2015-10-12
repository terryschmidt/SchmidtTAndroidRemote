package com.transitiontose.schmidttandroidremote;

import android.app.Activity;
import android.view.*;
import android.view.View.*;
import android.os.Bundle;
import android.widget.*;

public class remoteMainActivity extends Activity {

    private int currentChannelNum = 1;
    private String channelUserIsEntering = "";

    private ButtonClickListener btnClick;
    private TextView tvPowerText;
    private TextView speakerVolumeText;
    private TextView currentChanText;
    private Switch powerSwitch;
    private SeekBar seekBar;

    // numeric buttons
    private Button zeroButton; private Button oneButton; private Button twoButton;
    private Button threeButton; private Button fourButton; private Button fiveButton;
    private Button sixButton; private Button sevenButton; private Button eightButton; private Button nineButton;

    // Channel up and channel down buttons
    private Button plusButton; private Button minusButton;

    // Favorite channel buttons
    private Button ABCButton; private Button CBSButton; private Button NBCButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_main);

        tvPowerText = (TextView) findViewById(R.id.tvPowerText);
        speakerVolumeText = (TextView) findViewById(R.id.speakerVolumeText);
        currentChanText = (TextView) findViewById(R.id.currentChanText);
        btnClick = new ButtonClickListener();
        zeroButton = (Button) findViewById(R.id.zeroButton);
        oneButton = (Button) findViewById(R.id.oneButton);
        twoButton = (Button) findViewById(R.id.twoButton);
        threeButton = (Button) findViewById(R.id.threeButton);
        fourButton = (Button) findViewById(R.id.fourButton);
        fiveButton = (Button) findViewById(R.id.fiveButton);
        sixButton = (Button) findViewById(R.id.sixButton);
        sevenButton = (Button) findViewById(R.id.sevenButton);
        eightButton = (Button) findViewById(R.id.eightButton);
        nineButton = (Button) findViewById(R.id.nineButton);
        ABCButton = (Button) findViewById(R.id.ABCButton);
        CBSButton = (Button) findViewById(R.id.CBSButton);
        NBCButton = (Button) findViewById(R.id.NBCButton);
        plusButton = (Button) findViewById(R.id.plusButton);
        minusButton = (Button) findViewById(R.id.minusButton);
        powerSwitch = (Switch) findViewById(R.id.powerSwitch);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        powerSwitch.setChecked(true);

        powerSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    zeroButton.setClickable(true); oneButton.setClickable(true); twoButton.setClickable(true);
                    threeButton.setClickable(true); fourButton.setClickable(true); fiveButton.setClickable(true);
                    sixButton.setClickable(true); sevenButton.setClickable(true); eightButton.setClickable(true);
                    nineButton.setClickable(true); ABCButton.setClickable(true); CBSButton.setClickable(true);
                    NBCButton.setClickable(true); plusButton.setClickable(true); minusButton.setClickable(true);
                    seekBar.setEnabled(true); tvPowerText.setText("On");
                } else {
                    zeroButton.setClickable(false); oneButton.setClickable(false); twoButton.setClickable(false);
                    threeButton.setClickable(false); fourButton.setClickable(false); fiveButton.setClickable(false);
                    sixButton.setClickable(false); sevenButton.setClickable(false); eightButton.setClickable(false);
                    nineButton.setClickable(false); ABCButton.setClickable(false); CBSButton.setClickable(false);
                    NBCButton.setClickable(false); plusButton.setClickable(false); minusButton.setClickable(false);
                    seekBar.setEnabled(false); tvPowerText.setText("Off");
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // TODO Auto-generated method stub
                speakerVolumeText.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
        });

        int[] idList = {
                R.id.ABCButton, R.id.CBSButton, R.id.NBCButton,
                R.id.plusButton, R.id.minusButton, R.id.zeroButton,
                R.id.oneButton, R.id.twoButton, R.id.threeButton,
                R.id.fourButton, R.id.fiveButton, R.id.sixButton,
                R.id.sevenButton, R.id.eightButton, R.id.nineButton
        };

        for (int id : idList) {
            View v = (View) findViewById(id);
            v.setOnClickListener(btnClick);
        }
    }

    private class ButtonClickListener implements OnClickListener {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.plusButton: plusPressed(); break;
                case R.id.minusButton: minusPressed(); break;
                case R.id.ABCButton: currentChanText.setText("007"); currentChannelNum = 7; break;
                case R.id.CBSButton: currentChanText.setText("002"); currentChannelNum = 2; break;
                case R.id.NBCButton: currentChanText.setText("005"); currentChannelNum = 5; break;
                default: numPressed(v); break;  // default case is when user pressed a numeric button
            }
        }
    }

    void minusPressed() {
        if (currentChannelNum >= 2) {
            currentChannelNum--;
            String tmp = Integer.toString(currentChannelNum);
            if (tmp.length() == 1) {
                currentChanText.setText("00" + Integer.toString(currentChannelNum));
            }

            if (tmp.length() == 2) {
                currentChanText.setText("0" + Integer.toString(currentChannelNum));
            }

            if (tmp.length() == 3) {
                currentChanText.setText(Integer.toString(currentChannelNum));
            }
        }
    }

    void plusPressed() {
        if (currentChannelNum <= 998) {
            currentChannelNum++;
            String tmp = Integer.toString(currentChannelNum);
            if (tmp.length() == 1) {
                currentChanText.setText("00" + Integer.toString(currentChannelNum));
            }

            if (tmp.length() == 2) {
                currentChanText.setText("0" + Integer.toString(currentChannelNum));
            }

            if (tmp.length() == 3) {
                currentChanText.setText(Integer.toString(currentChannelNum));
            }
        }
    }

    void numPressed(View v) {
        String numPressed = ((Button) v).getText().toString();
        channelUserIsEntering += numPressed;

        if (channelUserIsEntering.length() == 3) {
            if (Integer.parseInt(channelUserIsEntering) <= 999 && Integer.parseInt(channelUserIsEntering) >= 1) {
                currentChanText.setText(channelUserIsEntering);
                currentChannelNum = Integer.parseInt(currentChanText.getText().toString());
                channelUserIsEntering = "";
            } else {
                channelUserIsEntering = "";
            }
        }
    }
}
