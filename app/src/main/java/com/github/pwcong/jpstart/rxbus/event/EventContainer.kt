package com.github.pwcong.jpstart.rxbus.event

class EventContainer(var type: Int, var event: Any) {
    override fun toString(): String {
        return "EventContainer{" +
                "type=" + type +
                ", event=" + event +
                '}'
    }

    companion object {
        const val TYPE_PHOTOVIEW: Int = 123
        const val TYPE_SETTING: Int = 456
        const val TYPE_GAME: Int = 666
    }
}
