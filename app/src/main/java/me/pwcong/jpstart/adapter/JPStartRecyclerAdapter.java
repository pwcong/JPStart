package me.pwcong.jpstart.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import me.pwcong.jpstart.R;
import me.pwcong.jpstart.conf.Constants;
import me.pwcong.jpstart.manager.SharedPreferenceManager;
import me.pwcong.jpstart.mvp.bean.JPItem;
import me.pwcong.jpstart.mvp.bean.JPItemWithViewType;

/**
 * Created by Pwcong on 2016/9/27.
 */

public class JPStartRecyclerAdapter extends RecyclerView.Adapter<JPStartRecyclerAdapter.ViewHolder>{

    List<JPItemWithViewType> list;

    public JPStartRecyclerAdapter(List<JPItemWithViewType> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;

        if(viewType== Constants.TYPE_HEADER) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jpitem_header,parent,false);
        }else if (viewType==Constants.TYPE_ITEM_DISABLE){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jpitem_disable,parent,false);
        }
        else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jpitem,parent,false);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        JPItemWithViewType itemWithViewType = list.get(position);

        if(SharedPreferenceManager.getInstance().getInt(Constants.TYPE_MING,0)==Constants.TYPE_HIRAGANA){
            holder.tv_jiaming.setText(itemWithViewType.getItem().getHiragana());
        }else {
            holder.tv_jiaming.setText(itemWithViewType.getItem().getKatakana());
        }

        holder.tv_rome.setText(itemWithViewType.getItem().getRome());
        holder.item=itemWithViewType.getItem();


    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getViewType();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public final View view;
        public final TextView tv_jiaming;
        public final TextView tv_rome;
        public JPItem item;

        public ViewHolder(View itemView) {
            super(itemView);

            this.view=itemView;
            this.tv_jiaming= (TextView) itemView.findViewById(R.id.tv_jiaming);
            this.tv_rome= (TextView) itemView.findViewById(R.id.tv_rome);

        }
    }


}
