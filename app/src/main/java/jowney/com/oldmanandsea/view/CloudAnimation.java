package jowney.com.oldmanandsea.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;

/**
 * Creator: Jowney  (~._.~)
 * Date: 2019/3/23/15:07
 * Description:
 */
public class CloudAnimation extends BaseView {
    private static final String TAG = "CloudAnimation";
    int mOffset;
    Paint mCloudPaint;
    Path mCloudPath;
    PathMeasure mPathMeasure;
    float[] pos = new float[2];
    float[] tan = new float[2];


    public CloudAnimation(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mCloudPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCloudPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mCloudPaint.setColor(Color.parseColor("#5788b2"));
        mCloudPath = new Path();
        mPathMeasure = new PathMeasure();

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, mScreenW + 200);
        valueAnimator.setDuration(10000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mOffset = (int) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        valueAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mCloudPath.reset();
        mCloudPath.moveTo(mScreenW + 100, 100);
        mCloudPath.lineTo(-100, 100);
        mPathMeasure.setPath(mCloudPath, false);
        mPathMeasure.getPosTan(mOffset, pos, tan);
        canvas.drawCircle(pos[0], pos[1], 100, mCloudPaint);
    }

    public float[] getPos() {
        return pos;
    }
}
