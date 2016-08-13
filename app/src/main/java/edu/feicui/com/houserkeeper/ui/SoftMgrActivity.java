package edu.feicui.com.houserkeeper.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import edu.feicui.com.houserkeeper.R;
import edu.feicui.com.houserkeeper.biz.MemoryManager;

public class SoftMgrActivity extends BaseActivity implements View.OnClickListener {

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
        long outSDCardSize = MemoryManager.getPhoneOutSDCardSize(this);
        pb_phone_extral_space.setMax((int) outSDCardSize);
        //获取手机外部sd卡可以用存储空间
        long outSDCardFreeSize = MemoryManager.getPhoneOutSDCardFreeSize(this);
        pb_phone_extral_space.setProgress((int) (outSDCardSize-outSDCardFreeSize));

        long selfSDCardSize = MemoryManager.getPhoneSelfSDCardSize();
        String total = String.format("%d%%dM", selfSDCardSize,1024);
        long selfSDCardFreeSize = MemoryManager.getPhoneSelfSDCardFreeSize();
        pb_phone_internal_space.setMax((int) selfSDCardSize);
        int progress = (int) (selfSDCardSize - selfSDCardFreeSize);
        pb_phone_internal_space.setProgress(progress);

       TextView tv_internal = (TextView) findViewById(R.id.tv_internal);
        tv_internal.setText(total+"/"+selfSDCardSize);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_all_soft:
                myStartActivity(ShowAllSoftActivity.class);
                break;
            case R.id.btn_user_soft:
                break;
            case R.id.btn_sys_soft:
                break;
        }
    }
}
