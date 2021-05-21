package com.demo.note.detail;

import com.demo.note.bean.NoteBean;

public interface IDetailView {

    void getNoteSuccess(NoteBean noteBean);

    void saveSuccess();

    void showFailureView(String msg);

    void showLoading();

    void hideLoading();
}