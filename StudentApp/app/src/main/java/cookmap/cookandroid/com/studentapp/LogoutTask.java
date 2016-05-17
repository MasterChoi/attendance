package cookmap.cookandroid.com.studentapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Created by EmbLab on 2016-02-07.
 */
public class LogoutTask extends AsyncTask<Void,Void,Void> {
    String studentId;
    String re;
    Context context;
    LogoutTask(Context c,String i){
        context = c;
        studentId = i;
    }
    protected Void doInBackground(Void... params){
        try {
            Socket sendSocket=new Socket("52.69.56.103",7777);
            DataOutputStream oo = new DataOutputStream(sendSocket.getOutputStream());
            DataInputStream in = new DataInputStream(sendSocket.getInputStream());

            oo.writeUTF("Logout");
            oo.writeUTF(studentId);
            re = in.readUTF();

            oo.close();
            in.close();
            sendSocket.close();

        } catch (Exception e){}

        return null;
    }

    protected void  onPostExecute(Void result) {
        super.onPostExecute(result);
        if (re.equals("Logout_success")){
            Intent intent = new Intent(context, LoginActivity.class);
            context.startActivity(intent);
        } else if (re.equals("Logout_fail")){
            Log.d("Logout Fail","Logout Fail");
            Toast.makeText(context, "Logout Fail\n try again", Toast.LENGTH_SHORT).show();
        }

    }
}
