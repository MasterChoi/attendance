package cookmap.cookandroid.com.studentapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Calendar;

/**
 * Created by Administrator on 2016-01-28.
 */
public class SendTask extends AsyncTask<Void,Void,Void> {
    String tag=null;
    String a, b, c, d;
    String re,serialNumber;
    Context context;
    private boolean va = false;
    SendTask(String t, String aa, String bb, String cc, String dd){
        tag = t;
        a = aa;
        b = bb;
        c = cc;
        d = dd;
    }//생성자
    SendTask(Context c, String s){
        context=c;
        serialNumber = s;
    }
    @Override
    protected Void doInBackground(Void... params){
        try{
            Socket sendSocket=new Socket("52.69.56.103",7777);
            DataOutputStream oo = new DataOutputStream(sendSocket.getOutputStream());
            DataInputStream in = new DataInputStream(sendSocket.getInputStream());

            if(tag==null){
                oo.writeUTF("a");
                oo.writeUTF(serialNumber);
                re = in.readUTF();
            }

            else {
                oo.writeUTF("b");
                oo.writeUTF(tag);
                oo.writeUTF(a);
                oo.writeUTF(b);
                oo.writeUTF(c);
                oo.writeUTF(d);
                re = in.readUTF();
            }


                if (re.equals("a")) {
                    Log.d("DB select success", "DB select success");
                    va = true;
                } else if (re.equals("b"))
                    Log.d("DB select fail", "DB select fail");
                else if (re.equals("c"))
                    Log.d("MAC success", "MAC success");
                else
                    Log.d("DB fail", "DB fail");

                oo.close();
                in.close();
                sendSocket.close();


        }catch (UnknownHostException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
    protected void  onPostExecute(Void result) {
        super.onPostExecute(result);
        Intent intent = new Intent(context, NfcTagging.class);
        context.startActivity(intent);

    }

    public boolean vv(){
        return va;
    }
}

