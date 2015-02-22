package com.penguintutor.androidpirobotcontrol;


import android.content.Context;
import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.net.HttpURLConnection;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by stewart on 20/02/15.
 */
public class ControlRobot {

    Context contextGUI;
    //private static HttpSendMsg msgSender;

    // Uses Context to allow this class to update values in GUI elements in MainControl
    public ControlRobot(Context callerclass) {
        contextGUI = callerclass;
        //msgSender = new HttpSendMsg();
        // temp hard coded
        //robotIp = R.string.ipaddress;
        robotIp = "10.5.5.1";
        //port = R.integer.port;
        port = 80;
    }

    String robotIp;
    int port;


    // Move robot in a direction - uses grid position 1_1 = fwd left, 1_2 = left, 3_3 = back right etc.
    // 2_2 = stop (or use "STOP" or separate stop command)
    public String moveDirection(String button) {
        // These are the motor directions 0 = stop, 1 = fwd, 2 = rev
        int m1 = 0;
        int m2 = 0;
        // set m1 and m2 based on button
        switch (button) {
            case "1_1":
                m1 = 1;
                m2 = 0;
                break;
            case "2_1":
                m1 = 1;
                m2 = 1;
                break;
            case "3_1":
                m1 = 0;
                m2 = 1;
                break;
            case "1_2":
                m1 = 1;
                m2 = 2;
                break;
            case "2_2":
                m1 = 0;
                m2 = 0;
                break;
            case "3_2":
                m1 = 2;
                m2 = 1;
                break;
            case "1_3":
                m1 = 2;
                m2 = 0;
                break;
            case "2_3":
                m1 = 2;
                m2 = 2;
                break;
            case "3_3":
                m1 = 0;
                m2 = 2;
                break;
            // default to initial state m1, m2 = 0
            // Allows to use word "STOP"
            default:
                break;
        }
        sendCmd("/control?cmd=motor&m1=" + m1 + "&m2=" + m2);
        return "OK";
    }


    // Able to run system commands
    public String cmd(String instruction) {
        switch (instruction) {
            case "status":
                sendCmd("status");
                return "Command Sent";
        }
        return "InvalidCommand";
    }


    // lower level command - where command needs to be formatted as url suffix string
    // This is public to allow a command to be formed outside of this class, but normally cmd or one of the more user friendly methods are used
    public void sendCmd(String command) {
        sendGetCmd(robotIp, port, command);
    }


    // http get using HttpGet
    private void sendGetCmd(String host, int port, String path) {
        try {
            new HttpSendMsg().execute(new URL("http://" + host + ":" + port + path));
        }
        catch (MalformedURLException e) {
            // need to handle this ?
        }
    }

    class HttpSendMsg extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {
            StringBuilder builder = new StringBuilder();
            HttpClient client = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(urls[0].toString());

            ((MainControl) contextGUI).statusText.setText("Sending: "+urls[0].toString());

            try {
                HttpResponse response = client.execute(httpGet);
                StatusLine statusLine = response.getStatusLine();
                int statusCode = statusLine.getStatusCode();
                if (statusCode == 200) {
                    HttpEntity entity = response.getEntity();
                    InputStream content = entity.getContent();
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(content));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        builder.append(line);
                    }
                    return "Success " + line;
                } else {
                    return "Error unable to connect";
                }
            } catch (ClientProtocolException e) {
                return "Error Client Protocol Exception";
            } catch (IOException e) {
                return "Error IO Exception";
            }
        }

        protected void onPostExecute(String result) {
            ((MainControl) contextGUI).statusText.setText(result); // changing TextView
        }
    }
}

