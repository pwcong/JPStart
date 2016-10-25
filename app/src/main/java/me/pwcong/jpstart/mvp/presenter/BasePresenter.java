package me.pwcong.jpstart.mvp.presenter;

import java.util.List;

import me.pwcong.jpstart.mvp.bean.JPItem;
import me.pwcong.jpstart.mvp.bean.PixivIllustBean;
import me.pwcong.jpstart.rxbus.event.EventContainer;

/**
 * Created by Pwcong on 2016/9/24.
 */

public abstract class BasePresenter<T> {

    public final T view;

    public BasePresenter(T view){
        this.view=view;
    }

    public interface MainActivityPresenter{
        void initMainActivity();
        void onRadioButtonChanged(int position);
        void onNavigationItemSelected(int id);
        void onBusEventInteraction(EventContainer eventContainer);
    }

    public interface JPStartTabFragmentPresenter{

        void initJPStartTabFragment();

    }

    public interface JPStartFragmentPresenter{

        void initJPStartFragment(int category);

    }

    public interface TranslateFragmentPresenter{

        void initTranslateFragment();
        void checkFromLanguate(int from);
        void checkToLanguage(int to);
        void checkImageViewClick(int id);
        void doTranslate();

    }

    public interface PixivIllustTabFragmentPresenter{
        void initPixivIllustTabFragment();
    }

    public interface PixivIllustFragmentPresenter{
        void initPixivIllustFragment(String mode);
        void reloadData(String mode);
        void onItemClick(PixivIllustBean bean);
    }

    public interface MemoryFragmentPresenter {

        void initMemoryFragment();
        void loadMore(int category);
        void setDate(int category);

    }

    public interface PuzzleActivityPresenter{

        void initPuzzleActivity();
        void loadData();
        void checkTypeSelect(int which);
        void checkAnswerSelect(int id, JPItem current,List<JPItem> items);
        void checkMenuSelect(int id);


    }


}
