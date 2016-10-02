package me.pwcong.jpstart.mvp.presenter;

import me.pwcong.jpstart.App;
import me.pwcong.jpstart.R;
import me.pwcong.jpstart.manager.ClipboardManager;
import me.pwcong.jpstart.mvp.bean.BaiduTranslateBean;
import me.pwcong.jpstart.mvp.model.BaseModel;
import me.pwcong.jpstart.mvp.model.TranslateFragmentModelImpl;
import me.pwcong.jpstart.mvp.view.BaseView;
import me.pwcong.jpstart.network.baidu.BaiduTranslateApi;
import me.pwcong.jpstart.utils.StringUtils;
import rx.Subscriber;

/**
 * Created by Pwcong on 2016/10/2.
 */

public class TranslateFragmentPresenterImpl extends BasePresenter<BaseView.TranslateFragmentView> implements BasePresenter.TranslateFragmentPresenter {

    BaseModel.TranslateFragmentModel model;

    public TranslateFragmentPresenterImpl(BaseView.TranslateFragmentView view) {
        super(view);
        model=new TranslateFragmentModelImpl();
    }

    @Override
    public void initTranslateFragment() {

        view.setFromSpinner(model.getFromList());
        view.setToSpinner(model.getToList());

    }

    @Override
    public void checkFromLanguate(int from) {
        App.FROM_LAN=from;
    }

    @Override
    public void checkToLanguage(int to) {
        App.TO_LAN=to;
    }

    @Override
    public void checkImageViewClick(int id) {

        switch (id){

            case R.id.iv_src_copy:

                String srcText = view.getSrcText();
                if(!StringUtils.isNullOrEmpty(srcText)){
                    ClipboardManager.getInstance().setText("label",view.getSrcText());
                    view.showMsg(R.string.copy_successfully);
                }
                break;
            case R.id.iv_src_paste:
                view.setSrcEditText(ClipboardManager.getInstance().getText());
                break;
            case R.id.iv_src_clear:
                view.setSrcEditText("");
                break;
            case R.id.iv_dst_copy:

                String dstText = view.getDstText();
                if(!StringUtils.isNullOrEmpty(dstText)){
                    ClipboardManager.getInstance().setText("label",view.getDstText());
                    view.showMsg(R.string.copy_successfully);
                }
                break;
            case R.id.iv_dst_clear:
                view.setDstTextView("");
                break;
            default:break;
        }

    }

    @Override
    public void doTranslate() {

        String from;

        switch (App.FROM_LAN){

            case 0:from= BaiduTranslateApi.AUTO;break;
            case 1:from=BaiduTranslateApi.ZH;break;
            case 2:from=BaiduTranslateApi.EN;break;
            case 3:from=BaiduTranslateApi.JP;break;
            default:from=BaiduTranslateApi.AUTO;break;

        }

        String to;

        switch (App.TO_LAN){

            case 0:to=BaiduTranslateApi.ZH;break;
            case 1:to=BaiduTranslateApi.EN;break;
            case 2:to=BaiduTranslateApi.JP;break;
            default:to=BaiduTranslateApi.ZH;break;

        }

        model.translate(view.getSrcText(), from, to, new Subscriber<BaiduTranslateBean>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                view.showMsg(R.string.error_unknown);
                e.printStackTrace();
            }

            @Override
            public void onNext(BaiduTranslateBean baiduTranslateBean) {
                if(baiduTranslateBean.getError_code()==null){
                    view.setDstTextView(baiduTranslateBean.getTrans_result()[0].getDst());
                }else {
                    view.showMsg(baiduTranslateBean.getError_msg());
                }
            }
        });


    }
}
