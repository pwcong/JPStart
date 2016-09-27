package me.pwcong.jpstart.mvp.model;

import java.util.ArrayList;
import java.util.List;

import me.pwcong.jpstart.App;
import me.pwcong.jpstart.R;
import me.pwcong.jpstart.conf.Constants;
import me.pwcong.jpstart.mvp.bean.JPTab;
import me.pwcong.jpstart.utils.ResourceUtils;

/**
 * Created by Pwcong on 2016/9/27.
 */

public class JPStartTabFragmentModelImpl implements BaseModel.JPStartTabFragmentModel {

    @Override
    public List<JPTab> getData() {

        List<JPTab> list=new ArrayList<>();
        String qingyin = ResourceUtils.getString(App.getInstance(), R.string.qingyin);
        String zhuoyin = ResourceUtils.getString(App.getInstance(), R.string.zhuoyin);
        String aoyin = ResourceUtils.getString(App.getInstance(), R.string.aoyin);

        list.add(new JPTab(Constants.TYPE_QINGYIN,qingyin));
        list.add(new JPTab(Constants.TYPE_ZHUOYIN,zhuoyin));
        list.add(new JPTab(Constants.TYPE_AOYIN,aoyin));

        return list;
    }
}
