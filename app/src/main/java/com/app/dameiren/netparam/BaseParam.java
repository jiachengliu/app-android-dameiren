package com.app.dameiren.netparam;

import android.content.Context;

import com.app.dameiren.core.Constants;
import com.app.dameiren.utils.DeviceUtil;
import com.litesuits.common.utils.PackageUtil;

import cn.finalteam.okhttpfinal.RequestParams;
import cn.finalteam.toolsfinal.AppCacheUtils;

/**
 * Created by liujiacheng on 2016/4/29.
 */
public class BaseParam {
    public  RequestParams getBaseParams(RequestParams params,Context context) {
        params.addFormDataPart("deviceId", DeviceUtil.getInstance(context).getDeviceId());
        params.addFormDataPart("versionId", PackageUtil.getAppVersionCode(context));
        params.addFormDataPart("reqTime", System.currentTimeMillis()+"");
        params.addFormDataPart("operatorUid", AppCacheUtils.getInstance(context).getString(Constants.SP_USER_ID)+"");

        return params;
    }
}
