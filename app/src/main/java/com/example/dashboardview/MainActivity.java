package com.example.dashboardview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private DashBoardView mDashBoardView;
    private Button speed_up;
    private Button speed_down;
    private Button speed_set;
    private TextView speed_edit;
    private int b;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDashBoardView = (DashBoardView) findViewById(R.id.mDashBoardView);
        speed_up = (Button) findViewById(R.id.speed_up);
        speed_down = (Button) findViewById(R.id.speed_down);
        //获取输入值
        speed_edit = (TextView)findViewById(R.id.speed_edit);
        speed_set = (Button)findViewById(R.id.speed_set);
        setListener();


        speed_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public  void onClick(View v) {
             b =20;
              if (mDashBoardView.mVelocity<=b){
                  mDashBoardView.setType(1);
              }else {
                  mDashBoardView.setType(2);
              }
            }
        });
    }


    private void setListener() {
        //设置监听
        speed_up.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //按下的时候加速
                        mDashBoardView.setType(1);
                        break;
                    case MotionEvent.ACTION_UP:
                        //松开做自然减速
                        mDashBoardView.setType(2);
                        break;
                }
                return true;
            }
        });
        speed_down.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //按下的时候减速
                        mDashBoardView.setType(2);
                        break;
                    case MotionEvent.ACTION_UP:
                        //松开做自然减速
                        mDashBoardView.setType(0);
                        break;
                }
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mDashBoardView != null) {
            mDashBoardView.setVelocity(0);
            mDashBoardView.setStart(true);
        }
        new Thread(mDashBoardView).start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mDashBoardView != null) {
            mDashBoardView.setVelocity(0);
            mDashBoardView.setStart(false);
        }
    }

    }

