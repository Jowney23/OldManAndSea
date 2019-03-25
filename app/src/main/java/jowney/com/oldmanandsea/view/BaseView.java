package jowney.com.oldmanandsea.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Creator: Jowney  (~._.~)
 * Date: 2019/3/23/15:22
 * Description:
 */
public class BaseView extends View {
    protected int mScreenW;
    protected int mScreenH;
    protected int mOldScreenW;
    protected int mOldScreenH;
    public BaseView(Context context) {
        super(context);
    }

    public BaseView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public BaseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mScreenW = w;
        mScreenH = h;
        mOldScreenH = oldh;
        mOldScreenW = oldw;
    }
}
