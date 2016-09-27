package me.pwcong.jpstart.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import me.pwcong.jpstart.R;
import me.pwcong.jpstart.mvp.bean.JPItem;

/**
 * Created by Pwcong on 2016/9/27.
 */

public class JPStartRecyclerAdapter extends RecyclerView.Adapter<JPStartRecyclerAdapter.ViewHolder>{

    List<JPItem> list;

    public JPStartRecyclerAdapter(List<JPItem> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemViewType(int position) {



        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return 0;
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
