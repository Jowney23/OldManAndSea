package jowney.com.oldmanandsea.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

import jowney.com.oldmanandsea.dialog.GameOverDialog;

/**
 * Creator: Jowney  (~._.~)
 * Date: 2019/3/23/14:51
 * Description:
 */
public class WaveAnimation extends View {
    private static final String TAG = "WaveBezier";
    private final int CIRCLE_R = 100;
    private final int WAVE_LENGHT = 1000;
    private float[] mWavePos;
    private float[] temporay = new float[2];
    private float[] tan;
    private int mScreenHeight;
    private int mScreenWidth;
    private int mOffset;
    private int mWaveCount;
    private int mCenterY;
    private Paint mPathPaint;
    private Paint mCirclePaint;
    private Path mWavePath;
    private PathMeasure mWavePathMeasure;
    private BarrierAnimation mBarrierView;
    private CloudAnimation mCloudView;
    ValueAnimator mAnimator;

    public WaveAnimation(Context context) {
        super(context);
    }

    public WaveAnimation(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPathPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPathPaint.setColor(Color.parseColor("#605788b2"));
        mPathPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setColor(Color.RED);
        mCirclePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mWavePos = new float[2];
        tan = new float[2];
        mWavePath = new Path();
        mWavePathMeasure = new PathMeasure();

    }

    public void setBarrier(View view) {
        if (view instanceof BarrierAnimation) {
            mBarrierView = (BarrierAnimation) view;
        } else if (view instanceof CloudAnimation) {
            mCloudView = (CloudAnimation) view;
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mScreenHeight = h;
        mScreenWidth = w;
        mWaveCount = (int) Math.round(mScreenWidth / WAVE_LENGHT + 1.5);
        mCenterY = mScreenHeight / 2;
        mAnimator = ValueAnimator.ofInt(0, WAVE_LENGHT);
        mAnimator.setDuration(4000);
        mAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mAnimator.setInterpolator(new LinearInterpolator());
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mOffset = (int) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        mAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mWavePath.reset();
        mWavePath.moveTo(-WAVE_LENGHT + mOffset, mCenterY);
        for (int i = 0; i < mWaveCount; i++) {
            mWavePath.quadTo((-WAVE_LENGHT * 3 / 4) + (i * WAVE_LENGHT) + mOffset, mCenterY + 160, (-WAVE_LENGHT / 2) + (i * WAVE_LENGHT) + mOffset, mCenterY);
            mWavePath.quadTo((-WAVE_LENGHT / 4) + (i * WAVE_LENGHT) + mOffset, mCenterY - 160, i * WAVE_LENGHT + mOffset, mCenterY);
        }
        mWavePathMeasure.setPath(mWavePath, false);
        float wavePerimeter = mWavePathMeasure.getLength() / (float) mWaveCount;
        float waveOffset = wavePerimeter / 1000;
        mWavePath.lineTo(mScreenWidth, mScreenHeight);
        mWavePath.lineTo(0, mScreenHeight);
        mWavePath.close();

        mWavePathMeasure.getPosTan(1500 - mOffset * waveOffset, mWavePos, tan);
        canvas.drawCircle(mWavePos[0], mWavePos[1], CIRCLE_R, mCirclePaint);
        canvas.drawPath(mWavePath, mPathPaint);
        if (judgeImpact()){
            GameOverDialog gameOverDialog = new GameOverDialog(getContext());
            gameOverDialog.show();
            mAnimator.cancel();
            mBarrierView.mValueAnimator.cancel();
        }

    }

    private Boolean judgeImpact() {
        if (mBarrierView.pos[0] >= mWavePos[0] - 50 && mBarrierView.pos[0] <= mWavePos[0] + 50) {
            Log.i(TAG, "judgeImpact: 进来了进来了");
            if ((mScreenHeight - mWavePos[1] - 50) <= mBarrierView.mRectH) {
                return true;
            }
        }

        return false;
    }

}