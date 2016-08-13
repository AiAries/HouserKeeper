package edu.feicui.com.houserkeeper.ui;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

import edu.feicui.com.houserkeeper.R;
import edu.feicui.com.houserkeeper.adapter.ShowSoftAdapter;

public class ShowAllSoftActivity extends BaseActivity /*implements AdapterView.OnItemClickListener*/ {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_soft);
        initToolBar();
        initView();
    }

    @Override
    public void initView() {
        ListView listView = (ListView) findViewById(R.id.listview_soft);
//        listView.setOnItemClickListener(this);
        PackageManager packageManager = getPackageManager();
        List<PackageInfo> installedPackages = packageManager.getInstalledPackages(
                PackageManager.GET_UNINSTALLED_PACKAGES
        );
        //创建一个自定义适配器
        ShowSoftAdapter adapter = new ShowSoftAdapter(installedPackages,this);
        listView.setAdapter(adapter);
    }

//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//        //获取条目数据的包名
//        PackageInfo packageInfo = (PackageInfo) parent.getItemAtPosition(position);
//
//        //当条目点击的时候触发次方法
//        Intent intent = new Intent(Intent.ACTION_DELETE);
//        intent.setData(Uri.parse("package:" + packageInfo.packageName));
//        startActivity(intent);
//    }
}
