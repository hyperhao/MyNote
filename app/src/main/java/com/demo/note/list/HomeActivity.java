package com.demo.note.list;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.demo.note.R;
import com.demo.note.base.activity.BaseActivity;
import com.demo.note.list.view.NoteListPage;
import com.demo.note.create.CreateNoteActivity;

public class HomeActivity extends BaseActivity {
    private Fragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        mFragment = new NoteListPage();
        mFragment.setArguments(intent.getExtras());
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, mFragment)
                .commitAllowingStateLoss();
        setTitle("日记列表");
        disableLeft();

        setRightBtn(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(HomeActivity.this, CreateNoteActivity.class);
                startActivity(newIntent);
            }
        });
    }

}