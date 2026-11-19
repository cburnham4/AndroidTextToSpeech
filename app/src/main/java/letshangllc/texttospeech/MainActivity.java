package letshangllc.texttospeech;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private TextToSpeech textToSpeech;
    private AdsHelper adsHelper;
    private Timer adRefreshTimer;

    private EditText etCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        View root = findViewById(R.id.root_layout);
        ViewCompat.setOnApplyWindowInsetsListener(root, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(getString(R.string.app_name));

        textToSpeech = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.ERROR) {
                Log.e(TAG, "TextToSpeech init ERROR");
                return;
            }
            textToSpeech.setLanguage(Locale.ENGLISH);
            textToSpeech.setSpeechRate(0.83f);
        });

        etCategory = findViewById(R.id.et_category);
        Button btnPostMessage = findViewById(R.id.btn_post_message);
        btnPostMessage.setOnClickListener(view -> {
            String text = etCategory.getText().toString();
            speak(text);
        });

        adsHelper = new AdsHelper(
                getWindow().getDecorView(),
                getString(R.string.admob_id),
                this
        );
        adsHelper.setUpAds();

        int delay = 1000;
        int period = getResources().getInteger(R.integer.ad_refresh_rate);
        adRefreshTimer = new Timer();
        adRefreshTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                adsHelper.refreshAd();
            }
        }, delay, period);
    }

    private void speak(String text) {
        if (textToSpeech == null) {
            return;
        }
        String utteranceId = Integer.toString(hashCode());
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, utteranceId);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (adsHelper != null) {
            adsHelper.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (adsHelper != null) {
            adsHelper.onResume();
        }
    }

    @Override
    protected void onDestroy() {
        if (adRefreshTimer != null) {
            adRefreshTimer.cancel();
            adRefreshTimer = null;
        }
        if (adsHelper != null) {
            adsHelper.onDestroy();
        }
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
            textToSpeech = null;
        }
        super.onDestroy();
    }
}
