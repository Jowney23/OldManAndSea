package jowney.com.oldmanandsea.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Display;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import jowney.com.oldmanandsea.R;

/**
 * Creator: Jowney  (~._.~)
 * Date: 2019/3/24/17:54
 * Description:
 */
public class GameOverDialog extends Dialog {
    //在构造方法里提前加载了样式
    private Context context;//上下文

    public GameOverDialog(Context context) {
        super(context, R.style.MyDialog);//加载dialog的样式
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //提前设置Dialog的一些样式
        Window dialogWindow = getWindow();
        dialogWindow.setGravity(Gravity.CENTER);//设置dialog显示居中
        //dialogWindow.setWindowAnimations();设置动画效果
        setContentView(R.layout.layout_gameover);
        WindowManager windowManager = ((Activity)context).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = display.getWidth()*4/5;// 设置dialog宽度为屏幕的4/5
        getWindow().setAttributes(lp);
        setCanceledOnTouchOutside(true);//点击外部Dialog消失
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
