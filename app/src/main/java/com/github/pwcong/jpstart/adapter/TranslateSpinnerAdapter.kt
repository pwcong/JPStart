package com.github.pwcong.jpstart.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.github.pwcong.jpstart.R
import com.github.pwcong.jpstart.mvp.bean.TranslateSpinnerItem

class TranslateSpinnerAdapter(var list: List<TranslateSpinnerItem>, var context: Context) :
    BaseAdapter() {
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View

        val spinnerItem = list[position]

        if (spinnerItem.isHasIcon) {
            view = LayoutInflater.from(context)
                .inflate(R.layout.spinner_item_translate_icon, parent, false)
            val textView = view.findViewById<View>(R.id.tv_name) as TextView
            textView.text = spinnerItem.name
            val imageView = view.findViewById<View>(R.id.iv_icon) as ImageView
            Glide.with(context).asBitmap().load(spinnerItem.icon).into(imageView)
        } else {
            view =
                LayoutInflater.from(context).inflate(R.layout.spinner_item_translate, parent, false)
            val textView = view.findViewById<View>(R.id.tv_name) as TextView
            textView.text = spinnerItem.name
        }

        return view
    }
}
