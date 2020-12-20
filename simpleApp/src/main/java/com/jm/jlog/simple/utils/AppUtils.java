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
