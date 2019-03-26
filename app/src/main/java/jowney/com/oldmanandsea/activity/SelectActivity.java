package jowney.com.oldmanandsea.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import jowney.com.common.widget.WidgetOvalButton;
import jowney.com.oldmanandsea.Contents;
import jowney.com.oldmanandsea.R;

/**
 * Time:2019/3/26
 * Author:jowney(^*_*^)
 * Description:
 */
public class SelectActivity extends BaseActivity {
    WidgetOvalButton mClickModeBt;
    WidgetOvalButton mSoundModeBt;
    Intent mIntent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_select);
        mClickModeBt = findViewById(R.id.click);
        mSoundModeBt = findViewById(R.id.sound);
        mIntent = new Intent(this, MainActivity.class);
        mClickModeBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIntent.putExtra("mode", Contents.CLICK_MODE);
                startActivity(mIntent);

            }
        });

        mSoundModeBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIntent.putExtra("mode", Contents.SOUND_MODE);
                startActivity(mIntent);
            }
        });
    }
}
