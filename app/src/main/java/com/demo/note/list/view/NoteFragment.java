package com.demo.note.list.view;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.note.R;
import com.demo.note.list.adapter.NoteAdapter;
import com.demo.note.bean.NoteModel;
import com.demo.note.list.contract.INoteContract;
import com.demo.note.list.presenter.NotePresenter;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.List;

/**
 * @author haoyupeng@bytedance.com
 * @description：
 * @time 2021/5/19
 */

public class NoteFragment extends Fragment implements INoteContract.View {
    private NotePresenter presenter;
    private NoteAdapter adapter;
    private RecyclerView recyclerView;
    private RefreshLayout refreshLayout;
    private boolean isLoading;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new NotePresenter(this);
        adapter = new NoteAdapter(getContext(), presenter);
        initView(view);
        presenter.getNoteList();
    }

    private void initView(View rootView) {
        refreshLayout = rootView.findViewById(R.id.refreshLayout);
        refreshLayout.setRefreshHeader(new ClassicsHeader(getContext()));
        refreshLayout.setRefreshFooter(new ClassicsFooter(getContext()));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                if (!isLoading) {
                    presenter.getNoteList();
                }
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(1000/*,false*/);//传入false表示加载失败
            }
        });
        recyclerView = rootView.findViewById(R.id.rv_content);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void getNoteListSuccess(List<NoteModel> list) {
        adapter.setData(list);
        refreshLayout.finishRefresh(1000/*,false*/);//传入false表示刷新失败
        refreshLayout.finishRefresh(true);//传入false表示刷新失败
    }

    Handler mHandler = new Handler(Looper.getMainLooper());

    public final void runOnUiThread(Runnable action) {

        mHandler.post(action);

    }

    @Override
    public void delete(int pos) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.removeItem(pos);
            }
        });

    }

    @Override
    public void showFailureView(String msg) {
        refreshLayout.finishRefresh(false);//传入false表示刷新失败
    }

    @Override
    public void showLoading() {
        isLoading = true;
    }

    @Override
    public void hideLoading() {
        isLoading = false;
    }
}
