package jowney.com.common.util;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.util.Log;

/**
 * Time:2019/3/26
 * Author:jowney(^*_*^)
 * Description:
 */

public class AudioRecordUtils {
    private static final String TAG = "AudioRecord";
    private static AudioRecordUtils mAudioRecordUtils;

    private static final int SAMPLE_RATE_IN_HZ = 8000;
    private static final int BUFFER_SIZE = AudioRecord.getMinBufferSize(SAMPLE_RATE_IN_HZ, AudioFormat.CHANNEL_IN_DEFAULT, AudioFormat.ENCODING_PCM_16BIT);
    private AudioRecord mAudioRecord;
    private boolean mIsRecording;
    private Object mLock;

    public interface OnVoiceValueListener {
        void onVoiceValue(int value);
    }

    public AudioRecordUtils() {
        mLock = new Object();
    }

    public static synchronized AudioRecordUtils getInstance() {
        if (mAudioRecordUtils == null) {
            mAudioRecordUtils = new AudioRecordUtils();
        }
        return mAudioRecordUtils;
    }

    public void startAudioRecord(final OnVoiceValueListener onVoiceValueListener) {
        if (mIsRecording) {
            return;
        }
        mAudioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC,
                SAMPLE_RATE_IN_HZ, AudioFormat.CHANNEL_IN_DEFAULT,
                AudioFormat.ENCODING_PCM_16BIT, BUFFER_SIZE);
        if (mAudioRecord == null) {
            Log.e(TAG, "mAudioRecord初始化失败");
            return;
        }
        mIsRecording = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                mAudioRecord.startRecording();
                short[] buffer = new short[BUFFER_SIZE];
                while (mIsRecording) {
                    //r是实际读取的数据长度，一般而言r会小于buffersize
                    int r = mAudioRecord.read(buffer, 0, BUFFER_SIZE);
                    long v = 0;
                    // 将 buffer 内容取出，进行平方和运算
                    for (int i = 0; i < buffer.length; i++) {
                        v += buffer[i] * buffer[i];
                    }
                    // 平方和除以数据总长度，得到音量大小。
                    double mean = v / (double) r;
                    double volume = 10 * Math.log10(mean);
                    onVoiceValueListener.onVoiceValue((int) Math.rint(volume));
                    synchronized (mLock) {
                        try {
                            mLock.wait(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                mAudioRecord.stop();
                mAudioRecord.release();
                mAudioRecord = null;
            }

        }).start();
    }

    public void stopAudioRecord() {
        mIsRecording = false;
    }
}
