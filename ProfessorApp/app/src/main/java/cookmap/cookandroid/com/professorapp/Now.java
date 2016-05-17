package cookmap.cookandroid.com.professorapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

/**
 * Created by Administrator on 2016-01-29.
 */
public class Now extends Activity{
    static  String select;
    private Button[] bt;
    private Button tempBt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.now);
        bt = new Button[10];

        bt[0] = (Button)findViewById(R.id.deskBt1);
        bt[1] = (Button)findViewById(R.id.deskBt2);
        bt[2] = (Button)findViewById(R.id.deskBt3);
        bt[3] = (Button)findViewById(R.id.deskBt4);
        bt[4] = (Button)findViewById(R.id.deskBt5);
        bt[5] = (Button)findViewById(R.id.deskBt6);
        bt[6] = (Button)findViewById(R.id.deskBt7);
        bt[7] = (Button)findViewById(R.id.deskBt8);
        bt[8] = (Button)findViewById(R.id.deskBt9);
        bt[9] = (Button)findViewById(R.id.deskBt10);

        for (int i = 0 ; i <10 ; i++){
            bt[i].setBackgroundResource(R.drawable.blacktable);
            bt[i].setEnabled(false);
        }
        DeskState deskState = new DeskState(bt);
        deskState.execute();

        bt[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select = "1";
                Intent intent = new Intent(getApplicationContext(), StudentInfo.class);
                startActivity(intent);
            }
        });
        bt[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select = "2";
                Intent intent = new Intent(getApplicationContext(), StudentInfo.class);
                startActivity(intent);
            }
        });
        bt[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select = "3";
                Intent intent = new Intent(getApplicationContext(), StudentInfo.class);
                startActivity(intent);
            }
        });
        bt[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select = "4";
                Intent intent = new Intent(getApplicationContext(), StudentInfo.class);
                startActivity(intent);
            }
        });
        bt[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select = "5";
                Intent intent = new Intent(getApplicationContext(), StudentInfo.class);
                startActivity(intent);
            }
        });
        bt[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select = "6";
                Intent intent = new Intent(getApplicationContext(), StudentInfo.class);
                startActivity(intent);
            }
        });
        bt[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select = "7";
                Intent intent = new Intent(getApplicationContext(), StudentInfo.class);
                startActivity(intent);
            }
        });
        bt[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select = "8";
                Intent intent = new Intent(getApplicationContext(), StudentInfo.class);
                startActivity(intent);
            }
        });
        bt[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select = "9";
                Intent intent = new Intent(getApplicationContext(), StudentInfo.class);
                startActivity(intent);
            }
        });
        bt[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select = "10";
                Intent intent = new Intent(getApplicationContext(), StudentInfo.class);
                startActivity(intent);
            }
        });
    }
}