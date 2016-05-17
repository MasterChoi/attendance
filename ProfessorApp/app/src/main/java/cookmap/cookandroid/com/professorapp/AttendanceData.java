package cookmap.cookandroid.com.professorapp;

import java.io.Serializable;

/**
 * Created by Administrator on 2016-02-13.
 */
public class AttendanceData implements Serializable {
    String studentId;
    String subjectName;
    String date;
    String hour;
    String state;

    public AttendanceData(String a, String b, String c, String d, String e){
        studentId = a;
        subjectName = b;
        date = c;
        hour = d;
        state = e;
    }
    public String getStudentId() {
        return studentId;
    }
    public String getSubjectName(){ return  subjectName; }
    public String getDate() {
        return date;
    }
    public String getHour() {
        return hour;
    }

    public String getState() {
        return state;
    }

}
