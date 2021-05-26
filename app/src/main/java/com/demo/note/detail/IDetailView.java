package com.demo.note.detail;

import com.demo.note.bean.NoteModel;

public interface IDetailView {

    void getNoteSuccess(NoteModel noteModel);

    void saveSuccess();

    void showFailureView(String msg);

    void showLoading();

    void hideLoading();
}