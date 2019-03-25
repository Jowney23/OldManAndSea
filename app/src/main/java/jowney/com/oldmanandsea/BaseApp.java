package jowney.com.oldmanandsea;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import jowney.com.common.util.SoundPoolUtils;

/**
 * Creator: Jowney  (~._.~)
 * Date: 2019/3/24/14:59
 * Description:
 */
public class BaseApp extends Application {
    private static final String TAG = "BaseApp";
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;

        Log.i(TAG, "onCreate: ");
    }
    public static Context getContext() {
        return mContext;
    }
}
