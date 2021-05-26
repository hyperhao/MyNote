package com.demo.note.list.view;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.swipe.SwipeLayout;
import com.demo.note.R;
import com.demo.note.bean.NoteModel;

public  class NoteListCell extends RecyclerView.ViewHolder {
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

        public NoteListCell(View view) {
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

        public void bindViewHolder(NoteModel bean) {
            time.setText(bean.timeStamp);
            title.setText(bean.title);
            desc.setText(bean.content);
        }
    }
