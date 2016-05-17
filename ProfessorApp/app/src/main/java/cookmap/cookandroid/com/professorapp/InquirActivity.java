package cookmap.cookandroid.com.professorapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Samsung on 2016-02-06.
 */
public class InquirActivity extends Activity {
    Button subjectbt,dataOrStudentBt,inquiryBt;
    int checkedItem=0;
    ArrayList<String> subjectList, dateOrStudentList, dateList;
    String subjectName,dateOrStudentId, choice;
    RadioGroup radioGroup;
    RadioButton rbDate, rbStudent;
    String proId;
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inquiry);
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(context.openFileInput("ProfessorId.txt")));
            proId = br.readLine();
            br.close();
        }catch (IOException e){}
        try {
            SubjectCheckTask subjectCheckTask = new SubjectCheckTask(proId);
            subjectList = subjectCheckTask.execute().get();
//            subjectItems = subjectList.toArray(new String[subjectList.size()]);
        }catch (Exception e){
            e.printStackTrace();
        }

        subjectbt = (Button)findViewById(R.id.selectbt);
        subjectbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSingleChoiceDialog(subjectList, subjectbt,1);
            }
        });
        dataOrStudentBt = (Button)findViewById(R.id.dateOrStudentBt);
        dataOrStudentBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    showSingleChoiceDialog(dateOrStudentList, dataOrStudentBt,2);
                }catch (NullPointerException e){
                    Toast toast = Toast.makeText(InquirActivity.this, "Select Radio Button", Toast.LENGTH_SHORT );
                    toast.show();
                }
            }
        });
        inquiryBt = (Button)findViewById(R.id.inquiryBt);
        inquiryBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ModifyActivity.class);
                InquiryData inquiryData = new InquiryData(subjectName, dateOrStudentId, choice);
                intent.putExtra("result",inquiryData);
                startActivity(intent);
            }
        });

        radioGroup=(RadioGroup)findViewById(R.id.radioGroup);
        rbDate=(RadioButton)findViewById(R.id.rbDate);
        rbStudent=(RadioButton)findViewById(R.id.rbStudent);


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                String subjectName = subjectList.get(checkedItem);
                switch (checkedId) {
                    case R.id.rbDate:
                        try {
                            choice = "date";
                            DateCheckTask dateCheckTask = new DateCheckTask(subjectName);
                            dateOrStudentList = dateCheckTask.execute().get();
                            dataOrStudentBt.setText("날짜를 선택하세요~");
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        break;
                    case R.id.rbStudent:
                        try {
                            choice = "student";
                            StudentCheckTask showStudent = new StudentCheckTask(subjectName);
                            dateOrStudentList = showStudent.execute().get();
                            dataOrStudentBt.setText("학번를 선택하세요~");
//                            studentItems= studentList.toArray(new String[studentList.size()]);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        break;
                }
            }
        });
    }
    public Void showSingleChoiceDialog(ArrayList<String> s, final Button button, final int a) {

        final int temp = checkedItem;
        final String[] items = s.toArray(new String[s.size()]);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Select Subject");
        builder.setCancelable(false);

        builder.setSingleChoiceItems(items, checkedItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                checkedItem = which;
            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                button.setText(items[checkedItem]);
                if(a == 1){
                    subjectName = items[checkedItem];
//                    Log.i("dd",dateOrStudentId);
                }else if(a==2) {
                    dateOrStudentId = items[checkedItem];
//                    Log.i("dd",dateOrStudentId);
                }
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                checkedItem = temp;
            }
        });
        builder.create();
        builder.show();
        return null;
    }
}
