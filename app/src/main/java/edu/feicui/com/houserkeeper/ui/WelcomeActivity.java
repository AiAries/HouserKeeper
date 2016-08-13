package edu.feicui.com.houserkeeper.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.widget.ImageView;

import edu.feicui.com.houserkeeper.R;

public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    public void initView() {

//        ImageView imageView = new ImageView(this);
//        ViewGroup.LayoutParams params = imageView.getLayoutParams();
//        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
//        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
//        imageView.setLayoutParams(params);
//        imageView.setBackgroundResource(R.mipmap.androidy);
        setContentView(R.layout.activity_welcome);
        ImageView iv = (ImageView) findViewById(R.id.iv);
        //属性动画-透明度动画
        ObjectAnimator animator = ObjectAnimator.ofFloat(
                iv,
                "alpha",
                0.3f,
                1.0f
        );
        animator.setDuration(2000);
        //平移动画
        ObjectAnimator translationAnimation = ObjectAnimator.ofFloat(
                this,
                "translate",
                0,
                -1000
        );
        translationAnimation.setDuration(2000);

        //设置监听器
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                //当动画执行完毕，进行一个跳转到主界面
                myStartActivity(MainActivity.class);
                //跳转之后，关闭当前界面
                finish();
            }
        });
//        AnimatorSet set = new AnimatorSet();
//        set.playSequentially(translationAnimation);
//        set.setTarget(iv);
//        set.setDuration(2000);
//        set.start();
        animator.start();
    }
}
