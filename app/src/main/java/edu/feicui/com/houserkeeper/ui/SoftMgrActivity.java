package edu.feicui.com.houserkeeper.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import edu.feicui.com.houserkeeper.R;
import edu.feicui.com.houserkeeper.biz.MemoryManager;

public class SoftMgrActivity extends BaseActivity implements View.OnClickListener {

    public static final int  USER_SOFT = 0;
    public static final int  SYSTEM_SOFT = 1;
    public static final int  ALL_SOFT = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft_mgr);
        initToolBar();
        initView();
    }

    @Override
    public void initView() {
        findViewById(R.id.btn_all_soft).setOnClickListener(this);
        findViewById(R.id.btn_user_soft).setOnClickListener(this);
        findViewById(R.id.btn_sys_soft).setOnClickListener(this);
        ProgressBar pb_phone_extral_space = (ProgressBar) findViewById(R.id.pb_phone_extral_space);
        ProgressBar pb_phone_internal_space = (ProgressBar) findViewById(R.id.pb_phone_internal_space);
        //获取外部sd卡存储空间的总值
        long outSDCardSize = MemoryManager.getPhoneOutSDCardSize(this)/1024/1024;
        //给progressBar设置最大值
        pb_phone_extral_space.setMax((int) outSDCardSize);
        //获取手机外部sd卡可以用存储空间  byte
        long outSDCardFreeSize = MemoryManager.getPhoneOutSDCardFreeSize(this)/1024/1024;
        pb_phone_extral_space.setProgress((int) (outSDCardSize-outSDCardFreeSize));
        TextView tv_out = (TextView) findViewById(R.id.tv_out);
        tv_out.setText(outSDCardSize-outSDCardFreeSize+"/"+outSDCardSize+"M");


        //获取内置sd卡的数据
        long selfSDCardSize = MemoryManager.getPhoneSelfSDCardSize()/1024/1024;
        long selfSDCardFreeSize = MemoryManager.getPhoneSelfSDCardFreeSize()/1024/1024;

        pb_phone_internal_space.setMax((int) selfSDCardSize);
        int progress = (int) (selfSDCardSize - selfSDCardFreeSize);
        pb_phone_internal_space.setProgress(progress);
        TextView tv_internal = (TextView) findViewById(R.id.tv_internal);
        tv_internal.setText(progress+"/"+selfSDCardSize+"M");

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId())
        {
            case R.id.btn_all_soft:
                intent.setClass(this,ShowSoftActivity.class);
                intent.putExtra("flag",ALL_SOFT);
                break;
            case R.id.btn_user_soft:
                intent.setClass(this,ShowSoftActivity.class);
                intent.putExtra("flag",USER_SOFT);
                break;
            case R.id.btn_sys_soft:
                intent.setClass(this,ShowSoftActivity.class);
                intent.putExtra("flag",SYSTEM_SOFT);
                break;
        }
        startActivity(intent);
    }
}
