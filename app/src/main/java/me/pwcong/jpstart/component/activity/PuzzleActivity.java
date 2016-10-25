package me.pwcong.jpstart.component.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import me.pwcong.jpstart.R;
import me.pwcong.jpstart.conf.Constants;
import me.pwcong.jpstart.manager.SharedPreferenceManager;
import me.pwcong.jpstart.mvp.bean.JPItem;
import me.pwcong.jpstart.mvp.presenter.BasePresenter;
import me.pwcong.jpstart.mvp.presenter.PuzzleActivityPresenterImpl;
import me.pwcong.jpstart.mvp.view.BaseView;

/**
 * Created by Pwcong on 2016/10/24.
 */

public class PuzzleActivity extends BaseActivity implements BaseView.PuzzleActivityView,View.OnClickListener {

    public static final int TYPE_HIRAGANA_ROME = 0;
    public static final int TYPE_HIRAGANA_KATAKANA = 1;
    public static final int TYPE_KATAKANA_ROME = 2;


    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_show)
    TextView mShowTextView;
    @BindView(R.id.tv_count)
    TextView mCountTextView;
    @BindView(R.id.tv_tips)
    TextView mTipsTextView;
    @BindView(R.id.btn_answer1)
    Button mButton1;
    @BindView(R.id.btn_answer2)
    Button mButton2;
    @BindView(R.id.btn_answer3)
    Button mButton3;
    @BindView(R.id.btn_answer4)
    Button mButton4;

    int type = 0;
    int count = 0;
    JPItem current;
    List<JPItem> items;

    BasePresenter.PuzzleActivityPresenter presenter;

    @Override
    protected int getViewId() {
        return R.layout.activity_puzzle;
    }

    @Override
    protected void initVariable(@Nullable Bundle savedInstanceState) {

        presenter = new PuzzleActivityPresenterImpl(this);

        initToolbar();
        initButton();
        initTextView();
    }

    private void initToolbar(){
        mToolbar.setTitle(R.string.whoami);
        mToolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        setSupportActionBar(mToolbar);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void initButton(){

        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton3.setOnClickListener(this);
        mButton4.setOnClickListener(this);

    }

    private void initTextView(){
        mCountTextView.setText(String.valueOf(count));
    }



    @Override
    protected void doAction() {

        presenter.initPuzzleActivity();
    }

    @Override
    public void onClick(View v) {

        presenter.checkAnswerSelect(v.getId(),current,items);

    }


    @Override
    public void setData(JPItem current, List<JPItem> jams) {

        this.current = current;

        items=new ArrayList<>();
        items.add(current);
        items.addAll(jams);
        Collections.shuffle(items);

        switch (type){

            case TYPE_HIRAGANA_ROME:

                mShowTextView.setText(this.current.getHiragana());

                mButton1.setText(this.items.get(0).getRome());
                mButton2.setText(this.items.get(1).getRome());
                mButton3.setText(this.items.get(2).getRome());
                mButton4.setText(this.items.get(3).getRome());

                break;
            case TYPE_HIRAGANA_KATAKANA:

                mShowTextView.setText(this.current.getHiragana());

                mButton1.setText(this.items.get(0).getKatakana());
                mButton2.setText(this.items.get(1).getKatakana());
                mButton3.setText(this.items.get(2).getKatakana());
                mButton4.setText(this.items.get(3).getKatakana());


                break;
            case TYPE_KATAKANA_ROME:

                mShowTextView.setText(this.current.getKatakana());

                mButton1.setText(this.items.get(0).getRome());
                mButton2.setText(this.items.get(1).getRome());
                mButton3.setText(this.items.get(2).getRome());
                mButton4.setText(this.items.get(3).getRome());

                break;
            default:break;

        }

    }



    @Override
    public void showSelectDialog(String[] selection) {

        new AlertDialog.Builder(this).setItems(selection, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                type = which;
                presenter.checkTypeSelect(which);

            }
        })
                .setCancelable(false)
                .create()
                .show();
    }

    @Override
    public void showResultDialog(int title, String msg, int icon,
                                 int pbt, DialogInterface.OnClickListener pbl,
                                 int nbt, DialogInterface.OnClickListener nbl) {


        new AlertDialog.Builder(this)
                .setCancelable(false)
                .setMessage(msg)
                .setTitle(title)
                .setPositiveButton(pbt,pbl)
                .setNegativeButton(nbt,nbl)
                .create()
                .show();

    }

    @Override
    public void showDialog(int icon,int title,String msg) {

        new AlertDialog.Builder(this)
                .setTitle(title)
                .setIcon(icon)
                .setMessage(msg)
                .create()
                .show();

    }

    @Override
    public void setTitle(String title) {
        mToolbar.setTitle(title);
    }

    @Override
    public void setTitle(int title) {
        mToolbar.setTitle(title);
    }

    @Override
    public void addCount() {
        count++;
        mCountTextView.setText(String.valueOf(count));
        mTipsTextView.setText(String.valueOf(count));
        mTipsTextView.startAnimation(getTipsAnimation());
    }

    @Override
    public void clearCount() {

        int hs = SharedPreferenceManager.getInstance().getInt(Constants.HIGHEST_SCORE, 0);
        if(count > hs)
            SharedPreferenceManager.getInstance().putInt(Constants.HIGHEST_SCORE,count);


        count=0;
        mCountTextView.setText(String.valueOf(count));

    }

    @Override
    public void showMsg(int msg) {
        showSnackBar(mToolbar,msg);
    }

    @Override
    public void showMsg(String msg) {
        showSnackBar(mToolbar,msg);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.activity_puzzle_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_hiragana_rome:
                type = TYPE_HIRAGANA_ROME;
                presenter.checkTypeSelect(TYPE_HIRAGANA_ROME);
                break;
            case R.id.menu_hiragana_katakana:
                type = TYPE_HIRAGANA_KATAKANA;
                presenter.checkTypeSelect(TYPE_HIRAGANA_KATAKANA);
                break;
            case R.id.menu_katakana_rome:
                type = TYPE_KATAKANA_ROME;
                presenter.checkTypeSelect(TYPE_KATAKANA_ROME);
                break;

            default:

                presenter.checkMenuSelect(item.getItemId());

                break;

        }
        return true;
    }


    private Animation getTipsAnimation(){

        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(new AlphaAnimation(1,0));
        animationSet.setDuration(1000);
        animationSet.setFillAfter(true);

        return  animationSet;

    }
}
