package cookmap.cookandroid.com.studentapp;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Calendar;

public class NfcTagging extends AppCompatActivity {
    public static Activity nfcTacAc;
    private NfcAdapter nfcAdapter;
    private PendingIntent pendingIntent;
    boolean dataFlag = false;
    static Vibrator mVibe;
    byte[] tagId;
    String stTagId, studentId;
    Context context = this;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tagging);
        nfcTacAc = NfcTagging.this;
        try{
            IntroActivity introAc = (IntroActivity)IntroActivity.introAc;
            introAc.finish();
            LoginActivity loginAc = (LoginActivity)LoginActivity.loginAc;
            loginAc.finish();
        } catch (NullPointerException e){}

        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(context.openFileInput("studentId.txt")));
            studentId = br.readLine();
            br.close();
        }catch (IOException e){}

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if(nfcAdapter == null) {
            Toast.makeText(getApplicationContext(), "This phone is not NFC enable", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        findViewById(R.id.logoutbt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    OutputStreamWriter osw = new OutputStreamWriter(context.openFileOutput("studentId.txt", Context.MODE_PRIVATE));
                    osw.write("");
                    osw.close();
                } catch (IOException e) {
                }
                LogoutTask logoutTask = new LogoutTask(context, studentId);
                logoutTask.execute();
            }
        });
    }

    @Override
    protected void onPause() {//앱 종료시 NFC어댑터 비활성화
        if (nfcAdapter != null) {
            nfcAdapter.disableForegroundDispatch(this);
        }
        super.onPause();
    }

    @Override
    protected void onResume() { //앱 실행시 NFC어댑터 활성화
        super.onResume();
        if (nfcAdapter != null) {
            nfcAdapter.enableForegroundDispatch(this, pendingIntent, null, null);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        if (tag != null) {
            tagId = tag.getId();
            dataFlag = true;
            stTagId =  toHexString(tagId);
            Log.i("태그", stTagId);
            mVibe = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
            TaggingTask taggingTask = new TaggingTask(stTagId, studentId, this);
            taggingTask.execute();
        }
    }

    public static final String CHARS = "0123456789ABCDEF";

    public static String toHexString(byte[] data) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length; ++i) {
            sb.append(CHARS.charAt((data[i] >> 4) & 0x0F))
                    .append(CHARS.charAt(data[i] & 0x0F));
        }
        return sb.toString();
    }
}
