package jp.ruby.rubylibrary;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import java.nio.charset.Charset;

import yuki.resource.extended.StorageIOManager;
import yuki.resource.extended.UIController;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv=(TextView)findViewById(R.id.tv);
        String s= null;
        UIController.SetStatusBarBackgroundColor(this,Color.BLACK);
        UIController.SetNavigationBarBackgroundColor(this,Color.RED);
        //UIController.HideStatusBar(this);
        UIController.HideNavigationBar(this);
        //UIController.ShowStatusBar(this);
        UIController.ShowNavigationBar(this);
        try {
            s=String.format("Screen Size\nReal Size: %d px * %d px\n", UIController.GetRealDisplayWidth(this),UIController.GetRealDisplayHeight(this));
            s+=String.format("Real Size: %d dp * %d dp\n", UIController.PixelToDP(UIController.GetRealDisplayWidth(this),UIController.GetRealDisplayMetrics(this).density),
                    UIController.PixelToDP(UIController.GetRealDisplayHeight(this),UIController.GetRealDisplayMetrics(this).density));
            s+=String.format("Logical Size: %d px * %d px\n", UIController.GetLogicalDisplayWidth(this),UIController.GetLogicalDisplayHeight(this));
            s+=String.format("Logical Size: %d dp * %d dp\n", UIController.PixelToDP(UIController.GetLogicalDisplayWidth(this),UIController.GetLogicalDisplayMetrics(this).density),
                    UIController.PixelToDP(UIController.GetLogicalDisplayHeight(this),UIController.GetLogicalDisplayMetrics(this).density));
            tv.setText(s);
        } catch (Exception e) {
            e.printStackTrace();
            tv.setText(e.getLocalizedMessage());
        }

    }
}
