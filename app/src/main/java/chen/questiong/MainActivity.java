package chen.questiong;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import chen.questiong.database.DataBase;
import chen.questiong.database.Question;
import cn.jpush.android.api.JPushInterface;

public class MainActivity extends AppCompatActivity {

    //数据库的名称
    public String DB_NAME ;
    //数据库的地址
    private String DB_PATH = "/data/data/chen.questiong/databases/";
    //总的题目数据
    private int count;
    //当前显示的题目
    private int corrent;
    //问题
    private TextView tv_title;
    //选项
    RadioButton[] mRadioButton = new RadioButton[4];
    //上一题
    private Button btn_up;
    //下一题
    private Button btn_down;
    //详情
    private TextView tv_result;
    //容器
    private RadioGroup mRadioGroup;
    //是否进入错题模式
    private boolean wrongMode;
    //接收的参数
    protected int param;
    //
    protected int param1;
    //是否选择
    private boolean isCheck = false;
    //计时参数
    private int  m = 0, s = 0;
    //time对象
    Timer timer = new Timer();
    //计数栏
    private TextView timerView;
    //
    private Chronometer chronometer;
    //进度栏
    private TextView prosse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        JPushInterface.setDebugMode(true);//如果时正式版就改成false
        JPushInterface.init(this);
        initView();
        reciveParam();
        switch (param) {
            case 0:
                Toast.makeText(MainActivity.this, "没有找到数据库", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                DB_NAME = "nuita.db";
                initFile();
                initDB();
                break;
            default:
                break;
        }
    }


    public void  reciveParam() {
        Intent intent = getIntent();
        param = intent.getIntExtra("param", 0);
        param1 = intent.getIntExtra("param1", 0);
    }

    /**
     * UI初始化
     */
    private void initView() {
        wrongMode = false;

        tv_title = (TextView) findViewById(R.id.tv_title);

        mRadioButton[0] = (RadioButton) findViewById(R.id.RadioA);
        mRadioButton[1] = (RadioButton) findViewById(R.id.RadioB);
        mRadioButton[2] = (RadioButton) findViewById(R.id.RadioC);
        mRadioButton[3] = (RadioButton) findViewById(R.id.RadioD);

        btn_down = (Button) findViewById(R.id.btn_down);
        btn_up = (Button) findViewById(R.id.btn_up);

        tv_result = (TextView) findViewById(R.id.tv_result);

        mRadioGroup = (RadioGroup) findViewById(R.id.mRadioGroup);

        timerView = (TextView) findViewById(R.id.time);

        prosse = (TextView) findViewById(R.id.prosse);

        chronometer = (Chronometer) findViewById(R.id.time00);
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();
        handler.sendEmptyMessage(2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.maintoolbar);

            toolbar.setTitle("");
            setSupportActionBar(toolbar);
            //显示返回按钮
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this).setMessage("题库君鄙视半途而废的人")
                        .setPositiveButton("接受鄙视", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        }).setNegativeButton("回去做题", null).show();
            }
        });

    }

    final Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 2:
                    prosse.setText((corrent+1) + "/" + (count+1));
                    break;
                default:
                    break;
            }
        }
    };


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            new AlertDialog.Builder(MainActivity.this).setMessage("题库君鄙视半途而废的人")
                    .setPositiveButton("接受鄙视", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    }).setNegativeButton("回去做题", null).show();
            return true;
        } else {

            return super.onKeyDown(keyCode, event);
        }
    }


    private void initDB() {
        DataBase dbService = new DataBase();
        dbService.DBParam = param;
        dbService.DBParam1 = param1;
        final List<Question> list = dbService.getQuestionA();

        count = list.size();
        corrent = 0;

        Question q = list.get(0);
        tv_title.setText(q.question);

        mRadioButton[0].setText(q.answerA);
        mRadioButton[1].setText(q.answerB);
        mRadioButton[2].setText(q.answerC);
        mRadioButton[3].setText(q.answerD);

        //上一题
        btn_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (corrent > 0) {
                    handler.sendEmptyMessage(2);
                    corrent--;

                    Question q = list.get(corrent);

                    tv_title.setText(q.question);

                    mRadioButton[0].setText(q.answerA);
                    mRadioButton[1].setText(q.answerB);
                    mRadioButton[2].setText(q.answerC);
                    mRadioButton[3].setText(q.answerD);

                    tv_result.setText(q.explaination);
                    if (wrongMode == true) {
                        int selecte = q.answer;
                        if (selecte == 0) {
                            tv_result.setText("正确答案为A");
                        } else if (selecte == 1) {
                            tv_result.setText("正确答案为B");
                        } else if (selecte == 2) {
                            tv_result.setText("正确答案为C");
                        } else if (selecte == 3) {
                            tv_result.setText("正确答案为D");
                        }
                    }
                    mRadioGroup.clearCheck();

                    //设置选中
                    if (q.selectedAnswer != -1) {
                        mRadioButton[q.selectedAnswer].setChecked(true);
                    }
                }

            }
        });

        //下一题
        btn_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //判断是否为最后一题
                if (corrent < count - 1) {
                    if (isCheck == true) {
                        corrent++;
                        handler.sendEmptyMessage(2);
                        isCheck = false;
                    } else {
                        Toast.makeText(MainActivity.this, "你还没有选择", Toast.LENGTH_SHORT).show();
                    }
                    Question q = list.get(corrent);

                    tv_title.setText(q.question);

                    mRadioButton[0].setText(q.answerA);
                    mRadioButton[1].setText(q.answerB);
                    mRadioButton[2].setText(q.answerC);
                    mRadioButton[3].setText(q.answerD);

                    tv_result.setText(q.explaination);

                    if (wrongMode == true) {
                        int selecte = q.answer;
                        if (selecte == 0) {
                            tv_result.setText("正确答案为A");
                        } else if (selecte == 1) {
                            tv_result.setText("正确答案为B");
                        } else if (selecte == 2) {
                            tv_result.setText("正确答案为C");
                        } else if (selecte == 3) {
                            tv_result.setText("正确答案为D");
                        }
                    }
                    mRadioGroup.clearCheck();
                    //设置选中
                    if (q.selectedAnswer != -1) {
                        mRadioButton[q.selectedAnswer].setChecked(true);
                    }
                } else if (corrent == count - 1 && wrongMode == true) {

                    new AlertDialog.Builder(MainActivity.this).setMessage("已经到达最后一道题，是否退出？")
                            .setIcon(R.drawable.ic_menu_camera)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            }).setNegativeButton("取消", null).show();

                } else {
                    //没有题目了，开始检测正确性
                    final List<Integer> wrongList = checkAnswer(list);

                    if (wrongList.size() == 0) {
                        new AlertDialog.Builder(MainActivity.this).setTitle("提示").setMessage("厉害呀，全部答对！")
                                .setPositiveButton("过奖了", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                    }
                                }).show();
                    }

                    //窗口提示
                    new AlertDialog.Builder(MainActivity.this)
                            .setMessage("答对了" + (list.size() - wrongList.size()) + "道题" + "\n"
                                    + "答错了" + wrongList.size() + "道题" + "\n" + "是否查看错题？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            wrongMode = true;
                            List<Question> newList = new ArrayList<Question>();
                            for (int i = 0; i < wrongList.size(); i++) {
                                newList.add(list.get(wrongList.get(i)));
                            }
                            list.clear();
                            for (int i = 0; i < newList.size(); i++) {
                                list.add(newList.get(i));
                            }
                            corrent = 0;
                            count = list.size();

                            //更新当前显示的内容
                            Question q = list.get(corrent);

                            tv_title.setText(q.question);

                            mRadioButton[0].setText(q.answerA);
                            mRadioButton[1].setText(q.answerB);
                            mRadioButton[2].setText(q.answerC);
                            mRadioButton[3].setText(q.answerD);

                            tv_result.setText(q.explaination);
                            //显示结果
                            tv_result.setVisibility(View.VISIBLE);
                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    }).show();
                }
            }

        });

        //答案选中
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0; i < 4; i++) {
                    if (mRadioButton[i].isChecked() == true) {
                        list.get(corrent).selectedAnswer = i;
                        isCheck = true;
                        break;
                    }
                }
            }
        });
    }

    /**
     * 判断是否答题正确
     * @param list
     * @return
     */
    private List<Integer> checkAnswer(List<Question> list) {
        List<Integer> wrongList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            //判断对错
            if (list.get(i).answer != list.get(i).selectedAnswer){
                wrongList.add(i);
            }
        }
        return wrongList;
    }

    private void initFile() {
        //判断数据库是否拷贝到相应的目录下
        if (new File(DB_PATH + DB_NAME).exists() == false) {
            File dir = new File(DB_PATH);
            if (!dir.exists()) {
                dir.mkdir();
            }

            //复制文件
            try {
                InputStream is = getBaseContext().getAssets().open(DB_NAME);
                OutputStream os = new FileOutputStream(DB_PATH + DB_NAME);

                //用来复制文件
                byte[] buffer = new byte[1024];
                //保存已经复制的长度
                int length;

                //开始复制
                while ((length = is.read(buffer)) > 0) {
                    os.write(buffer, 0, length);
                }

                //刷新
                os.flush();
                //关闭
                os.close();
                is.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
