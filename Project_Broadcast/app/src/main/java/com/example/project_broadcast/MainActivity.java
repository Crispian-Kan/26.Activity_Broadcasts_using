package com.example.project_broadcast;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.demo.R;


public class MainActivity extends Activity
{
    private MyBroadcastReceiver mMyReceiver; //自訂一個繼承 BroadcastReceiver 的類別

    private Button BtnRegReceiver, //註冊廣播
            BtnUnregReceiver, //撤銷廣播
            BtnSendBroadcast;//發送廣播

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BtnRegReceiver = (Button)findViewById(R.id.btnRegReceiver);
        BtnUnregReceiver = (Button)findViewById(R.id.btnUnregReceiver);
        BtnSendBroadcast = (Button)findViewById(R.id.btnSendBroadcast);

        BtnRegReceiver.setOnClickListener(btnRegReceiverOnClickLis);
        BtnUnregReceiver.setOnClickListener(btnUnregReceiverOnClickLis);
        BtnSendBroadcast.setOnClickListener(btnSendBroadcastOnClickLis);
    }

    private Button.OnClickListener btnRegReceiverOnClickLis
            = new Button.OnClickListener()
    {
        public void onClick(View v)
        {
            IntentFilter itFilter = new IntentFilter("tw.android.ACTION_01");
            //Intent itFilter =new IntentFilter();
            //itFilter.addAction("tw.android.ACTION_01"); 一樣用法

            mMyReceiver = new MyBroadcastReceiver();
            registerReceiver(mMyReceiver, itFilter); //註冊廣播接收器
        }
    };

    private Button.OnClickListener btnUnregReceiverOnClickLis
            = new Button.OnClickListener()
    {
        public void onClick(View v)
        {
            unregisterReceiver(mMyReceiver); //撤銷廣播接收器
        }
    };

    private Button.OnClickListener btnSendBroadcastOnClickLis
            = new Button.OnClickListener()
    {
        public void onClick(View v)
        {
            Intent it = new Intent("tw.android.ACTION_01"); //設定廣播識別碼
            it.putExtra("sender_name", "廣播中"); //設定廣播夾帶參數
            sendBroadcast(it); //發送廣播訊息
        }
    };
}