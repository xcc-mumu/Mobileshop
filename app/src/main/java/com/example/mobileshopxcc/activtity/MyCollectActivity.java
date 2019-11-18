package com.example.mobileshopxcc.activtity;


import android.widget.TextView;

import com.example.mobileshopxcc.Common.BaseActivity;
import com.example.mobileshopxcc.R;

import butterknife.BindView;
import butterknife.OnClick;

public class MyCollectActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;

    @Override
    public int getContentViewId() {
        return R.layout.activity_my_collec;
    }

    @Override
    protected void initView() {
        super.initView();
        tvTitle.setText("我的收藏");
    }

    @OnClick(R.id.iv_back)
    void close(){
        finish();
    }
}