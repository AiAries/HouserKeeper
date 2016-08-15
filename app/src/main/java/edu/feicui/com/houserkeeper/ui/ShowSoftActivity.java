package edu.feicui.com.houserkeeper.ui;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.feicui.com.houserkeeper.R;
import edu.feicui.com.houserkeeper.adapter.ShowSoftAdapter;

public class ShowSoftActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user_soft);
        initToolBar();
        initView();
    }

    @Override
    public void initView() {
        //初始化listview控件
        ListView userSoft = (ListView) findViewById(R.id.listview_soft);
        //先准备适配器需要的数据
        List<PackageInfo> allData = this.getPackageManager().getInstalledPackages(
                PackageManager.GET_UNINSTALLED_PACKAGES
        );//获取所有已安装的包
        //从allData中筛选处用户的安装的包
        List<PackageInfo> data =  getUserInstalledPackage(allData);

        ShowSoftAdapter adapter = new ShowSoftAdapter(data,this);
        userSoft.setAdapter(adapter);
    }

    private List<PackageInfo> getUserInstalledPackage(List<PackageInfo> allData) {

        Intent intent = getIntent();
        int flag = intent.getIntExtra("flag", -1);
        if (flag==SoftMgrActivity.ALL_SOFT)
        {
            return allData;
        }

        List<PackageInfo> data = new ArrayList<>();
        Iterator<PackageInfo> iterator = allData.iterator();
        while (iterator.hasNext()) {
            //取出迭代器中当前指针指向的元素
            PackageInfo packageInfo = iterator.next();
            //判断该应用是系统还是用户安装的标识
            int flags = packageInfo.applicationInfo.flags;

            switch (flag) {
                case SoftMgrActivity.SYSTEM_SOFT:
                    if ((flags & ApplicationInfo.FLAG_SYSTEM) == 1) {
                        //是系统应用.靠谱
                        data.add(packageInfo);
                    }
                    break;
                case SoftMgrActivity.USER_SOFT:
                    if ((flags & ApplicationInfo.FLAG_SYSTEM) != 1) {
                        //用户应用
                        data.add(packageInfo);
                    }
                    break;
            }


        }

        return data;
    }
}
