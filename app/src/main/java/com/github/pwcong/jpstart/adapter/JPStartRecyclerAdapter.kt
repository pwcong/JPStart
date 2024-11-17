package com.github.pwcong.jpstart.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.pwcong.jpstart.App
import com.github.pwcong.jpstart.R
import com.github.pwcong.jpstart.constants.Constants
import com.github.pwcong.jpstart.mvp.bean.JPItem

class JPStartRecyclerAdapter(private val list: List<JPItem>) :
    RecyclerView.Adapter<JPStartRecyclerAdapter.ViewHolder>() {
    private var onItemClickListener: OnItemClickListener? = null
    private var onItemLongClickListener: OnItemLongClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View

        if (viewType == Constants.TYPE_HEADER) {
            view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_jpitem_header, parent, false)
        } else if (viewType == Constants.TYPE_ITEM_DISABLE) {
            view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_jpitem_disable, parent, false)
        } else {
            view = LayoutInflater.from(parent.context).inflate(R.layout.item_jpitem, parent, false)
        }
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        if (App.TYPE_MING == Constants.TYPE_HIRAGANA) {
            holder.tv_jiaming.text = item.hiragana
        } else {
            holder.tv_jiaming.text = item.katakana
        }

        if (holder.tv_rome != null) {
            holder.tv_rome.text = item.rome
        }

        holder.item = item

        if (getItemViewType(position) == Constants.TYPE_ITEM && holder.item!!.isExisted) {
            holder.view.setOnClickListener {
                if (onItemClickListener != null) {
                    onItemClickListener!!.onClick(holder.item!!)
                }
            }

            if (holder.item!!.category != Constants.CATEGORY_AOYIN) {
                holder.view.setOnLongClickListener {
                    if (onItemLongClickListener != null) {
                        onItemLongClickListener!!.onLongClick(holder.item!!)
                    }
                    true
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return list[position].type
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val tv_jiaming: TextView = itemView.findViewById<View>(R.id.tv_jiaming) as TextView
        val tv_rome: TextView? = itemView.findViewById(R.id.tv_rome)
        var item: JPItem? = null
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener?) {
        this.onItemClickListener = onItemClickListener
    }

    fun setOnItemLongClickListener(onItemLongClickListener: OnItemLongClickListener?) {
        this.onItemLongClickListener = onItemLongClickListener
    }

    interface OnItemClickListener {
        fun onClick(item: JPItem)
    }

    interface OnItemLongClickListener {
        fun onLongClick(item: JPItem)
    }
}
