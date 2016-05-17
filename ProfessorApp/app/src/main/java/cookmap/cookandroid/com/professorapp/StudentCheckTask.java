package cookmap.cookandroid.com.professorapp;

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
 * Created by Samsung on 2016-02-09.
 */
public class StudentCheckTask extends AsyncTask<Void,Void,ArrayList<String>> {

    String subjectName;
    ArrayList<String> result = new ArrayList<>();
    //    String [] result;
    StudentCheckTask(String s){
        subjectName = s;
    }
    @Override
    protected ArrayList<String> doInBackground(Void... params) {
        try {
            Socket sendSocket = new Socket("52.69.56.103", 8888);
            DataOutputStream oo = new DataOutputStream(sendSocket.getOutputStream());
            ObjectInputStream ois= new ObjectInputStream(sendSocket.getInputStream());
            oo.writeUTF("checked student");
            oo.writeUTF(subjectName);
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
