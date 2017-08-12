package yuki.media.extended;

import android.content.Context;
import android.media.AudioManager;

/**
 * Created by Akeno on 2017/08/12.
 */

public class VolumeControl {
    public void GetMaxVolume(Context context,int streamType){
        AudioManager am=(AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
        am.getStreamMaxVolume(streamType);
    }

    public void SetVolume(Context context,int streamType,int volume,int flag){
        AudioManager am=(AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
        am.setStreamVolume(streamType,volume,flag);
    }

    public int GetVolume(Context context, int streamType){
        AudioManager am=(AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
        return am.getStreamVolume(streamType);
    }
}
