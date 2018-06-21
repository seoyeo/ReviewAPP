package chen.questiong.chapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import chen.questiong.MainActivity;
import chen.questiong.R;

public class ChapterTow extends Activity implements View.OnClickListener {

    private Button bt_one;
    private Button bt_tow;
    private Button bt_three;
    private Button bt_fuer;

    private int width;
    private int height;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.chaptertow);
        getDisplayMetrics();
        bt_one = (Button) findViewById(R.id.tow_one);
        bt_tow = (Button) findViewById(R.id.tow_tow);
        bt_three = (Button) findViewById(R.id.tow_three);
        bt_fuer = (Button) findViewById(R.id.four);


        bt_one.setOnClickListener(this);
        bt_tow.setOnClickListener(this);
        bt_three.setOnClickListener(this);
        bt_fuer.setOnClickListener(this);

        BtAnimation(bt_one, (int)(width * 0.15), (int)(height * 0.28));
        BtAnimation(bt_tow,(int)(-width * 0.15),(int)(height * 0.28));
        BtAnimation(bt_three,(int)(width * 0.15),(int)(-height * 0.28));
        BtAnimation(bt_fuer,(int)(-width * 0.15),(int)(-height * 0.28));

    }

    public void BtAnimation( View v, int x, int y) {

        PropertyValuesHolder phv1 = PropertyValuesHolder.ofFloat("translationX", x);
        PropertyValuesHolder phv2 = PropertyValuesHolder.ofFloat("translationY", y);
        ObjectAnimator.ofPropertyValuesHolder(v, phv1, phv2).setDuration(800).start();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tow_one:
                startAction(1,5);
                break;
            case R.id.tow_tow:
                startAction(1,6);
                break;
            case R.id.tow_three:
                startAction(1,7);
                break;
            case R.id.four:
                startAction(1,8);
                break;
        }

    }

    public void getDisplayMetrics() {
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        width = metric.widthPixels;     // 屏幕宽度（像素）
        height = metric.heightPixels;   // 屏幕高度（像素）
    }

    public void startAction(int param,int param1) {
        Intent intent = new Intent(ChapterTow.this, MainActivity.class);
        intent.putExtra("param", param);
        intent.putExtra("param1", param1);
        startActivity(intent);
    }
}
