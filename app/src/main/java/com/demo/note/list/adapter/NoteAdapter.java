package com.demo.note.list.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.swipe.SwipeLayout;
import com.demo.note.R;
import com.demo.note.detail.DetailActivity;
import com.demo.note.bean.NoteModel;
import com.demo.note.list.presenter.NotePresenter;
import com.demo.note.list.view.NoteListCell;

import java.util.ArrayList;
import java.util.List;

/**
 * @author haoyupeng
 * @time 2021/5/19
 */

public class NoteAdapter extends RecyclerView.Adapter {
    private List<NoteModel> mDatas = new ArrayList<>();
    private LayoutInflater inflater;
    private NotePresenter presenter;

    public NoteAdapter(Context context, NotePresenter presenter) {
        this.inflater = LayoutInflater.from(context);
        this.presenter = presenter;
    }

    public void removeItem(int pos) {
        mDatas.remove(pos);
        notifyDataSetChanged();
    }

    public void setData(List<NoteModel> list) {
        if (list == null) {
            return;
        }
        mDatas.clear();
        mDatas.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NoteListCell(inflater.inflate(R.layout.layout_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NoteListCell) {
            ((NoteListCell) holder).bindViewHolder(mDatas.get(position));
            ((NoteListCell) holder).setClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), DetailActivity.class);
                    intent.putExtra("id", mDatas.get(position).id);
                    view.getContext().startActivity(intent);
                }
            });

            ((NoteListCell) holder).setCloseListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("HYP", "pos pos:" + position);
                    presenter.deleteNote(mDatas.get(position).id, position);
                }
            });
        }
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

}
