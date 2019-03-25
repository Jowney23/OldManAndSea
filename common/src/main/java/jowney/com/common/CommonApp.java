package jowney.com.common;

import android.app.Application;
import android.content.Context;

/**
 * Creator: Jowney  (~._.~)
 * Date: 2019/3/23/19:22
 * Description:
 */
public class CommonApp extends Application {
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getContext() {
        return mContext;
    }
}
