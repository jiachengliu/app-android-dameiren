package com.app.dameiren.ui;

import com.app.dameiren.R;
import com.app.dameiren.adapter.main.ViewPagerAdapter;
import com.app.dameiren.callback.NetParamsAndResult;
import com.app.dameiren.netparam.MainParam;
import com.app.dameiren.ui.base.BaseActivity;
import com.app.dameiren.utils.NetHodlerUtil;
import com.litesuits.common.assist.Toastor;
import com.shizhefei.view.indicator.Indicator;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.viewpager.SViewPager;

import cn.finalteam.okhttpfinal.RequestParams;


public class MainActivity extends BaseActivity implements NetParamsAndResult {
    private IndicatorViewPager indicatorViewPager;

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initBundle() {
        initNetParamsAndResult(this);
    }

    @Override
    protected void initView() {
        SViewPager viewPager = (SViewPager) findViewById(R.id.tabmain_viewPager);
        Indicator indicator = (Indicator) findViewById(R.id.tabmain_indicator);
        indicatorViewPager = new IndicatorViewPager(indicator, viewPager);
        indicatorViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(),getApplicationContext()));
        // 禁止viewpager的滑动事件
        viewPager.setCanScroll(false);
        // 设置viewpager保留界面不重新加载的页面数量
        viewPager.setOffscreenPageLimit(4);
        startTask("http://api150.aiyouyou.paojiao.cn/v5/game/newlist.do",1001,NET_METHOD_POST,false);
    }

    @Override
    protected void initAfter() {

    }

    @Override
    public RequestParams onStart(int what) {
//        RequestParams params = new RequestParams(this);
//        params.addFormDataPart("page", 1);
//        params.addFormDataPart("limit", 12);

        return  NetHodlerUtil.getMainParam().getLive("",this);
    }

    @Override
    public void onSuccess(int what, String result, boolean hashCache) {
        new Toastor(mActivity).showToast(result);

    }

    @Override
    public void onError(int what, int e, String message) {
        new Toastor(mActivity).showToast(message);
    }

    @Override
    public void onFinishNet(int what) {
        new Toastor(mActivity).showToast("onFinishNet");
    }
    @Override
    public void onStartNet(int what) {
        new Toastor(mActivity).showToast("onStartNet");
    }

}
