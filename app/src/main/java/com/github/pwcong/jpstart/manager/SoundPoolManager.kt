package com.github.pwcong.jpstart.manager

import java.util.Vector
import java.util.concurrent.ConcurrentHashMap

import android.media.AudioManager
import android.media.SoundPool

import com.github.pwcong.jpstart.App
import com.github.pwcong.jpstart.R
import com.github.pwcong.jpstart.mvp.bean.JPSound
import com.github.pwcong.jpstart.mvp.bean.JPSoundWithID

class SoundPoolManager private constructor() : SoundPool.OnLoadCompleteListener {
    private var soundPool: SoundPool = SoundPool(1, AudioManager.STREAM_MUSIC, 0)
    private val soundsIdMap: MutableMap<String, JPSoundWithID> = ConcurrentHashMap()

    init {
        soundPool.setOnLoadCompleteListener(this)
    }

    fun init() {
        for (sound in sounds) {
            soundsIdMap[sound.rome] = JPSoundWithID(sound, null)
        }
    }

    fun play(rome: String) {
        val jpSoundWithID = soundsIdMap[rome] ?: return

        if (jpSoundWithID.id != null) {
            soundPool.play(jpSoundWithID.id!!, 1f, 1f, 0, 0, 1f)
        } else {
            val id = soundPool.load(App.getInstance(), jpSoundWithID.sound!!.resId, 1)
            jpSoundWithID.id = id
        }
    }

    override fun onLoadComplete(soundPool: SoundPool, i: Int, i1: Int) {
        soundPool.play(i, 1f, 1f, 0, 0, 1f)
    }

    companion object {
        private val sounds = Vector<JPSound>()

        init {
            sounds.add(JPSound("a", R.raw.a))
            sounds.add(JPSound("ba", R.raw.ba))
            sounds.add(JPSound("be", R.raw.be))
            sounds.add(JPSound("bi", R.raw.bi))
            sounds.add(JPSound("bo", R.raw.bo))
            sounds.add(JPSound("bu", R.raw.bu))
            sounds.add(JPSound("bya", R.raw.bya))
            sounds.add(JPSound("byo", R.raw.byo))
            sounds.add(JPSound("byu", R.raw.byu))
            sounds.add(JPSound("cha", R.raw.cha))
            sounds.add(JPSound("chi", R.raw.chi))
            sounds.add(JPSound("cho", R.raw.cho))
            sounds.add(JPSound("chu", R.raw.chu))
            sounds.add(JPSound("da", R.raw.da))
            sounds.add(JPSound("de", R.raw.de))
            sounds.add(JPSound("do", R.raw.doo))
            sounds.add(JPSound("e", R.raw.e))
            sounds.add(JPSound("fu", R.raw.fu))
            sounds.add(JPSound("ga", R.raw.ga))
            sounds.add(JPSound("ge", R.raw.ge))
            sounds.add(JPSound("gi", R.raw.gi))
            sounds.add(JPSound("go", R.raw.go))
            sounds.add(JPSound("gu", R.raw.gu))
            sounds.add(JPSound("gya", R.raw.gya))
            sounds.add(JPSound("gyo", R.raw.gyo))
            sounds.add(JPSound("gyu", R.raw.gyu))
            sounds.add(JPSound("ha", R.raw.ha))
            sounds.add(JPSound("he", R.raw.he))
            sounds.add(JPSound("hi", R.raw.hi))
            sounds.add(JPSound("ho", R.raw.ho))
            sounds.add(JPSound("hya", R.raw.hya))
            sounds.add(JPSound("hyo", R.raw.hyo))
            sounds.add(JPSound("hyu", R.raw.hyu))
            sounds.add(JPSound("i", R.raw.i))
            sounds.add(JPSound("ja", R.raw.ja))
            sounds.add(JPSound("ji", R.raw.ji))
            sounds.add(JPSound("jo", R.raw.jo))
            sounds.add(JPSound("ju", R.raw.ju))
            sounds.add(JPSound("ka", R.raw.ka))
            sounds.add(JPSound("ke", R.raw.ke))
            sounds.add(JPSound("ki", R.raw.ki))
            sounds.add(JPSound("ko", R.raw.ko))
            sounds.add(JPSound("ku", R.raw.ku))
            sounds.add(JPSound("kya", R.raw.kya))
            sounds.add(JPSound("kyo", R.raw.kyo))
            sounds.add(JPSound("kyu", R.raw.kyu))
            sounds.add(JPSound("ma", R.raw.ma))
            sounds.add(JPSound("me", R.raw.me))
            sounds.add(JPSound("mi", R.raw.mi))
            sounds.add(JPSound("mo", R.raw.mo))
            sounds.add(JPSound("mu", R.raw.mu))
            sounds.add(JPSound("mya", R.raw.mya))
            sounds.add(JPSound("myo", R.raw.myo))
            sounds.add(JPSound("myu", R.raw.myu))
            sounds.add(JPSound("n", R.raw.n))
            sounds.add(JPSound("na", R.raw.na))
            sounds.add(JPSound("ne", R.raw.ne))
            sounds.add(JPSound("ni", R.raw.ni))
            sounds.add(JPSound("no", R.raw.no))
            sounds.add(JPSound("nu", R.raw.nu))
            sounds.add(JPSound("nya", R.raw.nya))
            sounds.add(JPSound("nyo", R.raw.nyo))
            sounds.add(JPSound("nyu", R.raw.nyu))
            sounds.add(JPSound("o", R.raw.o))
            sounds.add(JPSound("pa", R.raw.pa))
            sounds.add(JPSound("pe", R.raw.pe))
            sounds.add(JPSound("pi", R.raw.pi))
            sounds.add(JPSound("po", R.raw.po))
            sounds.add(JPSound("pu", R.raw.pu))
            sounds.add(JPSound("pya", R.raw.pya))
            sounds.add(JPSound("pyo", R.raw.pyo))
            sounds.add(JPSound("pyu", R.raw.pyu))
            sounds.add(JPSound("ra", R.raw.ra))
            sounds.add(JPSound("re", R.raw.re))
            sounds.add(JPSound("ri", R.raw.ri))
            sounds.add(JPSound("ro", R.raw.ro))
            sounds.add(JPSound("ru", R.raw.ru))
            sounds.add(JPSound("rya", R.raw.rya))
            sounds.add(JPSound("ryo", R.raw.ryo))
            sounds.add(JPSound("ryu", R.raw.ryu))
            sounds.add(JPSound("sa", R.raw.sa))
            sounds.add(JPSound("se", R.raw.se))
            sounds.add(JPSound("sha", R.raw.sha))
            sounds.add(JPSound("shi", R.raw.shi))
            sounds.add(JPSound("sho", R.raw.sho))
            sounds.add(JPSound("shu", R.raw.shu))
            sounds.add(JPSound("so", R.raw.so))
            sounds.add(JPSound("su", R.raw.su))
            sounds.add(JPSound("ta", R.raw.ta))
            sounds.add(JPSound("te", R.raw.te))
            sounds.add(JPSound("to", R.raw.to))
            sounds.add(JPSound("tsu", R.raw.tsu))
            sounds.add(JPSound("u", R.raw.u))
            sounds.add(JPSound("wa", R.raw.wa))
            sounds.add(JPSound("wo", R.raw.o))
            sounds.add(JPSound("ya", R.raw.ya))
            sounds.add(JPSound("yo", R.raw.yo))
            sounds.add(JPSound("yu", R.raw.yu))
            sounds.add(JPSound("za", R.raw.za))
            sounds.add(JPSound("ze", R.raw.ze))
            sounds.add(JPSound("zo", R.raw.zo))
            sounds.add(JPSound("zu", R.raw.zu))
            sounds.add(JPSound("zi", R.raw.zi))
            sounds.add(JPSound("wo", R.raw.wo))
            sounds.add(JPSound("du", R.raw.du))
        }

        @get:Synchronized
        private var instance: SoundPoolManager? = null

        fun getInstance(): SoundPoolManager {
            if (instance == null) {
                instance = SoundPoolManager()
            }
            return instance!!
        }
    }
}