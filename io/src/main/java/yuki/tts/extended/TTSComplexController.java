package yuki.tts.extended;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;
import android.webkit.JavascriptInterface;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Akeno on 2017/08/11.
 */

public class TTSComplexController {
    private Context context;
    private TextToSpeech tts;
    private ArrayList<String> textList;

    public TTSComplexController(final Context context,final Locale locale){

        // TODO Auto-generated constructor stub
        this.context = context;
        textList=new ArrayList<String>();
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
        tts.setOnUtteranceProgressListener(new UtteranceProgressListener() {
            @Override
            public void onStart(String s) {

            }

            @Override
            public void onDone(String s) {
                if(textList.size()>0) {
                    textList.remove(0);
                }
            }

            @Override
            @Deprecated
            public void onError(String s) {

            }
        });
    }
    @JavascriptInterface
    public void say(String text) {
        textList.add(text);
        tts.speak(text,TextToSpeech.QUEUE_ADD,null,"TTS");
    }
}
