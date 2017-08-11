package yuki.tts.extended;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import java.util.Locale;

/**
 * Created by Akeno on 2017/08/11.
 */

public class TTSSimpleController {
    private Context context;
    private TextToSpeech tts;

    public TTSSimpleController(final Context context,final Locale locale){

        // TODO Auto-generated constructor stub
        this.context = context;
        tts = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                // TODO Auto-generated method stub
                if (status == TextToSpeech.SUCCESS) {
                    int result = tts.setLanguage(locale);
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.d("TTS","Language '"+locale.getDisplayLanguage()+"'is not supported");
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
