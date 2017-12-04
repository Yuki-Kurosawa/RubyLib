package yuki.pm.extended;

import android.content.DialogInterface;

/**
 * Created by Sora-chan on 2015/12/14.
 */
public abstract class UpdateClickListener implements DialogInterface.OnClickListener {
    private UpdateInfo info;

    /**
     * Set Update Info
     * @param info Update Info
     * */
    public void setInfo(UpdateInfo info){
        this.info=info;
    }

    /**
     * Get Update Info
     * @return Update Info
     * */
    public UpdateInfo getInfo(){
        return this.info;
    }

    @Override
    public abstract void onClick(DialogInterface dialog, int which);
}
