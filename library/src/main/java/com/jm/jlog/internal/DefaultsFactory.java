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

package com.jm.jlog.internal;

import com.jm.jlog.flattener.DefaultFlattener;
import com.jm.jlog.flattener.Flattener;
import com.jm.jlog.formatter.border.BorderFormatter;
import com.jm.jlog.formatter.border.DefaultBorderFormatter;
import com.jm.jlog.formatter.message.json.DefaultJsonFormatter;
import com.jm.jlog.formatter.message.json.JsonFormatter;
import com.jm.jlog.formatter.message.object.ObjectFormatter;
import com.jm.jlog.formatter.message.throwable.DefaultThrowableFormatter;
import com.jm.jlog.formatter.message.throwable.ThrowableFormatter;
import com.jm.jlog.formatter.message.xml.DefaultXmlFormatter;
import com.jm.jlog.formatter.message.xml.XmlFormatter;
import com.jm.jlog.formatter.stacktrace.DefaultStackTraceFormatter;
import com.jm.jlog.formatter.stacktrace.StackTraceFormatter;
import com.jm.jlog.formatter.thread.DefaultThreadFormatter;
import com.jm.jlog.formatter.thread.ThreadFormatter;
import com.jm.jlog.printer.Printer;
import com.jm.jlog.printer.file.FilePrinter;
import com.jm.jlog.printer.file.backup.BackupStrategy;
import com.jm.jlog.printer.file.backup.FileSizeBackupStrategy;
import com.jm.jlog.printer.file.clean.CleanStrategy;
import com.jm.jlog.printer.file.clean.NeverCleanStrategy;
import com.jm.jlog.printer.file.naming.ChangelessFileNameGenerator;
import com.jm.jlog.printer.file.naming.FileNameGenerator;

import java.util.Map;

/**
 * Factory for providing default implementation.
 */
public class DefaultsFactory {

  private static final String DEFAULT_LOG_FILE_NAME = "jlog.log";

  private static final long DEFAULT_LOG_FILE_MAX_SIZE = 1024 * 1024; // 1M bytes;

  /**
   * Create the default JSON formatter.
   */
  public static JsonFormatter createJsonFormatter() {
    return new DefaultJsonFormatter();
  }

  /**
   * Create the default XML formatter.
   */
  public static XmlFormatter createXmlFormatter() {
    return new DefaultXmlFormatter();
  }

  /**
   * Create the default throwable formatter.
   */
  public static ThrowableFormatter createThrowableFormatter() {
    return new DefaultThrowableFormatter();
  }

  /**
   * Create the default thread formatter.
   */
  public static ThreadFormatter createThreadFormatter() {
    return new DefaultThreadFormatter();
  }

  /**
   * Create the default stack trace formatter.
   */
  public static StackTraceFormatter createStackTraceFormatter() {
    return new DefaultStackTraceFormatter();
  }

  /**
   * Create the default border formatter.
   */
  public static BorderFormatter createBorderFormatter() {
    return new DefaultBorderFormatter();
  }

  /**
   * Create the default {@link Flattener}.
   */
  public static Flattener createFlattener() {
    return new DefaultFlattener();
  }

  /**
   * Create the default {@link Flattener}.
   */
  public static Flattener createFlattener2() {
    return new DefaultFlattener();
  }

  /**
   * Create the default printer.
   */
  public static Printer createPrinter() {
    return Platform.get().defaultPrinter();
  }

  /**
   * Create the default file name generator for {@link FilePrinter}.
   */
  public static FileNameGenerator createFileNameGenerator() {
    return new ChangelessFileNameGenerator(DEFAULT_LOG_FILE_NAME);
  }

  /**
   * Create the default backup strategy for {@link FilePrinter}.
   */
  public static BackupStrategy createBackupStrategy() {
    return new FileSizeBackupStrategy(DEFAULT_LOG_FILE_MAX_SIZE);
  }

  /**
   * Create the default clean strategy for {@link FilePrinter}.
   */
  public static CleanStrategy createCleanStrategy() {
    return new NeverCleanStrategy();
  }

  /**
   * Get the builtin object formatters.
   *
   * @return the builtin object formatters
   */
  public static Map<Class<?>, ObjectFormatter<?>> builtinObjectFormatters() {
    return Platform.get().builtinObjectFormatters();
  }
}
