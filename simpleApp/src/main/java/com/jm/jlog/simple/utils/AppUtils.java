/*
 * Copyright 2020 Jiang Ming
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jm.jlog.simple.utils;

import com.jm.jlog.simple.BuildConfig;

public class AppUtils {

    /**
     * Get the app version information.
     *
     * @return String to get the version related information of app.
     */
    public static String getAppVersionInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("App VersionInfo: BUILD_TYPE = ");
        sb.append(BuildConfig.BUILD_TYPE);
        sb.append(", VERSION_NAME = ");
        sb.append(BuildConfig.VERSION_NAME);
        sb.append(", VERSION_CODE = ");
        sb.append(BuildConfig.VERSION_CODE);
        return sb.toString();
    }

}
