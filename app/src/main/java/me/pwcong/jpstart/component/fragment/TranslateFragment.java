package me.pwcong.jpstart.component.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import butterknife.BindView;
import me.pwcong.jpstart.R;

/**
 * Created by Pwcong on 2016/9/28.
 */

public class TranslateFragment extends BaseFragment {

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
    @BindView(R.id.iv_dst_paste)
    ImageView mDstPasteImageView;
    @BindView(R.id.iv_dst_clear)
    ImageView mDstClearImageView;

    @Override
    protected int getViewId() {
        return R.layout.fragment_translate;
    }

    @Override
    protected void initVariable(@Nullable Bundle savedInstanceState) {





    }

    @Override
    protected void doAction() {

    }
}
