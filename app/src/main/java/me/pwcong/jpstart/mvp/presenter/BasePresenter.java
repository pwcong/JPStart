package me.pwcong.jpstart.mvp.presenter;

/**
 * Created by Pwcong on 2016/9/24.
 */

public abstract class BasePresenter<T> {

    public final T view;

    BasePresenter(T view){
        this.view=view;
    }

    public interface MainActivityPresenter{

        void onNavigationItemSelected(int id);

    }



}
