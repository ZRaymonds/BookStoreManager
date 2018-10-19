package com.app.bookstoremanager.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import com.app.bookstoremanager.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;


@ContentView(R.layout.activity_setting)
public class SettingActivity extends AppCompatActivity  {

    @ViewInject(R.id.RLCheckSetting)
    RelativeLayout RLCheckSetting;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
    }

    @Event(R.id.RLCheckSetting)
    private void onClick(View v){
        switch (v.getId()){
            case R.id.RLCheckSetting:
                break;
        }
    }

}
