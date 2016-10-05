package me.pwcong.jpstart.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import me.pwcong.jpstart.R;
import me.pwcong.jpstart.mvp.bean.JPItem;

/**
 * Created by Pwcong on 2016/10/5.
 */

public class MemorySwipeAdapter extends BaseAdapter {

    List<JPItem> list;

    public MemorySwipeAdapter(List<JPItem> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if(convertView==null){

            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_memory,parent,false);
            holder=new ViewHolder();

            holder.tv_rome= (TextView) convertView.findViewById(R.id.tv_rome);
            holder.btn_yin= (Button) convertView.findViewById(R.id.btn_yin);
            holder.btn_write= (Button) convertView.findViewById(R.id.btn_write);
            holder.tv_ming= (TextView) convertView.findViewById(R.id.tv_ming);

            convertView.setTag(holder);

        }else {
            holder= (ViewHolder) convertView.getTag();
        }

        holder.tv_rome.setText(list.get(position).getRome());
        holder.tv_ming.setText(list.get(position).getHiragana());


        return convertView;
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void remove(int index){
        if (index>-1&&index<list.size()){
            list.remove(index);
            notifyDataSetChanged();
        }
    }

    public class ViewHolder{

        public TextView tv_rome;
        public Button btn_yin;
        public Button btn_write;
        public TextView tv_ming;

    }



}
