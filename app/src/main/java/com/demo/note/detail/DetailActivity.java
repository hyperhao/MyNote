package com.demo.note.detail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.demo.note.R;
import com.demo.note.base.activity.BaseActivity;

/**
 * @author haoyupeng@bytedance.com
 * @description：
 * @time 2021/5/20
 */

public class DetailActivity extends BaseActivity {

    private NoteDetailPage mFragment = new NoteDetailPage();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        mFragment.setArguments(intent.getExtras());


        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, mFragment)
                .commitAllowingStateLoss();
        setTitle("日记详情");
        enableLeft();

        setRightBtnText("保存",new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFragment.save();
            }
        });
    }

}
