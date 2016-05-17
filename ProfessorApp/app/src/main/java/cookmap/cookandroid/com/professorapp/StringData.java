package cookmap.cookandroid.com.professorapp;

import java.io.Serializable;

/**
 * Created by Samsung on 2016-02-07.
 */
public class StringData implements Serializable {
    String[] data;
    public String[] getData(){
        return data;
    }

    public void setData(String[] data){
        this.data=data;
    }
}
