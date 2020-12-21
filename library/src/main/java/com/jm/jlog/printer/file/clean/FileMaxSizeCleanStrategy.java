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
