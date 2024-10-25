package com.github.pwcong.jpstart.mvp.bean

class JPSoundWithID {
    var sound: JPSound? = null
    var id: Int? = null

    constructor(sound: JPSound?, id: Int?) {
        this.sound = sound
        this.id = id
    }

    constructor()
}