package me.pwcong.jpstart.mvp.presenter;

import java.util.ArrayList;
import java.util.List;

import me.pwcong.jpstart.R;
import me.pwcong.jpstart.component.activity.PuzzleActivity;
import me.pwcong.jpstart.mvp.bean.JPItem;
import me.pwcong.jpstart.mvp.model.BaseModel;
import me.pwcong.jpstart.mvp.model.PuzzleActivityModelImpl;
import me.pwcong.jpstart.mvp.view.BaseView;

/**
 * Created by Pwcong on 2016/10/24.
 */

public class PuzzleActivityPresenterImpl extends BasePresenter<BaseView.PuzzleActivityView> implements BasePresenter.PuzzleActivityPresenter {

    BaseModel.PuzzleActivityModel model;

    public PuzzleActivityPresenterImpl(BaseView.PuzzleActivityView view) {
        super(view);
        model = new PuzzleActivityModelImpl();
    }

    @Override
    public void initPuzzleActivity() {

        view.showSelectDialog(model.getOptions());

    }

    @Override
    public void loadData() {

        List<JPItem> items = model.getItems();
        List<JPItem> jams = new ArrayList<>();

        JPItem current = items.get(0);
        jams.add(items.get(1));
        jams.add(items.get(2));
        jams.add(items.get(3));

        view.setData(current,jams);

    }

    @Override
    public void checkTypeSelect(int which) {

        switch (which){

            case PuzzleActivity.TYPE_HIRAGANA_ROME:
                view.setTitle(R.string.hiragana_rome);
                break;
            case PuzzleActivity.TYPE_HIRAGANA_KATAKANA:
                view.setTitle(R.string.hiragana_katakana);
                break;
            case PuzzleActivity.TYPE_KATAKANA_ROME:
                view.setTitle(R.string.katakana_rome);
                break;
            default:break;

        }

        loadData();

    }

    @Override
    public void checkAnswerSelect(int id, JPItem current, List<JPItem> items) {

        switch (id){

            case R.id.btn_answer1:

                if(items.get(0).getId() == current.getId()){

                    view.addCount();
                    loadData();

                }else {
                    view.clearCount();
                }

                break;
            case R.id.btn_answer2:

                if(items.get(1).getId() == current.getId()){

                    view.addCount();
                    loadData();

                }else {
                    view.clearCount();
                }

                break;
            case R.id.btn_answer3:

                if(items.get(2).getId() == current.getId()){
                    view.addCount();
                    loadData();

                }else {
                    view.clearCount();
                }

                break;
            case R.id.btn_answer4:

                if(items.get(3).getId() == current.getId()){
                    view.addCount();
                    loadData();

                }else {
                    view.clearCount();
                }

                break;
            default:break;

        }

    }
}
