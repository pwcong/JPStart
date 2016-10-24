package me.pwcong.jpstart.rxbus.event;

/**
 * Created by Pwcong on 2016/10/24.
 */

public class GameEvent {

    public static final int TYPE_PUZZLE = 1;
    public static final int TYPE_SUPPERZZLE = 2;

    int type;

    public GameEvent(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "GameEvent{" +
                "type=" + type +
                '}';
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
