package com.jm.jlog.printer.file.clean;

import java.io.File;

/**
 * File MaxMillis CleanStrategy.
 *
 * @since 1.0.0
 */
public class FileMaxMillisCleanStrategy implements CleanStrategy {
    private static final long MAX_TIME_MILLIS = 1000 * 60 * 60 * 24;
    private long mMaxTimeMillis = MAX_TIME_MILLIS;

    public FileMaxMillisCleanStrategy() {
    }

    public FileMaxMillisCleanStrategy(long maxTimeMillis) {
        this.mMaxTimeMillis = maxTimeMillis;
    }

    @Override
    public boolean shouldClean(File file) {
        if (null != file && file.exists()) {
            long currentTimeMillis = System.currentTimeMillis();
            long lastModified = file.lastModified();
            long timeInterval = currentTimeMillis - lastModified;
            if ((timeInterval < 0) || (timeInterval >= mMaxTimeMillis)) {
                return true;
            }
        }
        return false;
    }
}
