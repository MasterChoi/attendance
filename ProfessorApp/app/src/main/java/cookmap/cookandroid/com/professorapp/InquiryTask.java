package cookmap.cookandroid.com.professorapp;

import android.os.AsyncTask;
import android.util.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016-02-13.
 */
public class InquiryTask  extends AsyncTask<Void,Void, ArrayList<AttendanceData>> {

    String subjectId, dataOrStudentID, choice;
    ArrayList<ArrayList<String>> result = new ArrayList<>();
    ArrayList<AttendanceData> attendanceData = new ArrayList<>();
    InquiryTask(String s, String d, String c){
        subjectId = s;
        dataOrStudentID = d;
        choice = c;
    }
    @Override
    protected ArrayList<AttendanceData> doInBackground(Void... params) {
        try {
            Socket sendSocket = new Socket("52.69.56.103", 8888);
            Log.i("tag","asdfads");
            DataOutputStream oo = new DataOutputStream(sendSocket.getOutputStream());
            Log.i("tag","asdfads");
            ObjectInputStream ois= new ObjectInputStream(sendSocket.getInputStream());

            Log.i("tag","asdfads");
            if(choice.equals("student")) {
                oo.writeUTF("Inquiry By StudentID");
            }else if(choice.equals("date")){
                oo.writeUTF("Inquiry By Date");
            }
            Log.i("tag","asdfads");
            oo.writeUTF(subjectId);
            oo.writeUTF(dataOrStudentID);
            result= (ArrayList<ArrayList<String>>)ois.readObject();


            for(int i=0; i<result.size(); i++) {
                attendanceData.add(new AttendanceData(result.get(i).get(0),
                        result.get(i).get(1),result.get(i).get(2),result.get(i).get(3),result.get(i).get(4)));
            }
//            int i=0;
//            attendanceData.add(new AttendanceData(result.get(i).get(0),
//                    result.get(i).get(1),result.get(i).get(2),result.get(i).get(3)));

            ois.close();
            Log.i("tag","Inquiry Task ois close");
            oo.close();
            sendSocket.close();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }


        return attendanceData;
    }

    protected void onPostExecute( ArrayList<AttendanceData> result) {
        super.onPostExecute(result);
    }
}