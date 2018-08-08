package me.pwcong.jpstart.mvp.bean;

/**
 * Created by Pwcong on 2016/10/11.
 */

public class JPGif {

    private String rome;
    private int hiragana;
    private int katakana;

    public JPGif(String rome, int hiragana, int katakana) {
        this.rome = rome;
        this.hiragana = hiragana;
        this.katakana = katakana;
    }

    @Override
    public String toString() {
        return "JPGif{" +
                "rome='" + rome + '\'' +
                ", hiragana=" + hiragana +
                ", katakana=" + katakana +
                '}';
    }

    public String getRome() {
        return rome;
    }

    public void setRome(String rome) {
        this.rome = rome;
    }

    public int getHiragana() {
        return hiragana;
    }

    public void setHiragana(int hiragana) {
        this.hiragana = hiragana;
    }

    public int getKatakana() {
        return katakana;
    }

    public void setKatakana(int katakana) {
        this.katakana = katakana;
    }
}


