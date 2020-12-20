package com.jm.jlog.printer.file.clean;

import java.io.File;

/**
 * File MaxSizeClean Strategy.
 *
 * @since 1.0.0
 */
public class FileMaxSizeCleanStrategy implements CleanStrategy {
    private static final long DEFAULT_MAX_SIZE = 1024 * 1024 * 5;
    private long mMaxCleanSize = DEFAULT_MAX_SIZE;

    public FileMaxSizeCleanStrategy() {
    }

    public FileMaxSizeCleanStrategy(long maxSize) {
        this.mMaxCleanSize = maxSize;
    }

    @Override
    public boolean shouldClean(File file) {
        if (null != file && file.exists()) {
            if ((file.length() >= mMaxCleanSize)) {
                return true;
            }
        }
        return false;
    }
}
