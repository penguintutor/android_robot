package com.penguintutor.androidpirobotcontrol;


import android.os.AsyncTask;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by stewart on 20/02/15.
 */
public class ControlRobot extends AsyncTask<Void, Integer, Object> {


    private HttpURLConnection webConnection;

    public ControlRobot(HttpURLConnection connection) {
        webConnection = connection;
    }


    protected Long doInBackground(URL url) {
        long totalSize = 0;
        totalSize += Downloader.downloadFile(url);
        return totalSize;
    }

    protected void onProgressUpdate(Integer... progress) {
    }

    protected void onPostExecute(Long result) {
    }
}

}
