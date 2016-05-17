package cookmap.cookandroid.com.professorapp;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016-02-13.
 */
public class ModifyTask extends AsyncTask<Void,Void,Void> {

        String studentId ;
        String date;
        String hour ;
        String subjectName;
        String state;
        Context context;


        ModifyTask(String a, String b, String c, String d, String e, Context f){
            studentId = a;
            date = b;
            hour =c;
            subjectName = d;
            state = e;
            context = f;
        }
@Override
protected Void doInBackground(Void... params) {
        try {
            Socket sendSocket = new Socket("52.69.56.103", 8888);

            DataOutputStream oo = new DataOutputStream(sendSocket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(sendSocket.getInputStream());
            oo.writeUTF("State modify");
            oo.writeUTF(studentId);
            oo.writeUTF(date);
            oo.writeUTF(hour);
            oo.writeUTF(subjectName);
            oo.writeUTF(state);
//        Log.i("log", result.get(0));
//        Log.i("log", result.get(1).toString());
        oo.close();
            ois.close();
        sendSocket.close();
            Log.i("tag", "ModifyTask Task ois close");

        } catch (UnknownHostException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }

        return null;
        }

protected void onPostExecute(Void result) {
    super.onPostExecute(result);
        ((ModifyActivity)context).recreate();
    }
}
