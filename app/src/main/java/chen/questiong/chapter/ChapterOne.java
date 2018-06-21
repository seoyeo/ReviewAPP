package chen.questiong.chapter;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;

import chen.questiong.MainActivity;
import chen.questiong.R;

public class ChapterOne extends AppCompatActivity implements View.OnClickListener {

    private ImageButton sqlA;
    private ImageButton sqlB;
    private ImageButton sqlC;
    private ImageButton sqlD;

    private ImageView back;

    int width;
    int height;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chapterone);
        getDisplayMetrics();

        sqlA = (ImageButton) findViewById(R.id.sql_A);
        sqlB = (ImageButton) findViewById(R.id.sql_B);
        sqlC = (ImageButton) findViewById(R.id.sql_C);
        sqlD = (ImageButton) findViewById(R.id.sql_D);
        back = (ImageView) findViewById(R.id.listback);

        AnimationT(sqlA,-150);
        AnimationT(sqlB,-50);
        AnimationT(sqlC,50);
        AnimationT(sqlD,150);



        sqlA.setOnClickListener(this);
        sqlB.setOnClickListener(this);
        sqlC.setOnClickListener(this);
        sqlD.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sql_A:
                startAction(1,1);
                break;
            case R.id.sql_B:
                startAction(1,2);
                break;
            case R.id.sql_C:
                startAction(1,3);
                break;
            case R.id.sql_D:
                startAction(1,9);
                break;
            case R.id.listback:
                finish();
                break;
            default:
                break;

        }
    }

    public void startAction(int param,int param1) {
        Intent intent = new Intent(ChapterOne.this, MainActivity.class);
        intent.putExtra("param", param);
        intent.putExtra("param1", param1);
        startActivity(intent);
    }

    public void AnimationT(View view,int x) {
        PropertyValuesHolder phv1 = PropertyValuesHolder.ofFloat("translationY", x);
        PropertyValuesHolder phv2 = PropertyValuesHolder.ofFloat("alpha", 0,1);
       // PropertyValuesHolder phv2 = PropertyValuesHolder.ofFloat("rotationX", 0);
        ObjectAnimator.ofPropertyValuesHolder(view, phv1,phv2).setDuration(500).start();
    }
    private void startButton(View view) {
       /* Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha);
        imageButton.startAnimation(loadAnimation);*/

        PropertyValuesHolder phv1 = PropertyValuesHolder.ofFloat("rotationY", -180);
        // PropertyValuesHolder phv2 = PropertyValuesHolder.ofFloat("rotationX", 0);
        ObjectAnimator.ofPropertyValuesHolder(view, phv1).setDuration(500).start();
        /*AlphaAnimation al = new AlphaAnimation(0, 1);
        al.setDuration(1000);
        button.startAnimation(al);*/
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
