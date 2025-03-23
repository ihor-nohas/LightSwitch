package com.example.ihor.lightswitch;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Switch;

public class Tab3Auto extends Fragment implements View.OnClickListener {

    private CheckBox checkBoxLamp1, checkBoxLamp2;
    private Switch sensorMotion, sensorIllumination;

    String parameterValue;
    String ipAddress = "192.168.0.100";
    String portNumber = "80";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab3_auto, container, false);

        checkBoxLamp1 = (CheckBox) rootView.findViewById(R.id.checkBoxLamp1);
        checkBoxLamp2 = (CheckBox) rootView.findViewById(R.id.checkBoxLamp2);

        sensorMotion = (Switch) rootView.findViewById(R.id.sensorMotion);
        sensorIllumination = (Switch) rootView.findViewById(R.id.sensorIllumination);

        checkBoxLamp1.setOnClickListener(this);
        checkBoxLamp2.setOnClickListener(this);

        sensorMotion.setOnClickListener(this);
        sensorIllumination.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == sensorMotion.getId()) {
            if (sensorMotion.isChecked()) {
                parameterValue = "/smot=1/";
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
            } else parameterValue = "/smot=0/";

        } else if (view.getId() == sensorIllumination.getId()) {
            if (sensorIllumination.isChecked()) {
                parameterValue = "/ils=1/";
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
            } else parameterValue = "/ils=0/";

        }

        if (view.getId() == sensorMotion.getId() || view.getId() == sensorIllumination.getId()) {
            if (ipAddress.length() > 0 && portNumber.length() > 0) {
                new HttpRequestAsyncTask(
                        view.getContext(), parameterValue, ipAddress, portNumber, "pin"
                ).execute();
            }
        }
    }
}
