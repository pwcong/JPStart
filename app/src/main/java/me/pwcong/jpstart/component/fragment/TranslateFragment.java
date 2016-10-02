package me.pwcong.jpstart.component.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.pwcong.jpstart.App;
import me.pwcong.jpstart.R;
import me.pwcong.jpstart.adapter.TranslateSpinnerAdapter;
import me.pwcong.jpstart.mvp.bean.TranslateSpinnerItem;
import me.pwcong.jpstart.mvp.presenter.BasePresenter;
import me.pwcong.jpstart.mvp.presenter.TranslateFragmentPresenterImpl;
import me.pwcong.jpstart.mvp.view.BaseView;
import me.pwcong.jpstart.network.baidu.BaiduTranslateApi;
import me.pwcong.jpstart.utils.ResourceUtils;

/**
 * Created by Pwcong on 2016/9/28.
 */

public class TranslateFragment extends BaseFragment implements BaseView.TranslateFragmentView, View.OnClickListener {

    private final String TAG=getClass().getSimpleName();

    @BindView(R.id.layout_root)
    LinearLayout mRootLayout;
    @BindView(R.id.spinner_from)
    Spinner mFromSpinner;
    @BindView(R.id.spinner_to)
    Spinner mToSpinner;
    @BindView(R.id.btn_translate)
    LinearLayout mTranslateButton;
    @BindView(R.id.et_src)
    EditText mSrcEditText;
    @BindView(R.id.tv_dst)
    TextView mDstTextView;

    @BindView(R.id.iv_src_copy)
    ImageView mSrcCopyImageView;
    @BindView(R.id.iv_src_paste)
    ImageView mSrcPasteImageView;
    @BindView(R.id.iv_src_clear)
    ImageView mSrcClearImageView;
    @BindView(R.id.iv_dst_copy)
    ImageView mDstCopyImageView;
    @BindView(R.id.iv_dst_clear)
    ImageView mDstClearImageView;

    BasePresenter.TranslateFragmentPresenter presenter;

    @Override
    protected int getViewId() {
        return R.layout.fragment_translate;
    }

    @Override
    protected void initVariable(@Nullable Bundle savedInstanceState) {

        presenter=new TranslateFragmentPresenterImpl(this);

        initSpinner();
        initButton();
        initImageView();
    }

    private void initSpinner(){

        mFromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                presenter.checkFromLanguate(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        mToSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                presenter.checkToLanguage(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

    }

    private void initButton(){


    }

    private void initImageView(){

        mSrcCopyImageView.setOnClickListener(this);
        mSrcPasteImageView.setOnClickListener(this);
        mSrcClearImageView.setOnClickListener(this);
        mDstCopyImageView.setOnClickListener(this);
        mDstClearImageView.setOnClickListener(this);

    }

    @Override
    protected void doAction() {
        presenter.initTranslateFragment();
    }

    @Override
    public void showMsg(String msg) {
        showSnackBar(mRootLayout,msg);
    }

    @Override
    public void showMsg(int msg) {
        showSnackBar(mRootLayout,msg);

    }

    @Override
    public String getSrcText() {
        return mSrcEditText.getText().toString();
    }

    @Override
    public void setSrcEditText(String text) {
        mSrcEditText.setText(text);
    }

    @Override
    public String getDstText() {
        return mDstTextView.getText().toString();
    }

    @Override
    public void setDstTextView(String text) {
        mDstTextView.setText(text);
    }

    @Override
    public void setFromSpinner(List<TranslateSpinnerItem> list) {

        mFromSpinner.setAdapter(new TranslateSpinnerAdapter(list,getContext()));
        mFromSpinner.setSelection(App.FROM_LAN);
    }

    @Override
    public void setToSpinner(List<TranslateSpinnerItem> list) {
        mToSpinner.setAdapter(new TranslateSpinnerAdapter(list,getContext()));
        mToSpinner.setSelection(App.TO_LAN);
    }


    @Override
    public void onClick(View v) {
        presenter.checkImageViewClick(v.getId());
    }


}
