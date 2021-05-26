package com.demo.note.create;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.demo.note.R;
import com.demo.note.base.activity.BaseActivity;

/**
 * @author haoyupeng@bytedance.com
 * @description：
 * @time 2021/5/20
 */

public class CreateNoteActivity extends BaseActivity {

    private NoteCreatePage mFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        mFragment = new NoteCreatePage();
        mFragment.setArguments(intent.getExtras());

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, mFragment)
                .commitAllowingStateLoss();
        enableLeft();
        setTitle("新建笔记");
        setRightBtnText("完成", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFragment.save();
            }
        });
    }


}
