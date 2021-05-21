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
import com.demo.note.bean.NoteBean;
import com.demo.note.list.presenter.NotePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author haoyupeng@bytedance.com
 * @time 2021/5/19
 */

public class NoteAdapter extends RecyclerView.Adapter {
    private List<NoteBean> mDatas = new ArrayList<>();
    private LayoutInflater inflater;
    private NotePresenter presenter;

    public NoteAdapter(Context context, NotePresenter presenter) {
        this.inflater = LayoutInflater.from(context);
        this.presenter = presenter;
    }

    public void removeItem(int pos) {
        Log.d("HYP", "pos" + pos);
        mDatas.remove(pos);
        notifyDataSetChanged();
    }

    public void setData(List<NoteBean> list) {
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
        return new TextViewHolder(inflater.inflate(R.layout.layout_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TextViewHolder) {
            ((TextViewHolder) holder).bindViewHolder(mDatas.get(position));
            ((TextViewHolder) holder).setClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), DetailActivity.class);
                    intent.putExtra("id", mDatas.get(position).id);
                    view.getContext().startActivity(intent);
                }
            });

            ((TextViewHolder) holder).setCloseListener(new View.OnClickListener() {
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

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class TextViewHolder extends RecyclerView.ViewHolder {
        private SwipeLayout root;
        private LinearLayout delete;
        private LinearLayout layout_center;
        public TextView title;
        public TextView time;
        public TextView desc;

        public void setCloseListener(View.OnClickListener clickListener) {
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onClick(view);
                    root.close();
                }
            });
        }

        public void setClickListener(View.OnClickListener clickListener) {
            layout_center.setOnClickListener(clickListener);
        }

        public TextViewHolder(View view) {
            super(view);
            initView();
        }

        private void initView() {
            root = itemView.findViewById(R.id.swipe_root);
            delete = itemView.findViewById(R.id.layout_delete);
            time = itemView.findViewById(R.id.tv_time);
            desc = itemView.findViewById(R.id.tv_desc);
            title = itemView.findViewById(R.id.tv_title);
            layout_center = itemView.findViewById(R.id.layout_center);
        }

        public void bindViewHolder(NoteBean bean) {
            time.setText(bean.timeStamp);
            title.setText(bean.title);
            desc.setText(bean.content);
        }
    }


}
