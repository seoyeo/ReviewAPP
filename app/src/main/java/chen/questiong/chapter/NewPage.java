package chen.questiong.chapter;

import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import chen.questiong.R;
import chen.questiong.fragment.DianZi;
import chen.questiong.fragment.Micrsoft;
import chen.questiong.fragment.YuWen;

public class NewPage extends FragmentActivity {
    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mFragments = new ArrayList<Fragment>();

    /**
     * 顶部三个LinearLayout
     */
    private LinearLayout mTabLiaotian;
    private LinearLayout mTabFaxian;
    private LinearLayout mTabTongxunlun;
    /**
     * 顶部的三个TextView
     */
    private TextView yuwen;
    private TextView dianzi;
    private TextView weixin;

    /**
     * 分别为每个TabIndicator创建一个BadgeView
     */
    /*private BadgeView mBadgeViewforLiaotian;
    private BadgeView mBadgeViewforFaxian;
    private BadgeView mBadgeViewforTongxunlu;*/

    /**
     * Tab的那个引导线
     */
    private ImageView mTabLine;
    /**
     * ViewPager的当前选中页
     */
    private int currentIndex;
    /**
     * 屏幕的宽度
     */
    private int screenWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_page);
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
        initView();
        initTabLine();
        setListener();

        /**
         * 初始化Adapter
         */
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Override
            public Fragment getItem(int arg0) {
                return mFragments.get(arg0);
            }
        };

        mViewPager.setAdapter(mAdapter);

        /**
         * 设置监听
         */
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onPageSelected(int position) {
                // 重置所有TextView的字体颜色
                resetTextView();
                switch (position) {
                    case 0: //聊天
                        yuwen.setTextColor(getResources().getColor(
                                R.color.green));
                        mTabLiaotian.setBackground(getResources().getDrawable(R.drawable.back));
                        mTabFaxian.setBackground(null);
                        mTabTongxunlun.setBackground(null);
                    break;
                    case 1:   //发现
                        dianzi.setTextColor(getResources().getColor(R.color.green));
                        mTabFaxian.setBackground(getResources().getDrawable(R.drawable.back));
                        mTabLiaotian.setBackground(null);
                        mTabTongxunlun.setBackground(null);
                        break;
                    case 2: //通讯录
                        weixin.setTextColor(getResources().getColor(R.color.green));
                        mTabTongxunlun.setBackground(getResources().getDrawable(R.drawable.back));
                        mTabFaxian.setBackground(null);
                        mTabLiaotian.setBackground(null);
                        break;
                }

                currentIndex = position;    //设置当前页面的值
            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
                /**
                 * 利用position和currentIndex判断用户的操作是哪一页往哪一页滑动
                 * 然后改变根据positionOffset动态改变TabLine的leftMargin
                 */
                if (currentIndex == 0 && position == 0)// 0->1
                {
                    LinearLayout.LayoutParams lp = (android.widget.LinearLayout.LayoutParams) mTabLine
                            .getLayoutParams();
                    lp.leftMargin = (int) (positionOffset
                            * (screenWidth * 1.0 / 3) + currentIndex
                            * (screenWidth / 3));
                    mTabLine.setLayoutParams(lp);

                } else if (currentIndex == 1 && position == 0) // 1->0
                {
                    LinearLayout.LayoutParams lp = (android.widget.LinearLayout.LayoutParams) mTabLine
                            .getLayoutParams();
                    lp.leftMargin = (int) (-(1 - positionOffset)
                            * (screenWidth * 1.0 / 3) + currentIndex
                            * (screenWidth / 3));
                    mTabLine.setLayoutParams(lp);

                } else if (currentIndex == 1 && position == 1) // 1->2
                {
                    LinearLayout.LayoutParams lp = (android.widget.LinearLayout.LayoutParams) mTabLine
                            .getLayoutParams();
                    lp.leftMargin = (int) (positionOffset
                            * (screenWidth * 1.0 / 3) + currentIndex
                            * (screenWidth / 3));
                    mTabLine.setLayoutParams(lp);
                } else if (currentIndex == 2 && position == 1) // 2->1
                {
                    LinearLayout.LayoutParams lp = (android.widget.LinearLayout.LayoutParams) mTabLine
                            .getLayoutParams();
                    lp.leftMargin = (int) (-(1 - positionOffset)
                            * (screenWidth * 1.0 / 3) + currentIndex
                            * (screenWidth / 3));
                    mTabLine.setLayoutParams(lp);

                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        mViewPager.setCurrentItem(1); // 默认选中1

    }

    /**
     * 系统返回按钮处理
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            new AlertDialog.Builder(this).setMessage("是否退出").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            }).show();
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 根据屏幕的宽度，初始化引导线的宽度
     */
    private void initTabLine() {
        mTabLine = (ImageView) findViewById(R.id.id_tab_line);
        DisplayMetrics outMetrics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay()
                .getMetrics(outMetrics);
        screenWidth = outMetrics.widthPixels;
        LinearLayout.LayoutParams lp = (android.widget.LinearLayout.LayoutParams) mTabLine
                .getLayoutParams();
        lp.width = screenWidth / 3;
        mTabLine.setLayoutParams(lp);
    }

    /**
     * 重置颜色
     */
    protected void resetTextView() {
        yuwen.setTextColor(getResources().getColor(R.color.black));
        dianzi.setTextColor(getResources().getColor(R.color.black));
        weixin.setTextColor(getResources().getColor(R.color.black));
    }

    /**
     * 初始化控件，初始化Fragment
     */
    private void initView() {
        mTabLiaotian = (LinearLayout) findViewById(R.id.id_tab_liaotian_ly);
        mTabFaxian = (LinearLayout) findViewById(R.id.id_tab_faxian_ly);
        mTabTongxunlun = (LinearLayout) findViewById(R.id.id_tab_tongxunlu_ly);
        // 聊天
        yuwen = (TextView) findViewById(R.id.id_liaotian);
        // 发现
        dianzi = (TextView) findViewById(R.id.id_faxian);
        // 通讯录
        weixin = (TextView) findViewById(R.id.id_tongxunlu);
        DianZi tab01 = new DianZi();
        Micrsoft tab02 = new Micrsoft();
        YuWen tab03 = new YuWen();
        mFragments.add(tab01);   // 存 fragment 的集合
        mFragments.add(tab02);
        mFragments.add(tab03);

    }

    // 监听点击 上面的条目的 ,切换页面的事件
    private void setListener() {

        mTabLiaotian.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                mViewPager.setCurrentItem(0);
            }
        });
        mTabFaxian.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                mViewPager.setCurrentItem(1);
            }
        });
        mTabTongxunlun.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                mViewPager.setCurrentItem(2);
            }
        });

    }
}
