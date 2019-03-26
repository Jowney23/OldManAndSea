package jowney.com.oldmanandsea.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.LinearInterpolator;

import jowney.com.oldmanandsea.R;

/**
 * Creator: Jowney  (~._.~)
 * Date: 2019/3/23/22:59
 * Description:
 */
public class BarrierAnimation extends BaseView {
    private static final String TAG = "BarrierAnimation";
    int mOffset;
    Paint mBarrierPaint;
    Path mBarrierPath;
    PathMeasure mPathMeasure;
    int mRectW = 100;
    int mRectH = 100;
    float[] pos = new float[2];
    float[] tan = new float[2];
    Bitmap mBarrierBitmap;
    ValueAnimator mValueAnimator;
    public BarrierAnimation(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mBarrierPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBarrierPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mBarrierPaint.setColor(Color.parseColor("#5788b2"));
        mBarrierPath = new Path();
        mPathMeasure = new PathMeasure();
        mBarrierBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.barrier2);
        Log.i(TAG, "BarrierAnimation: x"+mBarrierBitmap.getWidth()+ " h"+mBarrierBitmap.getHeight());
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mValueAnimator = ValueAnimator.ofInt(0, mScreenW + 400);
        mValueAnimator.setDuration(3500);
        mValueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mValueAnimator.setInterpolator(new LinearInterpolator());
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mOffset = (int) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        mValueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                mRectH = 200 + (int) (Math.random() * 200);
                mRectW = 100 + (int) (Math.random() * 200);
            }
        });
        mValueAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mBarrierPath.reset();
        mBarrierPath.moveTo(mScreenW + 100, mScreenH);
        mBarrierPath.lineTo(-500, mScreenH);
        mPathMeasure.setPath(mBarrierPath, false);
        mPathMeasure.getPosTan(mOffset, pos, tan);
        canvas.drawRect(pos[0], pos[1] - mRectH, pos[0] + mRectW, pos[1], mBarrierPaint);

    }
}
