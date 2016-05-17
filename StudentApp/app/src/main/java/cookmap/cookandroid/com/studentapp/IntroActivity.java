package cookmap.cookandroid.com.studentapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * Created by Administrator on 2016-02-04.
 */
public class IntroActivity extends Activity {
    //static String studentId;
    public static Activity introAc;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        introAc = IntroActivity.this;
        TelephonyManager mgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String s = mgr.getSimSerialNumber();
        SerialNumberTask serialNumberTask = new SerialNumberTask(this, s);
        serialNumberTask.execute();
    }

}
