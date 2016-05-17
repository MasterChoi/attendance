package cookmap.cookandroid.com.professorapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * Created by EmbLab on 2016-02-07.
 */
public class LogoutTask extends AsyncTask<Void,Void,Void> {
    String ProId;
    String re;
    Context context;
    LogoutTask(Context c, String i){
        context = c;
        ProId = i;
    }
    protected Void doInBackground(Void... params){
        try {
            Socket sendSocket=new Socket("52.69.56.103",8888);
            DataOutputStream oo = new DataOutputStream(sendSocket.getOutputStream());
            ObjectInputStream ois= new ObjectInputStream(sendSocket.getInputStream());

            oo.writeUTF("Logout");
            oo.writeUTF(ProId);
            re = (String)ois.readObject();

            oo.close();
            ois.close();
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
