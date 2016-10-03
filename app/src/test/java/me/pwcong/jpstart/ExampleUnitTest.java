package me.pwcong.jpstart;

import org.junit.Test;

import java.util.List;

import me.pwcong.jpstart.mvp.bean.BaiduTranslateBean;
import me.pwcong.jpstart.mvp.bean.PixivIllustBean;
import me.pwcong.jpstart.mvp.bean.YoudaoTranslateBean;
import me.pwcong.jpstart.mvp.model.BaseModel;
import me.pwcong.jpstart.mvp.model.PixivIllustFragmentModelImpl;
import me.pwcong.jpstart.network.baidu.BaiduTranslateApi;
import me.pwcong.jpstart.network.baidu.service.BaiduService;
import me.pwcong.jpstart.network.baidu.service.BaiduTranslateServiceImpl;
import me.pwcong.jpstart.network.pixiv.PixivIllustApi;
import me.pwcong.jpstart.network.youdao.service.YoudaoService;
import me.pwcong.jpstart.network.youdao.service.YoudaoTranslateServiceImpl;
import rx.Subscriber;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }


    @Test
    public void testRamdom(){

        System.out.print("\t");

    }


    @Test
    public void testBaiduTranslate(){

        BaiduService.TranslateService service=new BaiduTranslateServiceImpl();
        service.translate("可愛い", BaiduTranslateApi.JP, BaiduTranslateApi.ZH, new Subscriber<BaiduTranslateBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(BaiduTranslateBean baiduTranslateBean) {
                System.out.print(baiduTranslateBean.toString());
            }
        });

    }

    @Test
    public void testYoudaoTranslate(){

        YoudaoService.YoudaoTranslateService service=new YoudaoTranslateServiceImpl();
        service.translate("可愛い", new Subscriber<YoudaoTranslateBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(YoudaoTranslateBean youdaoTranslateBean) {
                System.out.print(youdaoTranslateBean.toString());
            }
        });

    }

    @Test
    public void testPixiv(){

        BaseModel.PixivIllustFragmentModel model=new PixivIllustFragmentModelImpl();
        model.getIllusts(PixivIllustApi.MODE_MONTHLY, new Subscriber<List<PixivIllustBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<PixivIllustBean> pixivIllustBeen) {
                for (PixivIllustBean bean:pixivIllustBeen){

                    System.out.println(bean.toString());
                }
            }
        });

        System.out.println("OK");

    }





}