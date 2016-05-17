package cookmap.cookandroid.com.professorapp;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Samsung on 2016-02-06.
 */
public class SubjectCheckTask extends AsyncTask<Void,Void,ArrayList<String>> {

    String proId;
//    String [] result;
    ArrayList<String> result = new ArrayList<>();
    SubjectCheckTask(String p){
        proId= p;
    }
    @Override
    protected ArrayList<String> doInBackground(Void... params) {
        try {
            Socket sendSocket = new Socket("52.69.56.103", 8888);
            DataOutputStream oo = new DataOutputStream(sendSocket.getOutputStream());
            ObjectInputStream ois= new ObjectInputStream(sendSocket.getInputStream());
            oo.writeUTF("checked subject");
            oo.writeUTF(proId);
            result= (ArrayList<String>)ois.readObject();
            ois.close();
            oo.close();
            sendSocket.close();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }


        return result;
    }

    protected void onPostExecute(ArrayList<String> result) {
        super.onPostExecute(result);
    }
}