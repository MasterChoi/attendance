package cookmap.cookandroid.com.studentapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Created by Administrator on 2016-02-05.
 */
public class LoginTask extends AsyncTask<Void,Void,Void> {
    Context context;
    String id,pass;
    String re;
    String seri;
    LoginTask(Context c,String i, String p, String s){
        context = c;
        id = i;
        pass = p;
        seri = s;
    }

    protected Void doInBackground(Void... params){
        try {
            Socket sendSocket=new Socket("52.69.56.103",7777);
            DataOutputStream oo = new DataOutputStream(sendSocket.getOutputStream());
            DataInputStream in = new DataInputStream(sendSocket.getInputStream());

            oo.writeUTF("Login");
            oo.writeUTF(id);
            oo.writeUTF(pass);
            oo.writeUTF(seri);
            re = in.readUTF();

            oo.close();
            in.close();
            sendSocket.close();

        } catch (Exception e){}

        return null;
    }

    protected void  onPostExecute(Void result) {
        super.onPostExecute(result);
        if (re.equals("LoginSuccess")){
            //IntroActivity.studentId = id;
            try{
                OutputStreamWriter osw = new OutputStreamWriter(context.openFileOutput("studentId.txt", Context.MODE_PRIVATE));
                osw.write(id);
                osw.close();
            }catch (IOException e){}
            Intent intent = new Intent(context, NfcTagging.class);
            context.startActivity(intent);
        } else if (re.equals("LoginFail")){
            Toast.makeText(context,"Login Fail\n try again",Toast.LENGTH_SHORT).show();
        }

    }
}
