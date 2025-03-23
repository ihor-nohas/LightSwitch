package com.example.ihor.lightswitch;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

class HttpRequestAsyncTask extends AsyncTask<Void, Void, Void> {

    private String requestReply,ipAddress, portNumber;
    private Context context;
    private AlertDialog alertDialog;
    private String parameter;
    private String parameterValue;

    public HttpRequestAsyncTask(Context context, String parameterValue, String ipAddress, String portNumber, String parameter)
    {
        this.context = context;

        alertDialog = new AlertDialog.Builder(this.context)
                .setTitle("HTTP Response From IP Address:")
                .setCancelable(true)
                .create();

        this.ipAddress = ipAddress;
        this.parameterValue = parameterValue;
        this.portNumber = portNumber;
        this.parameter = parameter;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        alertDialog.setMessage("Data sent, waiting for reply from server...");
        if(!alertDialog.isShowing())
        {
            alertDialog.show();
        }
        requestReply = MainActivity.sendRequest(parameterValue,ipAddress,portNumber, parameter);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        alertDialog.setMessage(requestReply);
        if(!alertDialog.isShowing())
        {
            alertDialog.show();
        }
    }

    @Override
    protected void onPreExecute() {
        alertDialog.setMessage("Sending data to server, please wait...");
        if(!alertDialog.isShowing())
        {
            alertDialog.show();
        }
    }
}
