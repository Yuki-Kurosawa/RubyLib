package jp.ruby.rubylibrary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.nio.charset.Charset;
import java.util.Locale;

import yuki.control.extended.WebViewEx;
import yuki.resource.extended.GsonConvert;
import yuki.resource.extended.StorageIOManager;
import yuki.resource.extended.UIController;
import yuki.tts.extended.TTSComplexController;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView wv = (WebView) findViewById(R.id.wv);
        wv.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.loadUrl("http://10.1.1.134:8282/Client.html");
        wv.addJavascriptInterface(new TTSComplexController(getApplicationContext(), Locale.SIMPLIFIED_CHINESE), "tts");
        //wv.addJavascriptInterface(new JS(getApplicationContext()), "tts");
    }
}


