package edu.feicui.com.houserkeeper.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import edu.feicui.com.houserkeeper.R;
import edu.feicui.com.houserkeeper.fragment.IntroduceFragment;

public class IntroduceActivity extends BaseActivity {

    public int[] resIds = {R.mipmap.adware_style_applist,
            R.mipmap.adware_style_banner,
            R.mipmap.adware_style_creditswall};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce);
        SharedPreferences jump = getSharedPreferences("jump", Context.MODE_PRIVATE);
        boolean isFirst = jump.getBoolean("isFirst", true);
        if (isFirst) {
            jump.edit().putBoolean("isFirst", false).apply();
            initView();
        } else {
            jump(null);
        }

    }

    public void jump(View view) {
        //跳转页面
        myStartActivity(WelcomeActivity.class);
        finish();
    }

    @Override
    public void initView() {
        //2.
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        //3.准备适配器 自定义适配器继承FragmentStatePagerAdapter
        MyFragmentStateAdapter adapter = new MyFragmentStateAdapter(getSupportFragmentManager());
        //4.绑定适配器
        viewPager.setAdapter(adapter);

        final LinearLayout tv_container = (LinearLayout) findViewById(R.id.tv_container);
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                /*
                positon:是当前看到页面的位置
                positionOffset：Value from [0, 1) offset from the page at position.
                 */
                if (position == resIds.length - 1) {
                    //滑动到最后一个页面时，开启一个计时器，当时间达到3秒自动跳到首页面
                    tv_container.postDelayed(
                            new Runnable() {
                                @Override
                                public void run() {
                                    myStartActivity(WelcomeActivity.class);
                                }
                            },
                            3000
                    );
                }
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                //获取子控件的数量
                int count = tv_container.getChildCount();
                for (int i = 0; i < count; i++) {
                    //获取子控件对象
                    View v = tv_container.getChildAt(i);
                    v.setBackgroundDrawable(getResources().getDrawable(R.mipmap.adware_style_default));
                }
                View v = tv_container.getChildAt(position);
                v.setBackgroundDrawable(getResources().getDrawable(R.mipmap.adware_style_selected));
            }
        });
    }

    //内部类的适配器
    class MyFragmentStateAdapter extends FragmentStatePagerAdapter {

        public MyFragmentStateAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            //position 是从0到getcount-1
            IntroduceFragment fragment = new IntroduceFragment();
            //fragment 展示页面内容
            //怎么给fragment传递参数
            Bundle bundle = new Bundle();
            bundle.putInt("i", position);
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public int getCount() {
            //滑动页面的数量
            return resIds.length;
        }
    }
}
