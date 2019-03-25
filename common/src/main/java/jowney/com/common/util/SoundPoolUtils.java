package jowney.com.common.util;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;

import java.util.HashMap;

import jowney.com.common.CommonApp;

/**
 * Creator: Jowney  (~._.~)
 * Date: 2019/3/23/18:50
 * Description:
 */
public class SoundPoolUtils {
    public static HashMap<String, Integer> mSoundMap = new HashMap<>();
    private Context mContext;
    private static SoundPoolUtils mSoundPoolUtils;
    private SoundPool mSoundPool;

    public static SoundPoolUtils getInstance() {
        if (mSoundPoolUtils == null) {
            mSoundPoolUtils = new SoundPoolUtils();
        }
        return mSoundPoolUtils;
    }

    public void initSP(Context context) {
        mContext = context;
        if (mSoundPool == null) {
            // 5.0 及 之后
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                AudioAttributes audioAttributes = null;
                audioAttributes = new AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build();

                mSoundPool = new SoundPool.Builder()
                        .setMaxStreams(16)
                        .setAudioAttributes(audioAttributes)
                        .build();
            } else { // 5.0 以前
                mSoundPool = new SoundPool(16, AudioManager.STREAM_MUSIC, 0);  // 创建SoundPool
            }
        }
    }

    /**
     * 加载资源文件并获取加载后返回的id
     *
     * @param resId
     * @return
     */
    public int loadSound(int resId) {
        return mSoundPool.load(mContext, resId, 1);
    }

    //第一个参数soundID
    //第二个参数leftVolume为左侧音量值（范围= 0.0到1.0）
    //第三个参数rightVolume为右的音量值（范围= 0.0到1.0）
    //第四个参数priority 为流的优先级，值越大优先级高，影响当同时播放数量超出了最大支持数时SoundPool对该流的处理
    //第五个参数loop 为音频重复播放次数，0为值播放一次，-1为无限循环，其他值为播放loop+1次
    //第六个参数 rate为播放的速率，范围0.5-2.0(0.5为一半速率，1.0为正常速率，2.0为两倍速率)
    public void playSound(final String voiceName, int loop) {
        mSoundPool.play(mSoundMap.get(voiceName), 1, 1, 1, loop, 1);
    }

    public void  pauseSound(){
        mSoundPool.autoPause();
    }

    public void resumeSound(){
        mSoundPool.autoResume();
    }

    public void destroySound(){
        mSoundPool.release();
    }


}
