package com.app.dameiren.ui.base;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;

import com.app.dameiren.callback.NetParamsAndResult;
import com.app.dameiren.utils.ActivityControl;

import cn.finalteam.okhttpfinal.BaseHttpRequestCallback;
import cn.finalteam.okhttpfinal.HttpCycleContext;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.HttpTaskHandler;
import cn.finalteam.okhttpfinal.RequestParams;

/**
 * Created by liujiacheng on 2016/3/30.
 */
public abstract class BaseActivity extends FragmentActivity implements HttpCycleContext {
    protected final String HTTP_TASK_KEY = "HttpTaskKey_" + hashCode();
    protected BaseActivity mActivity;
    private NetParamsAndResult netParamsAndResult;
    /**
     * FINAL_GET 数据请求
     */
    public static final int NET_METHOD_GET = 101;
    /**
     * FINAL_POST 数据请求
     */
    public static final int NET_METHOD_POST = 102;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 绑定布局
        setContentView(initLayout());
        //初始化全局变量
        mActivity = this;
        // 实现类处理传入数据
        initBundle();
        // 实现类处理控件数据绑定
        initView();
        // 实现处理初始化完成之后
        initAfter();
        ActivityControl.getInstance(this).add(mActivity);
    }
    protected void initNetParamsAndResult(NetParamsAndResult mNetParamsAndResult){
        netParamsAndResult = mNetParamsAndResult;
    }
    protected void startTask(final String url, final int what, final int method, final boolean isCache){

        // 接口回调回去操作参数
       RequestParams params = netParamsAndResult.onStart(what);
       BaseHttpRequestCallback baseHttpRequestCallback = new BaseHttpRequestCallback<String>(){
           @Override
           public void onSuccess(String string) {
            super.onSuccess(string);
            sendMessage(what,0,string);
           }
           @Override
           public void onStart() {
               super.onStart();
           }

           @Override
           public void onFinish() {
               super.onFinish();
           }

           @Override
           public void onFailure(int errorCode, String msg) {
               super.onFailure(errorCode, msg);
               sendMessage(what, errorCode, msg);
           }
       };
       if (method == NET_METHOD_GET){
           HttpRequest.get(url, params, baseHttpRequestCallback);
       }else if (method == NET_METHOD_POST){
           HttpRequest.post(url, params, baseHttpRequestCallback);
       }else {
           HttpRequest.post(url, null, baseHttpRequestCallback);
       }

    }

    private void sendMessage(int what, int statusCode, String result) {
        // 创建消息对象
        Message msg = mHandler.obtainMessage();
        // 传入操作码
        msg.what = what;
        // 请求结果码
        msg.arg2 = statusCode;
        // 请求结果
        Bundle data = new Bundle();
        data.putString("result", result);
        msg.setData(data);
        // 发送消息
        mHandler.sendMessage(msg);
    }
    /**
     * NEW_消息处理对象
     */
    protected Handler mHandler = new Handler() {

        @SuppressWarnings("unchecked")
        @Override
        public void handleMessage(Message msg) {
            // 判断对象是否销毁
            if (mActivity == null || ActivityControl.getInstance(mActivity).getCurrent() == null) {
                return;
            }
            // 获取操作码
            int what = msg.what;
            // 获取结果值
            String result = msg.getData().getString("result");
            // 获取请求结果码
            int arg2 = msg.arg2;
            // 回调请求结果
            if (arg2 == 0) {
                netParamsAndResult.onSuccess(what, result, false);
            } else {
                netParamsAndResult.onError(what, arg2, result);
            }
        }
    };

    @Override
    public String getHttpTaskKey(){
        return HTTP_TASK_KEY;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        HttpTaskHandler.getInstance().removeTask(HTTP_TASK_KEY);
    }

    /**
     * Method_初始化布局 ：对展示布局进行设置
     *
     * @return 布局资源 ID
     */
    protected abstract int initLayout();

    /**
     * Method_初始化传入参数 ：处理进入之前传入的数据
     */
    protected abstract void initBundle();

    /**
     * Method_初始化控件参数： 在该方法中，可以对已绑定的控件数据初始化
     */
    protected abstract void initView();

    /**
     * Method_初始化之后： 在基础数据初始化完成之后可以使用该方法
     */
    protected abstract void initAfter();
}
