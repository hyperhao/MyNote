package com.demo.note.list.model;

import com.demo.note.bean.NoteModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author haoyupeng
 * @description：
 * @time 2021/5/19
 */

public interface INoteApi {

    @GET("list")
    Observable<List<NoteModel>> getNoteList();

    @GET("read")
    Observable<NoteModel> readNote(@Query("id") String id);

}
