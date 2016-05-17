package cookmap.cookandroid.com.professorapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText editId, editPass;
    private Button bt;
    String id, pass;
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TelephonyManager mgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String s = mgr.getSimSerialNumber();
        SerialNumberTask serialNumberTask = new SerialNumberTask(this, s);
        serialNumberTask.execute();
    }
}
