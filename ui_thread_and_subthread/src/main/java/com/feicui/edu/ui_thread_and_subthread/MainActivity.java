package com.feicui.edu.ui_thread_and_subthread;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        new Thread(new MyRunnable()).start();
//        new MyDownloadTask().execute();
    }

    public void click(View view) {
        view.setEnabled(false);
        new MyDownloadTask().execute();
//        try {
//            Thread.sleep(10000);
//            Toast.makeText(MainActivity.this, "下载完成", Toast.LENGTH_SHORT).show();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    }

    private class MyRunnable implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(10000);
                Toast.makeText(MainActivity.this, "下载完成", Toast.LENGTH_SHORT).show();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void showProgress() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMax(100);
        progressDialog.setTitle("开始下载中");
        progressDialog.setIcon(R.mipmap.ic_launcher);
        progressDialog.setCanceledOnTouchOutside(true);
        progressDialog.show();
    }

    class MyDownloadTask extends AsyncTask<Void, Integer, String> {

        @Override
        protected void onPreExecute() {
            showProgress();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            for (int i = 1; i < 11; i++) {
                try {
                    Thread.sleep(1000);
                    progressDialog.setProgress(10*i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "success";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
            Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
        }
    }
}
