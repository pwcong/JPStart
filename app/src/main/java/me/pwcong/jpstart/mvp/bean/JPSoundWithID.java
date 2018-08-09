package me.pwcong.jpstart.mvp.bean;

public class JPSoundWithID {

    private JPSound sound;
    private Integer id;

    public JPSoundWithID(JPSound sound, Integer id) {
        this.sound = sound;
        this.id = id;
    }

    public JPSoundWithID() {
    }

    public JPSound getSound() {
        return sound;
    }

    public void setSound(JPSound sound) {
        this.sound = sound;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
