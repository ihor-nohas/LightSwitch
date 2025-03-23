package com.example.ihor.lightswitch;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ToggleButton;

public class Tab1Basic extends Fragment implements View.OnClickListener {

    String parameterValue;
    String ipAddress;
    String portNumber;

    private ToggleButton toggleButtonLed1, toggleButtonLed2;

    private EditText editIp, editPort;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1_basic, container, false);

        toggleButtonLed1 = (ToggleButton) rootView.findViewById(R.id.toggleButtonLed1);
        toggleButtonLed2 = (ToggleButton) rootView.findViewById(R.id.toggleButtonLed2);

        editIp = (EditText) rootView.findViewById(R.id.editIP);
        editPort = (EditText) rootView.findViewById(R.id.editPort);

        toggleButtonLed1.setOnClickListener(this);
        toggleButtonLed2.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View view) {
        parameterValue = "";
        ipAddress = editIp.getText().toString().trim();
        portNumber = editPort.getText().toString().trim();

        if(view.getId()==toggleButtonLed1.getId())
        {
            parameterValue = "5";
        }
        else if(view.getId()==toggleButtonLed2.getId())
        {
            parameterValue = "6";
        }
        if(ipAddress.length()>0 && portNumber.length()>0) {
            new HttpRequestAsyncTask(
                    view.getContext(), parameterValue, ipAddress, portNumber, "pin"
            ).execute();
        }
    }
}
