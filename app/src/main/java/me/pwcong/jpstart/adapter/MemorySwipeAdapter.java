package me.pwcong.jpstart.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import me.pwcong.jpstart.R;
import me.pwcong.jpstart.conf.Constants;
import me.pwcong.jpstart.mvp.bean.JPItem;
import me.pwcong.jpstart.utils.ResourceUtils;

/**
 * Created by Pwcong on 2016/10/5.
 */

public class MemorySwipeAdapter extends BaseAdapter {

    List<JPItem> list;
    OnYinButtonClickListener onYinButtonClickListener;
    OnWriteButtonClickListener onWriteButtonClickListener;

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
        final JPItem item = list.get(position);

        if(convertView==null){

            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_memory,parent,false);
            holder=new ViewHolder();

            holder.tv_rome= (TextView) convertView.findViewById(R.id.tv_rome);
            holder.btn_yin= (Button) convertView.findViewById(R.id.btn_yin);
            holder.btn_write= (Button) convertView.findViewById(R.id.btn_write);
            holder.tv_hiragana= (TextView) convertView.findViewById(R.id.tv_hiragana);
            holder.tv_katakana= (TextView) convertView.findViewById(R.id.tv_katakana);

            convertView.setTag(holder);

        }else {
            holder= (ViewHolder) convertView.getTag();
        }

        holder.tv_rome.setText(item.getRome());

        if(item.getCategory()== Constants.CATEGORY_AOYIN){
            holder.tv_hiragana.setTextSize(ResourceUtils.getDimension(parent.getContext(),R.dimen.memory_item_text_size_mini));
        }else {
            holder.tv_hiragana.setTextSize(ResourceUtils.getDimension(parent.getContext(),R.dimen.memory_item_text_size));
        }

        holder.tv_hiragana.setText(item.getHiragana());
        holder.tv_katakana.setText(item.getKatakana());

        holder.btn_yin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onYinButtonClickListener!=null){
                    onYinButtonClickListener.onClick(item);
                }
            }
        });

        if(item.getCategory()==Constants.CATEGORY_AOYIN){
            holder.btn_write.setEnabled(false);
        }else {
            holder.btn_write.setEnabled(true);
            holder.btn_write.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onWriteButtonClickListener!=null){
                        onWriteButtonClickListener.onClick(item);
                    }
                }
            });
        }



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
        public TextView tv_hiragana;
        public TextView tv_katakana;

    }

    public void setOnYinButtonClickListener(OnYinButtonClickListener onYinButtonClickListener) {
        this.onYinButtonClickListener = onYinButtonClickListener;
    }

    public void setOnWriteButtonClickListener(OnWriteButtonClickListener onWriteButtonClickListener) {
        this.onWriteButtonClickListener = onWriteButtonClickListener;
    }

    public void setList(List<JPItem> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public interface OnYinButtonClickListener{
        void onClick(JPItem item);
    }

    public interface OnWriteButtonClickListener{
        void onClick(JPItem item);
    }


}
