package me.pwcong.jpstart.mvp.presenter;

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
    }



}
