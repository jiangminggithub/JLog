package com.jm.jlog.flattener;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import com.jm.jlog.LogLevel;

import java.text.SimpleDateFormat;

/**
 * Date Format Flattener.
 *
 * @since 1.0.0
 */
public class DateFormatFlattener implements Flattener {
    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    private SimpleDateFormat mDateFormat;

    @SuppressLint("SimpleDateFormat")
    public DateFormatFlattener(String formatPattern) {
        if (!TextUtils.isEmpty(formatPattern)) {
            mDateFormat = new SimpleDateFormat(formatPattern);
        } else {
            mDateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        }
    }

    @Override
    public CharSequence flatten(long timeMillis, int logLevel, String tag, String message) {
        return mDateFormat.format(timeMillis)
                + '|' + LogLevel.getShortLevelName(logLevel)
                + '|' + tag
                + '|' + message;
    }
}
