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

import yuki.control.extended.WebViewEx;
import yuki.resource.extended.GsonConvert;
import yuki.resource.extended.StorageIOManager;
import yuki.resource.extended.UIController;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        A a=new A();
        a.a=1;
        a.b="hello world!";
        TextView tv=findViewById(R.id.tv);
        String gson=GsonConvert.SerializeObject(a);
        tv.setText(gson);
        A b=GsonConvert.DeserializeObject(gson,A.class);
        b=null;
        Intent aa=new Intent(Intent.ACTION_VIEW, Uri.parse("weixin://baidu.com/"));
        startActivity(aa);
    }
}


