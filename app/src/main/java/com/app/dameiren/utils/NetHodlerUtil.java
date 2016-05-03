package com.app.dameiren.utils;

import com.app.dameiren.netparam.MainParam;

/**
 * Created by liujiacheng on 2016/5/3.
 */
public class NetHodlerUtil {

    private static class NetMainHodler{
        private static final MainParam mainParam = new MainParam();
    }
    public static MainParam getMainParam(){
        return NetMainHodler.mainParam;
    }

}
