package jowney.com.oldmanandsea.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import jowney.com.oldmanandsea.App;
import jowney.com.oldmanandsea.R;

/**
 * Creator: Jowney  (~._.~)
 * Date: 2019/3/24/15:44
 * Description:
 */
public class BackgroundView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    private SurfaceHolder mSurfaceHolder;
    private Canvas mCanvas;
    private Boolean mIsDrawing;
    private Bitmap mBackgroundBitmap;
    private int mBgW;
    private int mBgH;
    private int mScreenW;
    private int mScreenH;
    private Paint mBackgroudPaint;
    private int mOffset;

    public BackgroundView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mIsDrawing = true;
        //开启子线程
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        mScreenW = width;
        mScreenH = height;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public void run() {
        while (mIsDrawing) {
            drawSomething();
        }
    }

    //绘图逻辑
    private void drawSomething() {
        try {
            mOffset+=3;
            if (mOffset >= mBgW){
                mOffset = mOffset - mBgW;
            }
            //获得canvas对象
            mCanvas = mSurfaceHolder.lockCanvas();
            mCanvas.save();
            //   mCanvas.drawColor(Color.YELLOW);
            mCanvas.drawBitmap(mBackgroundBitmap, new Rect(mOffset, 0, mBgW, mBgH), new RectF(0, 0, mScreenW - mOffset, mScreenH), null);
            mCanvas.drawBitmap(mBackgroundBitmap, new Rect(0, 0, mOffset, mBgH), new RectF(mScreenW - mOffset, 0, mScreenW, mScreenH), null);
            // mCanvas.drawBitmap(mBackgroundBitmap, 0, 0, mBackgroudPaint);
            //绘制背景
            mCanvas.restore();

            //绘图
        } catch (Exception e) {

        } finally {
            if (mCanvas != null) {
                //释放canvas对象并提交画布
                mSurfaceHolder.unlockCanvasAndPost(mCanvas);
            }
        }
    }

    /**
     * 初始化View
     */
    private void initView() {
        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);
        setFocusable(true);
        setKeepScreenOn(true);
        setFocusableInTouchMode(true);
        mBackgroundBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg);
        mBgW = mBackgroundBitmap.getWidth();
        mBgH = mBackgroundBitmap.getHeight();
        mBackgroudPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBackgroudPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }
}
