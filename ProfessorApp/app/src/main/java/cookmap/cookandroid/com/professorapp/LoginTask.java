package cookmap.cookandroid.com.professorapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Created by Administrator on 2016-02-05.
 */
public class LoginTask extends AsyncTask<Void,Void,Void> {
    Context context;
    String id,pass;
    String serial;
    String re;
    LoginTask(Context c, String i, String p, String s){
        context = c;
        id = i;
        pass = p;
        serial = s;
    }

    protected Void doInBackground(Void... params){
        try {
            Socket sendSocket=new Socket("52.69.56.103",8888);
            DataOutputStream oo = new DataOutputStream(sendSocket.getOutputStream());
            ObjectInputStream ois= new ObjectInputStream(sendSocket.getInputStream());

            oo.writeUTF("Login");
            oo.writeUTF(id);
            oo.writeUTF(pass);
            oo.writeUTF(serial);
            re = (String)ois.readObject();

            oo.close();
            ois.close();
            sendSocket.close();

        } catch (Exception e){}

        return null;
    }

    protected void  onPostExecute(Void result) {
        super.onPostExecute(result);
        if (re.equals("LoginSuccess")){
            //IntroActivity.studentId = id;
            try{
                OutputStreamWriter osw = new OutputStreamWriter(context.openFileOutput("ProfessorId.txt", Context.MODE_PRIVATE));
                osw.write(id);
                osw.close();
            }catch (IOException e){}
            Intent intent = new Intent(context, Choice.class);
            context.startActivity(intent);
        } else if (re.equals("LoginFail")){
            Toast.makeText(context,"Login Fail\n try again",Toast.LENGTH_SHORT).show();
        }
    }
}
