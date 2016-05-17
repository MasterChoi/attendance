package cookmap.cookandroid.com.professorapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016-02-13.
 */
public class CustomAdapter extends BaseAdapter {
    Context context= null;
    int layout = 0;
    ArrayList<AttendanceData> list = null;
    LayoutInflater inflater = null;
    int checkedItem;

    ArrayList<String> studentId = new ArrayList<>();
    ArrayList<String> sSubject= new ArrayList<>();
    ArrayList<String> sDate= new ArrayList<>();
    ArrayList<String> sHour= new ArrayList<>();

    TextView id;
    TextView subject ;
    TextView date;
    TextView hour;
    TextView state;
    Button button;


    public CustomAdapter(Context context, int l,ArrayList<AttendanceData> list) {
        this.context = context;
        this.layout= l;
        this.list = list;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = inflater.inflate(this.layout, parent, false);
        }



        id = (TextView)convertView.findViewById(R.id.idText);
        subject = (TextView)convertView.findViewById(R.id.subjectText);
        date = (TextView)convertView.findViewById(R.id.dateText);
        hour = (TextView)convertView.findViewById(R.id.hourText);
        state = (TextView)convertView.findViewById(R.id.stateText);
        button = (Button)convertView.findViewById(R.id.bt);

        id.setText("학번 : " + list.get(position).getStudentId());
        subject.setText("과목 : "+list.get(position).getSubjectName());
        date.setText("날짜 : "+list.get(position).getDate());
        hour.setText("시간 :"+list.get(position).getHour());
        state.setText("출석 상태 : " +list.get(position).getState());

        studentId.add(list.get(position).getStudentId());
        sSubject.add(list.get(position).getSubjectName());
        sDate.add(list.get(position).getDate());
        sHour.add(list.get(position).getHour());

        if(list.get(position).getState().equals("Attendence")){
            checkedItem=0;
        }else if(list.get(position).getState().equals("Absence")){
            checkedItem=1;
        }else if(list.get(position).getState().equals("Lateness")){
            checkedItem=2;
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("LOG", Integer.toString(position));
                showSingleChoiceDialog(position);
            }
        });
//        button.setOnClickListener((View.OnClickListener)context);
        return convertView;
    }

    public Void showSingleChoiceDialog(final int position) {

        final int temp = checkedItem;
        final String[] items ={"Attendence", "Absence", "Lateness"} ;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle("Select");
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
                ModifyTask modifyTask = new ModifyTask(studentId.get(position), sDate.get(position),
                        sHour.get(position), sSubject.get(position), items[checkedItem], context);
                modifyTask.execute();
//                state.setText(items[checkedItem]);
//                ((ModifyActivity)context).recreate();
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

