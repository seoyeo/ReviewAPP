package chen.questiong.chapter;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.Toast;

import chen.questiong.R;

public class ChapterThree extends AppCompatActivity implements View.OnClickListener {

    private Button bt_1;

    private Button bt_2;

    private Button bt_3;

    int width, height;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chapterthree);
        getDisplayMetrics();

        bt_1 = (Button) findViewById(R.id.three_one);
        bt_2 = (Button) findViewById(R.id.three_tow);
        bt_3 = (Button) findViewById(R.id.three_three);
        animtion(bt_1, (int) (width*0.6));
        animtion(bt_2, (int) (-width*0.6));
        animtion(bt_3, (int) (width*0.4));

        bt_1.setOnClickListener(this);
        bt_2.setOnClickListener(this);
        bt_3.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.three_one:

                Toast.makeText(ChapterThree.this, "等待添加数据库", Toast.LENGTH_SHORT).show();
                break;
            case R.id.three_tow:

                Toast.makeText(ChapterThree.this, "等待添加数据库", Toast.LENGTH_SHORT).show();
                break;
            case R.id.three_three:

                Toast.makeText(ChapterThree.this, "等待添加数据库", Toast.LENGTH_SHORT).show();
                break;
            default:

                break;
        }
    }

    public void animtion(View view,int x) {
        PropertyValuesHolder pvh = PropertyValuesHolder.ofFloat("translationX", x);
        PropertyValuesHolder pvh1 = PropertyValuesHolder.ofFloat("scaleX", 1, 0, 1);
        PropertyValuesHolder pvh2 = PropertyValuesHolder.ofFloat("scaleY", 1, 0, 1);
        ObjectAnimator.ofPropertyValuesHolder(view, pvh, pvh1, pvh2).setDuration(800).start();
    }

    /**
     * 获取屏幕尺寸
     */
    public void getDisplayMetrics() {
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        width = metric.widthPixels;     // 屏幕宽度（像素）
        height = metric.heightPixels;   // 屏幕高度（像素）
    }
}
