package com.github.pwcong.jpstart.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import com.github.pwcong.jpstart.R
import com.github.pwcong.jpstart.constant.Constant
import com.github.pwcong.jpstart.mvp.bean.JPItem
import com.github.pwcong.jpstart.utils.ResourceUtils

class MemorySwipeAdapter(private var list: List<JPItem>) : BaseAdapter() {
    private var onYinButtonClickListener: OnYinButtonClickListener? = null
    private var onWriteButtonClickListener: OnWriteButtonClickListener? = null

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
        var currentConvertView = convertView

        val holder: ViewHolder
        val item = list[position]

        if (currentConvertView == null) {
            currentConvertView =
                LayoutInflater.from(parent.context).inflate(R.layout.item_memory, parent, false)
            holder = ViewHolder()

            holder.tv_rome = currentConvertView.findViewById<View>(R.id.tv_rome) as TextView
            holder.btn_yin = currentConvertView.findViewById<View>(R.id.btn_yin) as Button
            holder.btn_write = currentConvertView.findViewById<View>(R.id.btn_write) as Button
            holder.tv_hiragana = currentConvertView.findViewById<View>(R.id.tv_hiragana) as TextView
            holder.tv_katakana = currentConvertView.findViewById<View>(R.id.tv_katakana) as TextView

            currentConvertView.tag = holder
        } else {
            holder = currentConvertView.tag as ViewHolder
        }

        holder.tv_rome!!.text = item.rome

        if (item.category == Constant.CATEGORY_AOYIN) {
            holder.tv_hiragana
                ?.setTextSize(
                    ResourceUtils.getDimension(
                        parent.context,
                        R.dimen.memory_item_text_size_mini
                    )
                )
        } else {
            holder.tv_hiragana
                ?.setTextSize(
                    ResourceUtils.getDimension(
                        parent.context,
                        R.dimen.memory_item_text_size
                    )
                )
        }

        holder.tv_hiragana!!.text = item.hiragana
        holder.tv_katakana!!.text = item.katakana

        holder.btn_yin!!.setOnClickListener {
            if (onYinButtonClickListener != null) {
                onYinButtonClickListener!!.onClick(item)
            }
        }

        if (item.category == Constant.CATEGORY_AOYIN) {
            holder.btn_write!!.isEnabled = false
        } else {
            holder.btn_write!!.isEnabled = true
            holder.btn_write!!.setOnClickListener {
                if (onWriteButtonClickListener != null) {
                    onWriteButtonClickListener!!.onClick(item)
                }
            }
        }

        return currentConvertView!!
    }

    override fun isEmpty(): Boolean {
        return list.isEmpty()
    }

    fun remove(index: Int) {
        if (index > -1 && index < list.size) {
            (list as ArrayList).removeAt(index)
            notifyDataSetChanged()
        }
    }

    inner class ViewHolder {
        var tv_rome: TextView? = null
        var btn_yin: Button? = null
        var btn_write: Button? = null
        var tv_hiragana: TextView? = null
        var tv_katakana: TextView? = null
    }

    fun setOnYinButtonClickListener(onYinButtonClickListener: OnYinButtonClickListener?) {
        this.onYinButtonClickListener = onYinButtonClickListener
    }

    fun setOnWriteButtonClickListener(onWriteButtonClickListener: OnWriteButtonClickListener?) {
        this.onWriteButtonClickListener = onWriteButtonClickListener
    }

    fun setList(list: List<JPItem>) {
        this.list = list
        notifyDataSetChanged()
    }

    interface OnYinButtonClickListener {
        fun onClick(item: JPItem)
    }

    interface OnWriteButtonClickListener {
        fun onClick(item: JPItem)
    }
}
