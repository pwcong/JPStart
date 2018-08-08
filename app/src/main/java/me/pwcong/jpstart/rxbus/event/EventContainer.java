package me.pwcong.jpstart.rxbus.event;

/**
 * Created by Pwcong on 2016/10/3.
 */

public class EventContainer {

    public static final int TYPE_PHOTOVIEW = 123;
    public static final int TYPE_SETTING = 456;
    public static final int TYPE_GAME = 666;


    private int type;
    private Object event;

    public EventContainer(int type, Object event) {
        this.type = type;
        this.event = event;
    }

    @Override
    public String toString() {
        return "EventContainer{" +
                "type=" + type +
                ", event=" + event +
                '}';
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Object getEvent() {
        return event;
    }

    public void setEvent(Object event) {
        this.event = event;
    }
}
