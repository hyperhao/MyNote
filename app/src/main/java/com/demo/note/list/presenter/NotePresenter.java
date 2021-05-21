package com.demo.note.list.presenter;

import com.demo.note.base.network.RetrofitUtil;
import com.demo.note.bean.NoteBean;
import com.demo.note.list.contract.INoteContract;
import com.demo.note.list.model.INoteApi;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author haoyupeng@bytedance.com
 * @description：
 * @time 2021/5/19
 */

public class NotePresenter implements INoteContract.Presenter {

    private INoteContract.View mView;
    private INoteApi repo = RetrofitUtil.getRetrofit().create(INoteApi.class);
    private CompositeDisposable mCompositeDisposable;

    public NotePresenter(INoteContract.View view) {
        mView = view;
    }

    //RxJava取消注册，以避免内存泄露
    public void onUnSubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
    }


    private void addSubscription(Disposable observer) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(observer);
    }


    public void delete(String id, int pos) {
        OkHttpClient client = new OkHttpClient.Builder().callTimeout(3000L, TimeUnit.MILLISECONDS).build();
        Request request = new Request.Builder()
                .url("http://10.76.104.58:8080/delete?id=" + id)
                .get()
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                mView.delete(pos);
            }
        });
    }

    @Override
    public void getNoteList() {
        repo.getNoteList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<NoteBean>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                mView.showLoading();
                addSubscription(d);
            }

            @Override
            public void onNext(@NonNull List<NoteBean> noteBeans) {
                mView.getNoteListSuccess(noteBeans);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                mView.hideLoading();
                mView.showFailureView(e.toString());
            }

            @Override
            public void onComplete() {
                mView.hideLoading();
            }
        });
    }


    @Override
    public void deleteNote(String id, int pos) {
        delete(id, pos);
    }


}
