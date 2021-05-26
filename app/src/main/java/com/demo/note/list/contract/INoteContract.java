package com.demo.note.list.contract;

import com.demo.note.bean.NoteModel;

import java.util.List;

/**
 * @author haoyupeng@bytedance.com
 * @description：
 * @time 2021/5/19
 */

public interface INoteContract {

    interface View {
        void getNoteListSuccess(List<NoteModel> list);

        void delete(int pos);

        void showFailureView(String msg);

        void showLoading();

        void hideLoading();
    }

    interface Presenter {
        void getNoteList();

        void deleteNote(String id, int pos);

    }
}
