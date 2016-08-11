package com.example.jsonarry.notificationdemo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int numMessages;
    boolean flag;
    private NotificationManager manager;
    private PendingIntent pi;
    int requestCode ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    public void update(View view)
    {
//        PendingIntent pendingIntent = PendingIntent.getActivity()
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
// Sets an ID for the notification, so it can be updated
        int notifyID = 1;
        android.support.v4.app.NotificationCompat.Builder mNotifyBuilder =
                new NotificationCompat.Builder(this)
                .setContentTitle("New Message")
                .setContentText("You've received new messages.")
                .setSmallIcon(R.mipmap.ic_launcher);
//        numMessages = 0;
// Start of a loop that processes data and then notifies the user
//        ...
        mNotifyBuilder.setContentText("currentText"+numMessages)
                .setNumber(++numMessages);
        // Because the ID remains unchanged, the existing notification is
        // updated.
        mNotificationManager.notify(
                notifyID,
                mNotifyBuilder.build());
    }

    public void notify1(View view) {
        //发送普通通知
        //创建通知的构造者
        android.support.v4.app.NotificationCompat.Builder builder =
         new  android.support.v4.app.NotificationCompat.Builder(this);
        //设置必要的内容
        builder.setTicker("您有一条新消息")
                .setContentTitle("奥运赛事")
                .setContentText("中国得到九块金牌")
                .setSmallIcon(R.mipmap.ic_launcher);

        //设置震动属性和声音
        //builder.setDefaults(Notification.DEFAULT_VIBRATE|Notification.DEFAULT_SOUND);

        //生成通知对象
        Notification notification = builder.build();
        //发送通知
        //通知都需要有唯一的id值
        int notifyId = 110;
        manager.notify(notifyId,notification);
    }

    public void notify2(View view) {
        android.support.v4.app.NotificationCompat.Builder builder =
                new  android.support.v4.app.NotificationCompat.Builder(this);
        String number ;
        if (flag) {
            number = "tel:" + "1868888888";
        } else {
            number = "tel:" + "110";
        }
        flag =!flag;
        //设置必要的内容
        builder.setTicker("您有一条新消息")
                .setContentTitle("未接电话")
                .setContentText(number)
                .setSmallIcon(R.mipmap.ic_launcher);

        //设置当点击通知的时候可以自动取消
        //builder.setAutoCancel(true);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(number));
        pi = PendingIntent.getActivity(
                this,
                requestCode++,//请求码
                intent,
                //PendingIntent.FLAG_NO_CREATE//不创建Pendingintent对象，返回null
                PendingIntent.FLAG_IMMUTABLE//6.0出来的
                //PendingIntent.FLAG_UPDATE_CURRENT//如果通知pendingintent存在带更新intent数据
                //PendingIntent.FLAG_CANCEL_CURRENT//如果通知pendingintent存在，替换之前的
                //PendingIntent.FLAG_ONE_SHOT//通知只对第一次点击有效
        );
//        try {
//            pi.send(this,0,intent);//等价于点击通知条目
//        } catch (PendingIntent.CanceledException e) {
//            e.printStackTrace();
//        }
//        Intent intent = new Intent(this,Main2Activity.class);
        //PendingIntent.FLAG_NO_CREATE//不创建Pendingintent对象，返回null
// PendingIntent.FLAG_IMMUTABLE//6.0出来的
//PendingIntent.FLAG_UPDATE_CURRENT//如果通知pendingintent存在带更新intent数据
//
//PendingIntent.FLAG_ONE_SHOT//通知只对第一次点击有效

        Toast.makeText(MainActivity.this, pi.toString(), Toast.LENGTH_SHORT).show();
        builder.setContentIntent(pi);

        //生成通知对象
        Notification notification = builder.build();
        //发送通知
        //通知都需要有唯一的id值
        int notifyId = 110;
        manager.notify(notifyId,notification);
    }
}
