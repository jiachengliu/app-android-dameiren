package com.app.dameiren.callback;

import cn.finalteam.okhttpfinal.RequestParams;

/**
 * Created by liujiacheng on 2016/3/31.
 */
public interface NetParamsAndResult {

    /**
     * Method_启动处理回调
     *
     * @param what_操作码
     * @return 请求参数
     */
    public RequestParams onStart(int what);

    /**
     * Method_启动定义参数回调
     *
     * @param what_操作码
     * @return 请求参数
     */
//    public Map<String, Object> onStartNetParam(int what);

    /**
     * Method_成功处理回调
     *
     * @param what_操作码
     * @param result_请求结果字符串
     */
    public void onSuccess(int what, String result, boolean hashCache);

    /**
     * Method_成功处理回调
     *
     * @param what_操作码
     * @param result_请求结果流
     * @param cookies_请求结果
     *            Cookie 信息
     */
//    public void onSuccess(int what, InputStream result, HashMap<String, String> cookies, boolean hashCache);

    /**
     * Method_错误处理回调
     *
     * @param what_操作码
     * @param e_错误码
     * @param message_错误信息
     */
    public void onError(int what, int e, String message);

    /**
     * 请求开始
     */
    public void onStartNet(int what);
    /**
     * 请求结束
     */
    public void onFinishNet(int what);
}
