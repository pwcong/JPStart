package me.pwcong.jpstart.mvp.bean;

/**
 * Created by Pwcong on 2016/10/3.
 */

public class PixivIllustBean {

    int id;
    String title;
    String author;
    String date;
    String img_240x480;
    String img_600x600;
    String img_1200x1200;
    String img_original;
    String link;

    public PixivIllustBean() {
    }

    public PixivIllustBean(int id, String title, String author, String date, String img_240x480, String img_600x600, String img_1200x1200, String img_original, String link) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.date = date;
        this.img_240x480 = img_240x480;
        this.img_600x600 = img_600x600;
        this.img_1200x1200 = img_1200x1200;
        this.img_original = img_original;
        this.link = link;
    }

    @Override
    public String toString() {
        return "PixivIllustBean{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", date='" + date + '\'' +
                ", img_240x480='" + img_240x480 + '\'' +
                ", img_600x600='" + img_600x600 + '\'' +
                ", img_1200x1200='" + img_1200x1200 + '\'' +
                ", img_original='" + img_original + '\'' +
                ", link='" + link + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImg_240x480() {
        return img_240x480;
    }

    public void setImg_240x480(String img_240x480) {
        this.img_240x480 = img_240x480;
    }

    public String getImg_600x600() {
        return img_600x600;
    }

    public void setImg_600x600(String img_600x600) {
        this.img_600x600 = img_600x600;
    }

    public String getImg_1200x1200() {
        return img_1200x1200;
    }

    public void setImg_1200x1200(String img_1200x1200) {
        this.img_1200x1200 = img_1200x1200;
    }

    public String getImg_original() {
        return img_original;
    }

    public void setImg_original(String img_original) {
        this.img_original = img_original;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
