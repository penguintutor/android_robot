package com.penguintutor.androidpirobotcontrol;

import com.penguintutor.androidpirobotcontrol.util.SystemUiHider;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class MainControl extends Activity {

    private ControlRobot robot;
    TextView statusText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_control);

        final View controlsView = findViewById(R.id.fullscreen_content_controls);
        final View contentView = findViewById(R.id.fullscreen_content);


        robot = new ControlRobot(this);

        statusText = (TextView)findViewById(R.id.statusText);
        statusText.setText("Waiting for connection ...");


        /**
         * Set button handlers - uses OnTouchListener so that up and down can be registered
         */
        findViewById(R.id.directionButton_1_1).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP ||
                        event.getAction() == MotionEvent.ACTION_CANCEL) {
                    String result = robot.moveDirection("STOP");
                    //statusText.setText("Motor stop " + result);
                } else {
                    String result = robot.moveDirection("1_1");
                    //statusText.setText("Forward left " + result);
                }
                return true;
            }
        });
        findViewById(R.id.directionButton_2_1).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP ||
                        event.getAction() == MotionEvent.ACTION_CANCEL) {
                    String result = robot.moveDirection("STOP");
                    //statusText.setText("Motor stop " + result);
                } else {
                    String result = robot.moveDirection("2_1");
                    //statusText.setText("Forward " + result);
                }
                return true;
            }
        });
        findViewById(R.id.directionButton_3_1).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP ||
                        event.getAction() == MotionEvent.ACTION_CANCEL) {
                    String result = robot.moveDirection("STOP");
                    //statusText.setText("Motor stop " + result);
                } else {
                    String result = robot.moveDirection("3_1");
                    //statusText.setText("Forward right " + result);
                }
                return true;
            }
        });
        findViewById(R.id.directionButton_1_2).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP ||
                        event.getAction() == MotionEvent.ACTION_CANCEL) {
                    robot.moveDirection("STOP");
                    statusText.setText("Motor stop");
                } else {
                    robot.moveDirection("1_2");
                    statusText.setText("Motor stop" );
                }
                return true;
            }
        });
        findViewById(R.id.directionButton_2_2).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP ||
                        event.getAction() == MotionEvent.ACTION_CANCEL) {
                    robot.moveDirection("STOP");
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    robot.moveDirection("2_2");
                }
                return true;
            }
        });
        findViewById(R.id.directionButton_3_2).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP ||
                        event.getAction() == MotionEvent.ACTION_CANCEL) {
                    robot.moveDirection("STOP");
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    robot.moveDirection("3_2");
                }
                return true;
            }
        });
        findViewById(R.id.directionButton_1_3).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP ||
                        event.getAction() == MotionEvent.ACTION_CANCEL) {
                    robot.moveDirection("STOP");
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    robot.moveDirection("1_3");
                }
                return true;
            }
        });
        findViewById(R.id.directionButton_2_3).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP ||
                        event.getAction() == MotionEvent.ACTION_CANCEL) {
                    robot.moveDirection("STOP");
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    robot.moveDirection("2_3");
                }
                return true;
            }
        });
        findViewById(R.id.directionButton_3_3).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP ||
                        event.getAction() == MotionEvent.ACTION_CANCEL) {
                    robot.moveDirection("STOP");
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    robot.moveDirection("3_3");
                }
                return true;
            }
        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }


}
