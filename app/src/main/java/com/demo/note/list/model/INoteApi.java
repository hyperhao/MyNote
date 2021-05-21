package com.demo.note.list.model;

import com.demo.note.bean.NoteBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author haoyupeng@bytedance.com
 * @description：
 * @time 2021/5/19
 */

public interface INoteApi {

    @GET("list")
    Observable<List<NoteBean>> getNoteList();

    @GET("read")
    Observable<NoteBean> readNote(@Query("id") String id);

}
