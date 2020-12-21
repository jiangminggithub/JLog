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
