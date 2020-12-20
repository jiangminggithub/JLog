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
