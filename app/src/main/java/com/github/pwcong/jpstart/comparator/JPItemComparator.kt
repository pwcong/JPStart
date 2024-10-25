package com.github.pwcong.jpstart.comparator

import com.github.pwcong.jpstart.mvp.bean.JPItem

class JPItemComparator : Comparator<JPItem?> {
    override fun compare(o1: JPItem?, o2: JPItem?): Int {
        return if (o1 == null || o2 == null || o1.row < o2.row) {
            -1
        } else if (o1.row == o2.row) {
            if (o1.column < o2.column) {
                -1
            } else if (o1.column == o2.column) {
                0
            } else {
                1
            }
        } else {
            1
        }
    }
}