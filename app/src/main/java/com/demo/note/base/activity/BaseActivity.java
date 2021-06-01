package com.demo.note.base.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.demo.note.R;

/**
 * @author haoyupeng
 * @description：
 * @time 2021/5/19
 */

public class BaseActivity extends AppCompatActivity {
    protected TextView mBackBtn;
    protected TextView mRightBtn;
    protected TextView mTitleView;
    protected RelativeLayout mTopBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        mTopBar = findViewById(R.id.title_bar);
        mBackBtn = findViewById(R.id.tv_back);
        mRightBtn = findViewById(R.id.tv_right_btn);
        mTitleView = findViewById(R.id.tv_center_title);
    }

    public void disableLeft() {
        mBackBtn.setVisibility(View.INVISIBLE);
    }

    public void enableLeft() {
        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    protected void setTitle(String title) {
        mTitleView.setText(title);
    }

    protected void setRightBtnText(String text, View.OnClickListener clickListener) {
        mRightBtn.setText(text);
        mRightBtn.setBackground(null);
        mRightBtn.setOnClickListener(clickListener);
    }

    protected void setRightBtn(View.OnClickListener clickListener) {
        mRightBtn.setOnClickListener(clickListener);
    }
}
