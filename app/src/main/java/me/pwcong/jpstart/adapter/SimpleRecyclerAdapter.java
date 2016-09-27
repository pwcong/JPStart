package me.pwcong.jpstart.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import me.pwcong.jpstart.R;
import me.pwcong.jpstart.mvp.bean.JPItem;

/**
 * Created by Pwcong on 2016/9/27.
 */

public class SimpleRecyclerAdapter extends RecyclerView.Adapter<SimpleRecyclerAdapter.ViewHolder>{

    List<JPItem> list;

    public SimpleRecyclerAdapter(List<JPItem> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_simple, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.item=list.get(position);
        holder.tv_simple.setText(holder.item.getHiragana());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public final View view;
        public final TextView tv_simple;

        public JPItem item;

        public ViewHolder(View itemView) {
            super(itemView);
            view=itemView;
            tv_simple= (TextView) itemView.findViewById(R.id.tv_simple);

        }
    }

}
