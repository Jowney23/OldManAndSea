package jowney.com.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import jowney.com.common.R;


/**
 * Time:2019/1/12
 * Author:jowney(^*_*^)
 * Description:
 */
public class WidgetOvalButton extends RelativeLayout {
    private static final String TAG = "WidgetOvalButton";
    private ImageView mImageView;
    private TextView mTextView;
    private RelativeLayout mRelativeLayout;
    private GradientDrawable mRrawable;
    private int mBtDownColor;
    private int mBtColor;

    public WidgetOvalButton(Context context) {
        this(context, null);
    }

    public WidgetOvalButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WidgetOvalButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.widget_oval_button, this);
        mImageView = view.findViewById(R.id.img);
        mTextView = view.findViewById(R.id.text);
        mRelativeLayout = view.findViewById(R.id.oval_layout);

        //加载自定义属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.WidgetOvalButton);
        if (typedArray != null) {
            int btIconRes = typedArray.getResourceId(R.styleable.WidgetOvalButton_bt_ico, 0);
            String btText = typedArray.getString(R.styleable.WidgetOvalButton_bt_text);//文字内容
            float btTextSize = typedArray.getDimension(R.styleable.WidgetOvalButton_bt_text_size, 10);//文字大小
            int btTextColor = typedArray.getColor(R.styleable.WidgetOvalButton_bt_text_color, 16);//文字颜色
            mBtDownColor = typedArray.getColor(R.styleable.WidgetOvalButton_bt_color_down, Color.parseColor("#401E90B5"));//按钮按下后颜色
            mBtColor = typedArray.getColor(R.styleable.WidgetOvalButton_bt_color, Color.parseColor("#40000000"));//按钮颜色
            int btStrokeColor = typedArray.getColor(R.styleable.WidgetOvalButton_bt_stroke_color, Color.parseColor("#1E90B5"));
            float btStrokewidth = typedArray.getDimension(R.styleable.WidgetOvalButton_bt_stroke_width, 2);
            float btCornorRadius = typedArray.getDimension(R.styleable.WidgetOvalButton_bt_corner_radius, 20);//矩形圆角半径
            float btIconMarginLeft = typedArray.getDimension(R.styleable.WidgetOvalButton_bt_ico_marginLeft, 10);
            float btTextMarginRight = typedArray.getDimension(R.styleable.WidgetOvalButton_bt_text_marginRight, 10);
            view.setClickable(true);
            if (btIconRes != 0) {
                LayoutParams imageParams = (LayoutParams) mImageView.getLayoutParams();
                imageParams.setMarginStart((int) btIconMarginLeft);
                mImageView.setLayoutParams(imageParams);
                mImageView.setBackgroundResource(btIconRes);
                LayoutParams textParams = (LayoutParams) mTextView.getLayoutParams();
                textParams.setMarginEnd((int) btTextMarginRight);
                textParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                mTextView.setLayoutParams(textParams);

            } else {
                LayoutParams params = (LayoutParams) mTextView.getLayoutParams();
                params.addRule(RelativeLayout.CENTER_HORIZONTAL);
                mTextView.setLayoutParams(params);
            }
            mTextView.setText(btText);
            mTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, btTextSize);
            mTextView.setTextColor(btTextColor);
            if (mRrawable == null) {
                mRrawable = new GradientDrawable();
            }
            mRrawable.setCornerRadius(btCornorRadius);
            mRrawable.setStroke((int) btStrokewidth, btStrokeColor);
            mRrawable.setColor(mBtColor);
            setBackground(mRrawable);
           /* mRrawable = (GradientDrawable) mRelativeLayout.getBackground();
            mRrawable.setCornerRadius(btCornorRadius);
            mRrawable.setStroke((int) btStrokewidth, btStrokeColor);
            mRrawable.setColor(mBtColor);*/

        }
        typedArray.recycle();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.i(TAG, "onTouchEvent:down ");
                mRrawable.setColor(mBtDownColor);
                break;
            case MotionEvent.ACTION_UP:
                Log.i(TAG, "onTouchEvent: cancel");
                mRrawable.setColor(mBtColor);
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    public void setTextContent(String content){
        mTextView.setText(content);
    }
}
