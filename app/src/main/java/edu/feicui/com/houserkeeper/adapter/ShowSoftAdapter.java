package edu.feicui.com.houserkeeper.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import edu.feicui.com.houserkeeper.R;

/**
 * Created by Administrator on 2016/8/12 0012.
 */
public class ShowSoftAdapter extends MyBaseAdapter<PackageInfo> {

    private final PackageManager packageManager;
    private Context context;
    //存放当前条目的checkbox是否选中
    HashMap<Integer,Boolean> ischecks = new HashMap<>();

    public ShowSoftAdapter(@NonNull List<PackageInfo> data, @NonNull Context context) {
        super(data, context);
        this.context = context;
        packageManager = context.getPackageManager();
    }

    @Override
    public View MyGetView(final int position, View convertView, final ViewGroup parent) {

//        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_soft, null);
//        }
        final PackageInfo packageInfo = getData().get(position);
        int versionCode = packageInfo.versionCode;//版本号
        //String versionName = packageInfo.versionName;//版本名
        final String packageName = packageInfo.packageName;//包名
        CharSequence label = packageInfo.applicationInfo.loadLabel(packageManager);//应用程序的名字
        Drawable drawable = packageInfo.applicationInfo.loadIcon(packageManager);//应用程序的图标

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //当条目点击的时候触发次方法
                Intent intent = new Intent(Intent.ACTION_DELETE);
                intent.setData(Uri.parse("package:" + packageName));
                context.startActivity(intent);
            }
        });

        TextView tv_application_name = (TextView) convertView.findViewById(R.id.tv_application_name);
        tv_application_name.setText(label);

        ImageView iv = (ImageView) convertView.findViewById(R.id.iv);
        iv.setBackgroundDrawable(drawable);

        TextView tv_code = (TextView) convertView.findViewById(R.id.tv_code);
        //String.format();
        String vc = String.format("%s", versionCode);
        tv_code.setText(vc);

        TextView tv_package_name = (TextView) convertView.findViewById(R.id.tv_package_name);
        tv_package_name.setText(packageName);

        final CheckBox box =  (CheckBox) convertView.findViewById(R.id.checkbox);
        //给checkbox设置是否选中状态
        Boolean isCheck = ischecks.get(position);
        box.setChecked(isCheck==null?false:isCheck);
        //给checkbox设置监听
        box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                box.setChecked(isChecked);
                ischecks.put(position, isChecked);
            }
        });
        return convertView;
    }
}
