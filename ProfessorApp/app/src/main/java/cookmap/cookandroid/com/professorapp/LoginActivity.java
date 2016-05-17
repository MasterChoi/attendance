package cookmap.cookandroid.com.professorapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Administrator on 2016-02-05.
 */
public class LoginActivity extends Activity {
    String id;
    String pass;
    EditText editId,editPass;
    Button btLogin;
    Context context = this;
    String serial;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editId = (EditText)findViewById(R.id.editId);
        editPass=(EditText)findViewById(R.id.editPass);
        btLogin=(Button)findViewById(R.id.btLogin);
        TelephonyManager mgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        serial = mgr.getSimSerialNumber();
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = editId.getText().toString();
                pass=editPass.getText().toString();

                LoginTask loginTask = new LoginTask(context,id, pass, serial);
                loginTask.execute();
            }
        });

    }
}
