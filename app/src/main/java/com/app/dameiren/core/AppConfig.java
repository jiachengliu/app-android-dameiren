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

import com.litesuits.common.utils.SystemTool;

/**
 * 配置文件常量
 * 
 * @author kymjs (https://www.kymjs.com)
 * @since 2015-3
 */
public class AppConfig {
    public static final String saveFolder = "app_dameiren";
    public static final String crashFolder = "/crash";
    public static final String cacheFolder = "/cache";
    public static final long cacheSize = 10*1024*1024;
    public static final int cacheTime = 10;//单位：秒


}
