package com.penguintutor.androidpirobotcontrol;


import android.content.Context;


/**
 * Control Robot vehicle
 * see http://www.penguintutor.com
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
    @SuppressWarnings("unused")
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

    public String getStatus() {
        sendGetCmd(robotIp, port, "/status");
        return "Checking robot status";
    }



    // http get using HttpGet
    private void sendGetCmd(String host, int port, String path) {
        new HttpSendMsg().send("http://" + host + ":" + port + path);
    }

    class HttpSendMsg implements RestTask.ProgressCallback, RestTask.ResponseCallback {

        //private ProgressDialog mProgress;


        private void send (String url) {

            //Create the requests
            try{
                //Simple GET
                RestTask getTask = RestUtil.obtainGetTask(url);
                getTask.setResponseCallback(this);
                getTask.setProgressCallback(this);

                getTask.execute();

                //Display progress to the user
            } catch (Exception e) {
                ((MainControl) contextGUI).statusText.setText(e.getMessage());
            }

        }

        @Override
        public void onProgressUpdate(int progress) {
        }

        @Override
        public void onRequestSuccess(String response) {
            ((MainControl) contextGUI).statusText.setText("success:" + response);
        }

        @Override
        public void onRequestError(Exception error) {
            ((MainControl) contextGUI).statusText.setText("error: "+error);
        }
    }
}

