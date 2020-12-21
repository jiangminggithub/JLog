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
 * Decide whether the log file should be clean.
 *
 * @since 1.0.0
 */
public interface CleanStrategy {

  /**
   * Whether we should clean a specified log file.
   *
   * @param file the log file
   * @return true is we should clean the log file
   */
  boolean shouldClean(File file);
}
