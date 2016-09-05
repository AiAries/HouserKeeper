package com.feicui.edu.highpart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.feicui.edu.highpart.bean.News;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/9/5 0005.
 */
public class ListViewAdapter extends BaseAdapter {

    private  ArrayList<News> newses;
    private  Context context;

    public ListViewAdapter(ArrayList<News> newses, Context context) {

        this.newses = newses;
        this.context = context;
    }

    @Override
    public int getCount() {
        return newses.size();
    }

    @Override
    public Object getItem(int position) {
        return newses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_list_news, null);
        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView1);
        TextView tv1 = (TextView) itemView.findViewById(R.id.textView1);
        TextView tv2 = (TextView) itemView.findViewById(R.id.textView2);

        tv1.setText(newses.get(position).getTitle());
        tv2.setText(newses.get(position).getSummary());

        String iconPath = newses.get(position).getIcon();
        iconPath = iconPath.replace("localhost", "192.168.2.35");
        Glide.with(context)
                .load(iconPath)
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .crossFade()
                .into(imageView);
        return itemView;
    }

    public void setNewses(ArrayList<News> newses) {
        this.newses = newses;
    }
}
