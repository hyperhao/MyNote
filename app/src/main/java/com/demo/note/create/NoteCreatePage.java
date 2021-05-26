package com.demo.note.create;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.demo.note.R;
import com.demo.note.detail.DetailRepo;
import com.demo.note.detail.IDetailView;
import com.demo.note.bean.NoteModel;

import jp.wasabeef.richeditor.RichEditor;

/**
 * @author haoyupeng@bytedance.com
 * @description：
 * @time 2021/5/19
 */

public class NoteCreatePage extends Fragment implements IDetailView {

    private DetailRepo detailRepo;
    private TextView time;
    private TextView title;
    private RichEditor content;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
    }

    private void initData() {
        detailRepo = new DetailRepo(this);

    }

    private void initView(View rootView) {
        content = rootView.findViewById(R.id.et_new_content);
        title = rootView.findViewById(R.id.tv_title);
        content.setEditorFontColor(Color.GRAY);
        content.setEditorBackgroundColor(getResources().getColor(R.color.white_80));
        content.setPadding(10, 10, 10, 10);
        content.setFontSize(17);
        content.setOnTextChangeListener(new RichEditor.OnTextChangeListener() {
            @Override
            public void onTextChange(String text) {

            }
        });
    }


    Handler mHandler = new Handler(Looper.getMainLooper());

    public final void runOnUiThread(Runnable action) {
        mHandler.post(action);
    }

    @Override
    public void getNoteSuccess(NoteModel noteModel) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                time.setText(noteModel.timeStamp);
                title.setText(noteModel.title);
                content.setHtml(noteModel.content);
            }
        });
    }

    @Override
    public void saveSuccess() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                getActivity().finish();
                Toast.makeText(getContext(), "新建完成", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void showFailureView(String msg) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    public void save() {
        if (TextUtils.isEmpty(title.getText().toString())) {
            Toast.makeText(getContext(), "标题不可为空", Toast.LENGTH_SHORT).show();
            return;
        }
        detailRepo.createNote(title.getText().toString(), content.getHtml());
    }

}
