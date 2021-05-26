package com.demo.note.detail;

import com.demo.note.bean.NoteModel;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author haoyupeng@bytedance.com
 * @description：
 * @time 2021/5/20
 */

public class DetailRepo {
    private IDetailView iDetailView;

    public DetailRepo(IDetailView iDetailView) {
        this.iDetailView = iDetailView;
    }

    public void get(String id) {
        OkHttpClient client = new OkHttpClient.Builder().callTimeout(3000L, TimeUnit.MILLISECONDS).build();
        Request request = new Request.Builder()
                .url("http://10.76.104.58:8080/read?id=" + id)
                .get()
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.body() != null) {
                    NoteModel noteModel = new Gson().fromJson(response.body().string(), NoteModel.class);
                    iDetailView.getNoteSuccess(noteModel);
                }
            }
        });
    }

    public void createNote(String title, String content) {
        RequestBody body = new FormBody.Builder()
                .add("note_title", title)
                .add("note_content", content)
                .build();
        create(body);
    }

    public void updateNote(String noteId, String title, String content) {
        RequestBody body = new FormBody.Builder()
                .add("note_id", noteId)
                .add("note_title", title)
                .add("note_content", content)
                .build();
        update(body);
    }

    public void create(RequestBody body) {
        OkHttpClient client = new OkHttpClient.Builder().callTimeout(3000L, TimeUnit.MILLISECONDS).build();
        Request request = new Request.Builder()
                .url("http://10.76.104.58:8080/create")
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                iDetailView.saveSuccess();
            }
        });
    }

    public void update(RequestBody body) {
        OkHttpClient client = new OkHttpClient.Builder().callTimeout(3000L, TimeUnit.MILLISECONDS).build();
        Request request = new Request.Builder()
                .url("http://10.76.104.58:8080/update")
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                iDetailView.saveSuccess();
            }
        });
    }
}
