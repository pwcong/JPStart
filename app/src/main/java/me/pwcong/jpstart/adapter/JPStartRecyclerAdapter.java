package me.pwcong.jpstart.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import me.pwcong.jpstart.App;
import me.pwcong.jpstart.R;
import me.pwcong.jpstart.conf.Constants;
import me.pwcong.jpstart.mvp.bean.JPItem;

/**
 * Created by Pwcong on 2016/9/27.
 */

public class JPStartRecyclerAdapter extends RecyclerView.Adapter<JPStartRecyclerAdapter.ViewHolder> {

    private List<JPItem> list;

    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;


    public JPStartRecyclerAdapter(List<JPItem> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;

        if (viewType == Constants.TYPE_HEADER) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jpitem_header, parent, false);
        } else if (viewType == Constants.TYPE_ITEM_DISABLE) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jpitem_disable, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jpitem, parent, false);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        JPItem item = list.get(position);

        if (App.TYPE_MING == Constants.TYPE_HIRAGANA) {
            holder.tv_jiaming.setText(item.getHiragana());
        } else {
            holder.tv_jiaming.setText(item.getKatakana());
        }

        if (holder.tv_rome != null) {
            holder.tv_rome.setText(item.getRome());
        }

        holder.item = item;

        if (getItemViewType(position) == Constants.TYPE_ITEM && holder.item.isExisted()) {

            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (onItemClickListener != null) {
                        onItemClickListener.onClick(holder.item);
                    }

                }
            });

            if (holder.item.getCategory() != Constants.CATEGORY_AOYIN) {
                holder.view.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {

                        if (onItemLongClickListener != null) {
                            onItemLongClickListener.onLongClick(holder.item);
                        }

                        return true;
                    }
                });
            }


        }

    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View view;
        public final TextView tv_jiaming;
        public final TextView tv_rome;
        public JPItem item;

        public ViewHolder(View itemView) {
            super(itemView);

            this.view = itemView;
            this.tv_jiaming = (TextView) itemView.findViewById(R.id.tv_jiaming);
            this.tv_rome = (TextView) itemView.findViewById(R.id.tv_rome);

        }
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public interface OnItemClickListener {

        void onClick(JPItem item);

    }

    public interface OnItemLongClickListener {

        void onLongClick(JPItem item);

    }


}
