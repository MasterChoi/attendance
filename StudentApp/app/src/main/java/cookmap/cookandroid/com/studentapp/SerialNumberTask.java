package cookmap.cookandroid.com.studentapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
/**
 * Created by Administrator on 2016-02-04.
 */
public class SerialNumberTask extends AsyncTask<Void,Void,Void> {
    String serialNumber;
    Context context;
    String re;
    SerialNumberTask(Context c, String s){
        context=c;
        serialNumber = s;
    }
    protected Void doInBackground(Void... params){
        try {
            Socket socket=new Socket("52.69.56.103",7777);
            DataOutputStream oo = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());
            oo.writeUTF("ScanSerialNumber");
            oo.writeUTF("student");
            oo.writeUTF(serialNumber);
            re = in.readUTF();
            oo.close();
            in.close();
            socket.close();

        } catch (Exception e){}

        return null;
    }

    protected void  onPostExecute(Void result) {
        super.onPostExecute(result);
        if(re.equals("SerialNumber Nonexist")) {
            Intent intent = new Intent(context, LoginActivity.class);
            context.startActivity(intent);
        }else{
            //IntroActivity.studentId = re;
            try{
                OutputStreamWriter osw = new OutputStreamWriter(context.openFileOutput("studentId.txt", Context.MODE_PRIVATE));
                osw.write(re);
                osw.close();
//                Toast.makeText(context,"file write : "+re,Toast.LENGTH_SHORT).show();
            }catch (IOException e){}
            Intent intent = new Intent(context, NfcTagging.class);
            context.startActivity(intent);

        }
}
}
