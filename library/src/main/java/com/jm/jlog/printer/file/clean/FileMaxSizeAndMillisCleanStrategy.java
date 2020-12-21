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

package com.jm.jlog.printer.file.clean;

import java.io.File;

/**
 * File MaxSizeAndMillis CleanStrategy.
 *
 * @since 1.0.0
 */
public class FileMaxSizeAndMillisCleanStrategy implements CleanStrategy {
    private static final long DEFAULT_MAX_SIZE = 1024 * 1024 * 5;
    private static final long MAX_TIME_MILLIS = 1000 * 60 * 60 * 24;
    private long mMaxCleanSize = DEFAULT_MAX_SIZE;
    private long mMaxTimeMillis = MAX_TIME_MILLIS;

    public FileMaxSizeAndMillisCleanStrategy() {
    }

    public FileMaxSizeAndMillisCleanStrategy(long maxSize, long maxTimeMillis) {
        this.mMaxCleanSize = maxSize;
        this.mMaxTimeMillis = maxTimeMillis;
    }

    @Override
    public boolean shouldClean(File file) {
        if (null != file && file.exists()) {
            long currentTimeMillis = System.currentTimeMillis();
            long lastModified = file.lastModified();
            long timeInterval = currentTimeMillis - lastModified;
            if ((timeInterval < 0) || (timeInterval >= mMaxTimeMillis)
                    || (file.length() >= mMaxCleanSize)) {
                return true;
            }
        }
        return false;
    }
}
