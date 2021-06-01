package com.demo.note.detail;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.demo.note.R;
import com.demo.note.bean.NoteModel;

import jp.wasabeef.richeditor.RichEditor;

/**
 * @author haoyupeng
 * @description：
 * @time 2021/5/19
 */

public class NoteDetailPage extends Fragment implements IDetailView {

    private DetailRepo detailRepo;
    private TextView time;
    private TextView title;
    private RichEditor content;
    private String id;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        id = getActivity().getIntent().getExtras().getString("id");
        initView(view);
        initData(id);
    }

    private void initData(String id) {
        detailRepo = new DetailRepo(this);
        detailRepo.get(id);
    }

    private void initView(View rootView) {
        time = rootView.findViewById(R.id.tv_time);
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
                Toast.makeText(getContext(), "保存成功", Toast.LENGTH_SHORT).show();
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
        detailRepo.updateNote(id, title.getText().toString(), content.getHtml());
    }

}
