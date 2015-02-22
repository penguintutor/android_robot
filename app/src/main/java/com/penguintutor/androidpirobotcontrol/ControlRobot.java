package com.penguintutor.androidpirobotcontrol;


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


/**
 * Created by stewart on 20/02/15.
 */
public class ControlRobot {

    public ControlRobot() {
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
            case "1_1" : m1 = 1; m2 = 0;
                break;
            case "2_1" : m1 = 1; m2 = 1;
                break;
            case "3_1" : m1 = 0; m2 = 1;
                break;
            case "1_2" : m1 = 1; m2 = 2;
                break;
            case "2_2" : m1 = 0; m2 = 0;
                break;
            case "3_2" : m1 = 2; m2 = 1;
                break;
            case "1_3" : m1 = 2; m2 = 0;
                break;
            case "2_3" : m1 = 2; m2 = 2;
                break;
            case "3_3" : m1 = 0; m2 = 2;
                break;
            // default to initial state m1, m2 = 0
            // Allows to use word "STOP"
            default :
                break;
        }
        //return sendCmd("/control?cmd=motor&m1=" + m1 + "&m2=" + m2);
        return "OK";
    }


    // Able to run system commands
    public String cmd(String instruction) {
        switch (instruction) {
            case "status" :
                return sendCmd ("status");
        }
        return "InvalidCommand";
    }


    // lower level command - where command needs to be formatted as url suffix string
    // This is public to allow a command to be formed outside of this class, but normally cmd or one of the more user friendly methods are used
    public String sendCmd(String command) {
        String result;
        try {
            result = sendGetCmd(robotIp, port, command);
        }
        catch (IOException e)
        {
            return "Error";
        }
        return result;
    }



    // http get using HttpGet
    private static String sendGetCmd(String host, int port, String path)
                                  throws IOException {
        HttpHost target = new HttpHost(host, port);
        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(path);
        HttpEntity results = null;
        try {
            HttpResponse response = client.execute(target, get);
            results = response.getEntity();
            return EntityUtils.toString(results);
        } catch (Exception e) {
            throw new RuntimeException("Web Service Failure");
        } finally {
            if (results != null)
                try {
                    results.consumeContent();
                } catch (IOException e) {
                    // empty, Checked exception but don't care
                }
        }
    }
}
