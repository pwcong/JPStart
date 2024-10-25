package com.github.pwcong.jpstart.rxbus.event

class PhotoViewEvent(var img_url: String, var img_id: Int) {
    override fun toString(): String {
        return "PhotoViewEvent{" +
                "img_url='" + img_url + '\'' +
                ", img_id=" + img_id +
                '}'
    }
}
