package cookmap.cookandroid.com.professorapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by Administrator on 2016-01-29.
 */
public class Choice extends Activity{
    private Button nowbt, modifybt, logoutbt;
    Context context = this;
    String proid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choice);

        nowbt = (Button)findViewById(R.id.nowbt);
        modifybt = (Button)findViewById(R.id.modifybt);
        logoutbt = (Button)findViewById(R.id.Logout);
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(context.openFileInput("ProfessorId.txt")));
            proid = br.readLine();
            br.close();
        }catch (IOException e){}

        nowbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Now.class);
                startActivity(intent);
            }
        });
        modifybt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),InquirActivity.class);
                startActivity(intent);
            }
        });
        logoutbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    OutputStreamWriter osw = new OutputStreamWriter(context.openFileOutput("ProfessorId.txt", Context.MODE_PRIVATE));
                    osw.write("");
                    osw.close();
                } catch (IOException e) {}
                LogoutTask logoutTask = new LogoutTask(context, proid);
                logoutTask.execute();
            }
        });
    }
}
