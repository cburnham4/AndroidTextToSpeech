package letshangllc.texttospeech;

import android.annotation.TargetApi;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import junit.framework.Test;

import java.util.HashMap;
import java.util.Locale;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private String TAG = MainActivity.class.getSimpleName();
    private TextToSpeech textToSpeech;

    private AdsHelper adsHelper;

    private Toolbar toolbar;

    private EditText et_category;

    private Button btn_post_message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.setTitle(getResources().getString(R.string.app_name));

        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.ERROR){
                    Log.e(TAG, "ERROR");
                }
                textToSpeech.setLanguage(Locale.ENGLISH);
                textToSpeech.setSpeechRate(.83f);
            }
        });

        et_category = (EditText) findViewById(R.id.et_category);
        btn_post_message = (Button) findViewById(R.id.btn_post_message);


        btn_post_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String category = et_category.getText().toString();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ttsGreater21(category);
                } else {
                    ttsUnder20(category);
                }

            }
        });

        adsHelper = new AdsHelper(this.getWindow().getDecorView(), getResources().getString(R.string.admob_id),this);
        adsHelper.setUpAds();
        int delay = 1000; // delay for 1 sec.
        int period = getResources().getInteger(R.integer.ad_refresh_rate);
        java.util.Timer timer = new java.util.Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                adsHelper.refreshAd();  // display the data
            }
        }, delay, period);


       // textToSpeech.speak("HELLO THIS IS A TEST MESSAGE", TextToSpeech.QUEUE_FLUSH, null);



    }

    @SuppressWarnings("deprecation")
    private void ttsUnder20(String text) {
        HashMap<String, String> map = new HashMap<>();
        map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "MessageId");
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, map);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void ttsGreater21(String text) {
        String utteranceId=this.hashCode() + "";
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, utteranceId);
    }
}
