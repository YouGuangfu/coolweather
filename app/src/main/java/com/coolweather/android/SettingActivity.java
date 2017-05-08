package com.coolweather.android;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

public class SettingActivity extends AppCompatActivity {
    private boolean checked;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Button back = (Button) findViewById(R.id.back_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this,WeatherActivity.class);
                startActivity(intent);
                onBackPressed();//直接返回到weather页面 修复已知多次按返回键的bug，对应活动的启动模式改成singTask
            }
        });

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        boolean nowChecked = prefs.getBoolean("which",checked);

        final Switch switcher = (Switch) findViewById(R.id.checkbox_button);

        switcher.setChecked(nowChecked);
        switcher.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {

                    Intent intent = new Intent(SettingActivity.this,AutoUpdateService.class);
                    startService(intent);
                    Toast.makeText(SettingActivity.this, "已开启自动更新", Toast.LENGTH_SHORT).show();
                    checked = true;
                } else {
                    checked = false;

                    Intent intent = new Intent(SettingActivity.this,AutoUpdateService.class);
                    stopService(intent);

                    Toast.makeText(SettingActivity.this, "已停止自动更新", Toast.LENGTH_SHORT).show();

                }
                SharedPreferences.Editor editor =PreferenceManager.getDefaultSharedPreferences(SettingActivity.this).edit();
                editor.putBoolean("which",checked);
                editor.apply();
            }

        });

    }

}
