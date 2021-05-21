package com.demo.note.list;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.demo.note.R;
import com.demo.note.base.activity.BaseActivity;
import com.demo.note.list.view.NoteFragment;
import com.demo.note.create.NewNoteActivity;

public class HomeActivity extends BaseActivity {
    private Fragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        mFragment = new NoteFragment();
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
                Intent newIntent = new Intent(HomeActivity.this, NewNoteActivity.class);
                startActivity(newIntent);
            }
        });
    }

}