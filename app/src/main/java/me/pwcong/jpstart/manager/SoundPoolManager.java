package me.pwcong.jpstart.manager;

import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

import me.pwcong.jpstart.App;
import me.pwcong.jpstart.R;
import me.pwcong.jpstart.mvp.bean.JPSound;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Pwcong on 2016/9/29.
 */

public class SoundPoolManager {

    public static final Vector<JPSound> sounds = new Vector<>();

    static {

        sounds.add(new JPSound("a",R.raw.a));
        sounds.add(new JPSound("ba",R.raw.ba));
        sounds.add(new JPSound("be",R.raw.be));
        sounds.add(new JPSound("bi",R.raw.bi));
        sounds.add(new JPSound("bo",R.raw.bo));
        sounds.add(new JPSound("bu",R.raw.bu));
        sounds.add(new JPSound("bya",R.raw.bya));
        sounds.add(new JPSound("byo",R.raw.byo));
        sounds.add(new JPSound("byu",R.raw.byu));
        sounds.add(new JPSound("cha",R.raw.cha));
        sounds.add(new JPSound("chi",R.raw.chi));
        sounds.add(new JPSound("cho",R.raw.cho));
        sounds.add(new JPSound("chu",R.raw.chu));
        sounds.add(new JPSound("da",R.raw.da));
        sounds.add(new JPSound("de",R.raw.de));
        sounds.add(new JPSound("do",R.raw.doo));
        sounds.add(new JPSound("e",R.raw.e));
        sounds.add(new JPSound("fu",R.raw.fu));
        sounds.add(new JPSound("ga",R.raw.ga));
        sounds.add(new JPSound("ge",R.raw.ge));
        sounds.add(new JPSound("gi",R.raw.gi));
        sounds.add(new JPSound("go",R.raw.go));
        sounds.add(new JPSound("gu",R.raw.gu));
        sounds.add(new JPSound("gya",R.raw.gya));
        sounds.add(new JPSound("gyo",R.raw.gyo));
        sounds.add(new JPSound("gyu",R.raw.gyu));
        sounds.add(new JPSound("ha",R.raw.ha));
        sounds.add(new JPSound("he",R.raw.he));
        sounds.add(new JPSound("hi",R.raw.hi));
        sounds.add(new JPSound("ho",R.raw.ho));
        sounds.add(new JPSound("hya",R.raw.hya));
        sounds.add(new JPSound("hyo",R.raw.hyo));
        sounds.add(new JPSound("hyu",R.raw.hyu));
        sounds.add(new JPSound("i",R.raw.i));
        sounds.add(new JPSound("ja",R.raw.ja));
        sounds.add(new JPSound("ji",R.raw.ji));
        sounds.add(new JPSound("jo",R.raw.jo));
        sounds.add(new JPSound("ju",R.raw.ju));
        sounds.add(new JPSound("ka",R.raw.ka));
        sounds.add(new JPSound("ke",R.raw.ke));
        sounds.add(new JPSound("ki",R.raw.ki));
        sounds.add(new JPSound("ko",R.raw.ko));
        sounds.add(new JPSound("ku",R.raw.ku));
        sounds.add(new JPSound("kya",R.raw.kya));
        sounds.add(new JPSound("kyo",R.raw.kyo));
        sounds.add(new JPSound("kyu",R.raw.kyu));
        sounds.add(new JPSound("ma",R.raw.ma));
        sounds.add(new JPSound("me",R.raw.me));
        sounds.add(new JPSound("mi",R.raw.mi));
        sounds.add(new JPSound("mo",R.raw.mo));
        sounds.add(new JPSound("mu",R.raw.mu));
        sounds.add(new JPSound("mya",R.raw.mya));
        sounds.add(new JPSound("myo",R.raw.myo));
        sounds.add(new JPSound("myu",R.raw.myu));
        sounds.add(new JPSound("n",R.raw.n));
        sounds.add(new JPSound("na",R.raw.na));
        sounds.add(new JPSound("ne",R.raw.ne));
        sounds.add(new JPSound("ni",R.raw.ni));
        sounds.add(new JPSound("no",R.raw.no));
        sounds.add(new JPSound("nu",R.raw.nu));
        sounds.add(new JPSound("nya",R.raw.nya));
        sounds.add(new JPSound("nyo",R.raw.nyo));
        sounds.add(new JPSound("nyu",R.raw.nyu));
        sounds.add(new JPSound("o",R.raw.o));
        sounds.add(new JPSound("pa",R.raw.pa));
        sounds.add(new JPSound("pe",R.raw.pe));
        sounds.add(new JPSound("pi",R.raw.pi));
        sounds.add(new JPSound("po",R.raw.po));
        sounds.add(new JPSound("pu",R.raw.pu));
        sounds.add(new JPSound("pya",R.raw.pya));
        sounds.add(new JPSound("pyo",R.raw.pyo));
        sounds.add(new JPSound("pyu",R.raw.pyu));
        sounds.add(new JPSound("ra",R.raw.ra));
        sounds.add(new JPSound("re",R.raw.re));
        sounds.add(new JPSound("ri",R.raw.ri));
        sounds.add(new JPSound("ro",R.raw.ro));
        sounds.add(new JPSound("ru",R.raw.ru));
        sounds.add(new JPSound("rya",R.raw.rya));
        sounds.add(new JPSound("ryo",R.raw.ryo));
        sounds.add(new JPSound("ryu",R.raw.ryu));
        sounds.add(new JPSound("sa",R.raw.sa));
        sounds.add(new JPSound("se",R.raw.se));
        sounds.add(new JPSound("sha",R.raw.sha));
        sounds.add(new JPSound("shi",R.raw.shi));
        sounds.add(new JPSound("sho",R.raw.sho));
        sounds.add(new JPSound("shu",R.raw.shu));
        sounds.add(new JPSound("so",R.raw.so));
        sounds.add(new JPSound("su",R.raw.su));
        sounds.add(new JPSound("ta",R.raw.ta));
        sounds.add(new JPSound("te",R.raw.te));
        sounds.add(new JPSound("to",R.raw.to));
        sounds.add(new JPSound("tsu",R.raw.tsu));
        sounds.add(new JPSound("u",R.raw.u));
        sounds.add(new JPSound("wa",R.raw.wa));
        sounds.add(new JPSound("wo",R.raw.o));
        sounds.add(new JPSound("ya",R.raw.ya));
        sounds.add(new JPSound("yo",R.raw.yo));
        sounds.add(new JPSound("yu",R.raw.yu));
        sounds.add(new JPSound("za",R.raw.za));
        sounds.add(new JPSound("ze",R.raw.ze));
        sounds.add(new JPSound("zo",R.raw.zo));
        sounds.add(new JPSound("zu",R.raw.zu));


    }

    private static SoundPoolManager instance = null;

    SoundPool soundPool = null;
    Map<String,Integer> soundsIdMap = new ConcurrentHashMap<>();

    private SoundPoolManager(){

        soundPool=new SoundPool(1, AudioManager.STREAM_MUSIC,0);

    }

    public void init(){

        for(JPSound sound:sounds){

            int id = soundPool.load(App.getInstance(), sound.getResId(), 1);
            soundsIdMap.put(sound.getRome(),id);

        }


    }


    public static synchronized SoundPoolManager getInstance(){

        if(instance==null){
            instance=new SoundPoolManager();
        }
        return instance;

    }

    public void play(String rome){

        if(soundsIdMap.get(rome)!=null){
            soundPool.play(soundsIdMap.get(rome),1,1,0,0,1);
        }

    }




}
