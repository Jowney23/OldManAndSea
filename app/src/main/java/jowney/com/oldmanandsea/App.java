package jowney.com.oldmanandsea;

import android.app.Application;

import jowney.com.common.util.SoundPoolUtils;

/**
 * Creator: Jowney  (~._.~)
 * Date: 2019/3/23/18:52
 * Description:
 */
public class App extends BaseApp {
    @Override
    public void onCreate() {
        super.onCreate();
        SoundPoolUtils.getInstance().initSP(this);
        SoundPoolUtils.mSoundMap.put("rain_sound", SoundPoolUtils.getInstance().loadSound(R.raw.rain));
        SoundPoolUtils.mSoundMap.put("thunder_sound", SoundPoolUtils.getInstance().loadSound(R.raw.thunder));
    }

}
