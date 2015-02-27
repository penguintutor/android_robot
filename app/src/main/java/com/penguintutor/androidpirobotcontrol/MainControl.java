package com.penguintutor.androidpirobotcontrol;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;


/**
 * Android app to control Raspberry Pi robot
 * http://www.penguintutor.com
 */
public class MainControl extends Activity {

    private ControlRobot robot;
    TextView statusText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_control);

        //final View controlsView = findViewById(R.id.fullscreen_content_controls);
        //final View contentView = findViewById(R.id.fullscreen_content);


        robot = new ControlRobot(this);

        statusText = (TextView)findViewById(R.id.statusText);
        statusText.setText("Initialization ...");


        /**
         * Set button handlers - uses OnTouchListener so that up and down can be registered
         * whereas other buttons can instead be registered using the onclick handlers
         */
        findViewById(R.id.directionButton_1_1).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP ||
                        event.getAction() == MotionEvent.ACTION_CANCEL) {
                    robot.moveDirection("STOP");
                } else {
                    robot.moveDirection("1_1");
                }
                return true;
            }
        });
        findViewById(R.id.directionButton_2_1).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP ||
                        event.getAction() == MotionEvent.ACTION_CANCEL) {
                    robot.moveDirection("STOP");
                } else {
                    robot.moveDirection("2_1");
                }
                return true;
            }
        });
        findViewById(R.id.directionButton_3_1).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP ||
                        event.getAction() == MotionEvent.ACTION_CANCEL) {
                    robot.moveDirection("STOP");
                } else {
                    robot.moveDirection("3_1");
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
                } else {
                    robot.moveDirection("1_2");
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
                } else {
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
                } else {
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
                } else {
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
                } else {
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
                } else {
                    robot.moveDirection("3_3");
                }
                return true;
            }
        });


        //
        statusText.setText("Connecting to robot ...");
        statusText.setText(robot.getStatus());


    }


    public void buttonDecreaseClick (View view) {
        robot.speedChange(-1);
    }

    public void buttonIncreaseClick (View view) {
        robot.speedChange(1);
    }




    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        //        WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_control);
    }



}
