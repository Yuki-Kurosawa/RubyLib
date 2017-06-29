package jp.ruby.rubylibrary;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.nio.charset.Charset;

import yuki.resource.extended.StorageIOManager;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv=(TextView)findViewById(R.id.tv);
        String s= null;
        try {
            StorageIOManager.WriteStringToFileSystem(getApplicationContext(),"/storage/emulated/0/1.txt","TEST IO");
            s = StorageIOManager.ReadStringFromFileSystem(getApplicationContext(),"/system/bin/sh",true);
            tv.setText(s);
        } catch (Exception e) {
            e.printStackTrace();
            tv.setText(e.getLocalizedMessage());
        }

    }
}
