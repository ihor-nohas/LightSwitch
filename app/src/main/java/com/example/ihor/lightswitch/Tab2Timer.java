package com.example.ihor.lightswitch;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;

public class Tab2Timer extends Fragment implements View.OnClickListener {

    private EditText editTime;
    private Button startTimer, pauseTimer, stopTimer;
    private CheckBox checkBoxLamp1, checkBoxLamp2;

    int posAction, posLed;

    String parameterValue;
    String ipAddress = "192.168.0.100";
    String portNumber = "80";

    RadioGroup radioGroupActions;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2_timer, container, false);

        checkBoxLamp1 = (CheckBox) rootView.findViewById(R.id.checkBoxLamp1);
        checkBoxLamp2 = (CheckBox) rootView.findViewById(R.id.checkBoxLamp2);

        radioGroupActions = (RadioGroup) rootView.findViewById(R.id.radioGroupActions);

        checkBoxLamp1.setOnClickListener(this);
        checkBoxLamp2.setOnClickListener(this);

        radioGroupActions.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.radioButton: {
                        posAction = 0;
                        break;
                    }
                    case R.id.radioButton2: {
                        posAction = 1;
                        break;
                    }
                    case R.id.radioButton3: {
                        posAction = 2;
                        break;
                    }
                }
            }
        });

        startTimer = (Button) rootView.findViewById(R.id.startTimer);
        pauseTimer = (Button) rootView.findViewById(R.id.pauseTimer);
        stopTimer = (Button) rootView.findViewById(R.id.stopTimer);

        editTime = (EditText) rootView.findViewById(R.id.editTime);

        startTimer.setOnClickListener(this);
        pauseTimer.setOnClickListener(this);
        stopTimer.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == startTimer.getId()) {
            parameterValue = "/tim=st/";
            parameterValue += editTime.getText().toString().trim() + "/";
            //Select led
            if (checkBoxLamp1.isChecked() && checkBoxLamp2.isChecked()) {
                parameterValue += "DD/";
            }
            if (checkBoxLamp1.isChecked() && checkBoxLamp2.isChecked() == false) {
                parameterValue += "D5/";
            }
            if (checkBoxLamp2.isChecked() && checkBoxLamp1.isChecked() == false) {
                parameterValue += "D6/";
            }
            //Select action
            if (posAction == 0) parameterValue += "A";
            if (posAction == 1) parameterValue += "N";
            if (posAction == 2) parameterValue += "F";
        } else if (view.getId() == pauseTimer.getId()) {
            parameterValue = "/tim=ps";
        } else if (view.getId() == stopTimer.getId()) {
            parameterValue = "/tim=sp";
        }

        if (view.getId() == startTimer.getId() || view.getId() == pauseTimer.getId() || view.getId() == stopTimer.getId()) {
            if (ipAddress.length() > 0 && portNumber.length() > 0) {
                new HttpRequestAsyncTask(
                        view.getContext(), parameterValue, ipAddress, portNumber, "pin"
                ).execute();
            }
        }
    }
}
