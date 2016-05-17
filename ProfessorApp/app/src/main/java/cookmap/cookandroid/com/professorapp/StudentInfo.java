package cookmap.cookandroid.com.professorapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 2016-01-31.
 */
public class StudentInfo extends Activity{
    private ImageView imageView;
    private TextView stid, stname;
    String da;
    String reStid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studentinfo);

        imageView = (ImageView)findViewById(R.id.image);
        stid = (TextView)findViewById(R.id.stid);
        stname = (TextView)findViewById(R.id.stname);


        if (Now.select.equals("1")){
            DeskState deskState = new DeskState("1", stid, stname, imageView);
            deskState.execute();
        } else if (Now.select.equals("2")){
            DeskState deskState = new DeskState("2", stid, stname, imageView);
            deskState.execute();
        } else if (Now.select.equals("3")){
            DeskState deskState = new DeskState("3", stid, stname, imageView);
            deskState.execute();
        } else if (Now.select.equals("4")){
            DeskState deskState = new DeskState("4", stid, stname, imageView);
            deskState.execute();
        } else if (Now.select.equals("5")){
            DeskState deskState = new DeskState("5", stid, stname, imageView);
            deskState.execute();
        } else if (Now.select.equals("6")){
            DeskState deskState = new DeskState("6", stid, stname, imageView);
            deskState.execute();
        } else if (Now.select.equals("7")){
            DeskState deskState = new DeskState("7", stid, stname, imageView);
            deskState.execute();
        } else if (Now.select.equals("8")){
            DeskState deskState = new DeskState("8", stid, stname, imageView);
            deskState.execute();
        } else if (Now.select.equals("9")){
            DeskState deskState = new DeskState("9", stid, stname, imageView);
            deskState.execute();
        } else if (Now.select.equals("10")){
            DeskState deskState = new DeskState("10", stid, stname, imageView);
            deskState.execute();
        }

    }
}
