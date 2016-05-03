/*
 * Copyright (c) 2015, 张涛.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.app.dameiren.core;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.litesuits.common.io.FileUtils;
import com.litesuits.common.utils.FileUtil;

import cn.finalteam.okhttpfinal.OkHttpFinal;
import cn.finalteam.okhttpfinal.OkHttpFinalConfiguration;
import okhttp3.Cache;


/**
 * 
 * @author kymjs (https://www.kymjs.com/)
 * @since 2015-3
 */
public class MyApplication extends Application {
    public static int screenW;
    public static int screenH;

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化UncaughtExceptionHandler，监听异常闪退，并保存到SD中。
        CrashHandler.create(this);
        //初始化OKHttpFinal，只是简单赋值，不会阻塞线程。
        OkHttpFinalConfiguration.Builder builder = new OkHttpFinalConfiguration.Builder();
        Cache cache = new Cache(FileUtils.getFile(AppConfig.saveFolder+AppConfig.cacheFolder),AppConfig.cacheSize);
        builder.setCacheAge(cache,AppConfig.cacheTime);

        OkHttpFinal.getInstance().init(builder.build());
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}
