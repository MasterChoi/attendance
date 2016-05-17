package cookmap.cookandroid.com.professorapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Samsung on 2016-02-10.
 */
public class ModifyActivity extends Activity {
    ArrayList<AttendanceData> attendanceList;
    ListView listView;
    CustomAdapter customAdapter;
    String subjectName, dateOrStudentId, choice;
//    static Context context = ModifyActivity.context;
    int checkedItem =0;
    InquiryTask inquiryTask;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify);
        listView = (ListView)findViewById(R.id.list_item);
        Intent intent = getIntent();
        InquiryData inquiryData = (InquiryData)intent.getSerializableExtra("result");
        subjectName = inquiryData.getSubjectName();
        dateOrStudentId = inquiryData.getDateOrStudentId();
        choice = inquiryData.getChoice();
        try {
            inquiryTask = new InquiryTask(subjectName, dateOrStudentId, choice);
            Log.i("tag","asdfads");
            attendanceList = inquiryTask.execute().get();
            customAdapter = new CustomAdapter(this,R.layout.row, attendanceList);
            listView.setAdapter(customAdapter);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

//    protected void onResume() {
//        super.onResume();
//        setContentView(R.layout.modify);
//        listView = (ListView)findViewById(R.id.list_item);
//        try {
//            attendanceList = inquiryTask.execute().get();
//            customAdapter = new CustomAdapter(this,R.layout.row, attendanceList);
//            listView.setAdapter(customAdapter);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
}