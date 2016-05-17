package cookmap.cookandroid.com.professorapp;

import java.io.Serializable;

/**
 * Created by Administrator on 2016-02-13.
 */
public class InquiryData implements Serializable {
    private String subjectName;
    private String dateOrStudentId;
    private String choice;

    InquiryData(String s, String d, String c){
        subjectName = s;
        dateOrStudentId = d;
        choice = c;
    }

    public String getChoice() {
        return choice;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getDateOrStudentId() {
        return dateOrStudentId;
    }
}
