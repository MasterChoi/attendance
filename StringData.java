package sampleServer;

import java.io.Serializable;

public class StringData implements Serializable {
    String[] data;
    public String[] getData(){
        return data;
    }

    public void setData(String[] data){
        this.data=data;
    }
}
