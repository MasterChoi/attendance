package cookmap.cookandroid.com.studentapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 * Created by Administrator on 2016-02-04.
 */
public class TaggingTask extends AsyncTask<Void,Void,Void> {
    String stTagId;
    String  studentId;
    String re;
    Context context;

    TaggingTask(String t, String aa, Context c){
        stTagId = t;
        studentId = aa;
        context= c;
    }

    protected Void doInBackground(Void... params){
        try {
            Socket sendSocket=new Socket("52.69.56.103",7777);
            DataOutputStream oo = new DataOutputStream(sendSocket.getOutputStream());
            DataInputStream in = new DataInputStream(sendSocket.getInputStream());

            oo.writeUTF("AttendanceInsert");
            oo.writeUTF(stTagId);
            oo.writeUTF(studentId);
            re = in.readUTF();
            Log.d("Result : ",re);
            oo.close();
            in.close();
            sendSocket.close();

        } catch (Exception e){}

        return null;
    }

    protected void  onPostExecute(Void result) {
        super.onPostExecute(result);
        if (re.equals("AttendanceInsert_success")){
            NfcTagging.mVibe.vibrate(1000);
        } else if(re.equals("AttendanceInsert_fail")){
            Toast.makeText(context,"Attendance_fail",Toast.LENGTH_SHORT).show();
        }
    }
}
