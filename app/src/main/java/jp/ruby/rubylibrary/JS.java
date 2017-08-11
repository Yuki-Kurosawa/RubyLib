package jp.ruby.rubylibrary;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import java.util.Locale;

/**
 * Created by Akeno on 2017/08/11.
 */

public class JS {
    private Context context;
    private TextToSpeech tts;

    public JS(final Context context) {

        // TODO Auto-generated constructor stub
        this.context = context;
        tts = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                // TODO Auto-generated method stub
                if (status == TextToSpeech.SUCCESS) {
                    int result = tts.setLanguage(Locale.SIMPLIFIED_CHINESE);
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Toast.makeText(context, "Language is not available.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    @JavascriptInterface
    public void say(String text) {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }
}

