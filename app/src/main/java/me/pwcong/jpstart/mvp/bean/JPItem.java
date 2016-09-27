package me.pwcong.jpstart.mvp.bean;

/**
 * Created by Pwcong on 2016/9/25.
 */

public class JPItem {

    private int id;
    private int row;
    private int column;
    private String hiragana;
    private String katakana;
    private String rome;
    private int category;
    private boolean existed;

    public JPItem(int id, int row, int column, String hiragana, String katakana, String rome, int category, boolean existed) {
        this.id = id;
        this.row = row;
        this.column = column;
        this.hiragana = hiragana;
        this.katakana = katakana;
        this.rome = rome;
        this.category = category;
        this.existed = existed;
    }

    @Override
    public String toString() {
        return "JPItem{" +
                "id=" + id +
                ", row=" + row +
                ", column=" + column +
                ", hiragana='" + hiragana + '\'' +
                ", katakana='" + katakana + '\'' +
                ", rome='" + rome + '\'' +
                ", category=" + category +
                ", existed=" + existed +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public String getHiragana() {
        return hiragana;
    }

    public void setHiragana(String hiragana) {
        this.hiragana = hiragana;
    }

    public String getKatakana() {
        return katakana;
    }

    public void setKatakana(String katakana) {
        this.katakana = katakana;
    }

    public String getRome() {
        return rome;
    }

    public void setRome(String rome) {
        this.rome = rome;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public boolean isExisted() {
        return existed;
    }

    public void setExisted(boolean existed) {
        this.existed = existed;
    }
}
