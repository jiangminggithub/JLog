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

import com.jm.jlog.LogLevel;

/**
 * Simply join the timestamp, log level, tag and message together.
 *
 * @since 1.0.0
 */
public class DefaultFlattener implements Flattener {

  @Override
  public CharSequence flatten(long timeMillis, int logLevel, String tag, String message) {
    return Long.toString(timeMillis)
        + '|' + LogLevel.getShortLevelName(logLevel)
        + '|' + tag
        + '|' + message;
  }
}
