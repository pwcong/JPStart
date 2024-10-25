package com.github.pwcong.jpstart.rxbus.event

class GameEvent(var type: Int) {
    override fun toString(): String {
        return "GameEvent{" +
                "type=" + type +
                '}'
    }

    companion object {
        const val TYPE_PUZZLE: Int = 1
    }
}
