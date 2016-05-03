package com.app.dameiren.netparam;


import android.content.Context;

import cn.finalteam.okhttpfinal.HttpCycleContext;
import cn.finalteam.okhttpfinal.RequestParams;

/**
 * Created by liujiacheng on 2016/4/29.
 */
public class MainParam extends BaseParam implements HttpCycleContext{
    protected String HTTP_TASK_KEY = "HttpTaskKey_"+hashCode();
    public RequestParams getLive(String key,Context context) {
        RequestParams param = new RequestParams(this);
        getBaseParams(param,context);
        param.addFormDataPart("page", 1);
        param.addFormDataPart("limit", 12);
        HTTP_TASK_KEY = key;
        return param;
    }
    @Override
    public String getHttpTaskKey(){
        return HTTP_TASK_KEY;
    }

}
