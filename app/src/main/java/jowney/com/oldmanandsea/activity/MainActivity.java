package jowney.com.oldmanandsea.activity;

import android.os.Bundle;
import android.util.Log;

import jowney.com.common.util.SoundPoolUtils;
import jowney.com.oldmanandsea.R;
import jowney.com.oldmanandsea.view.BarrierAnimation;
import jowney.com.oldmanandsea.view.WaveAnimation;

public class MainActivity extends BaseActivity {
    private static final String TAG = "wxy";
    BarrierAnimation mBarrierAnimation;
    WaveAnimation mWaveAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_main);
        mBarrierAnimation = findViewById(R.id.barrier);
        mWaveAnimation = findViewById(R.id.wave);
        mWaveAnimation.setBarrier(mBarrierAnimation);
        SoundPoolUtils.getInstance().playSound("rain_sound", -1);
        SoundPoolUtils.getInstance().playSound("thunder_sound", -1);
        Log.i(TAG, "onCreate: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        SoundPoolUtils.getInstance().resumeSound();
        Log.i(TAG, "onRestart: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        Log.i(TAG, "onResume: ");
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: ");
        SoundPoolUtils.getInstance().pauseSound();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }
}
