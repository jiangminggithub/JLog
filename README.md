# JLog

Powerful and flexible Android and Java log libraries that print logs to logcat, console and files, or wherever you like.

## Dependency

[Dependency reference link](https://jitpack.io/#jiangminggithub/JLog/1.0.0)

### Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

```groovy
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

### Step 2. Add the dependency

```groovy
    // release dependency
	dependencies {
	        implementation 'com.github.jiangminggithub:JLog:1.0.0'
	}
    
    // snapshot dependency
    dependencies {
    	        implementation 'com.github.jiangminggithub:JLog:master-SNAPSHOT'
    }
```

## Use Config

### 1. simple way

```java
JLog.init(LogLevel.ALL);
```

### 2. advance way

```java
LogConfiguration config = new LogConfiguration.Builder()
    .logLevel(BuildConfig.DEBUG ? LogLevel.ALL             // Specify log level, logs below this level won't be printed, default: LogLevel.ALL
        : LogLevel.NONE)
    .tag("MY_TAG")                                         // Specify TAG, default: "J-LOG"
    .enableThreadInfo()                                    // Enable thread info, disabled by default
    .enableStackTrace(2)                                   // Enable stack trace info with depth 2, disabled by default
    .enableBorder()                                        // Enable border, disabled by default
    .jsonFormatter(new MyJsonFormatter())                  // Default: DefaultJsonFormatter
    .xmlFormatter(new MyXmlFormatter())                    // Default: DefaultXmlFormatter
    .throwableFormatter(new MyThrowableFormatter())        // Default: DefaultThrowableFormatter
    .threadFormatter(new MyThreadFormatter())              // Default: DefaultThreadFormatter
    .stackTraceFormatter(new MyStackTraceFormatter())      // Default: DefaultStackTraceFormatter
    .borderFormatter(new MyBoardFormatter())               // Default: DefaultBorderFormatter
    .addObjectFormatter(AnyClass.class,                    // Add formatter for specific class of object
        new AnyClassObjectFormatter())                     // Use Object.toString() by default
    .addInterceptor(new BlacklistTagsFilterInterceptor(    // Add blacklist tags filter
        "blacklist1", "blacklist2", "blacklist3"))
    .addInterceptor(new MyInterceptor())                   // Add other log interceptor
    .build();

Printer androidPrinter = new AndroidPrinter(true);         // Printer that print the log using android.util.Log
Printer consolePrinter = new ConsolePrinter();             // Printer that print the log to console using System.out
Printer filePrinter = new FilePrinter                      // Printer that print(save) the log to file
    .Builder("<path-to-logs-dir>")                         // Specify the directory path of log file(s)
    .fileNameGenerator(new DateFileNameGenerator())        // Default: ChangelessFileNameGenerator("jlog.log")
    .backupStrategy(new NeverBackupStrategy())             // Default: FileSizeBackupStrategy(1024 * 1024)
    .cleanStrategy(new FileLastModifiedCleanStrategy(MAX_TIME))     // Default: NeverCleanStrategy()
    .flattener(new MyFlattener())                          // Default: DefaultFlattener
    .build();

JLog.init(                                                 // Initialize JLog
    config,                                                // Specify the log configuration, if not specified, will use new LogConfiguration.Builder().build()
    androidPrinter,                                        // Specify printers, if no printer is specified, AndroidPrinter(for Android)/ConsolePrinter(for java) will be used.
    consolePrinter,
    filePrinter);
```

## Simple use example

### 1. simple link

[Simple reference link](https://github.com/jiangminggithub/JLog/blob/master/simpleApp/src/main/java/com/jm/jlog/simple/utils/AppLog.java)

### 2. simple code

```java

/**
 * App Log Utils, powered by JLog.
 * <p>
 * Note: v/d/i/w/e/b are optional.
 *
 * @author JiangMing.
 * @see #v  [v] for VERBOSE, [vb] for enableBorder log
 * @see #d  [d] for DEBUG, [db] for enableBorder log
 * @see #i  [i] for INFO, [ib] for enableBorder log
 * @see #w  [w] for WARNING, [wb] for enableBorder log
 * @see #e  [e] for ERROR, [eb] for enableBorder log
 * @see #b  [b] for enableBorder log
 */
public class AppLog {
    private static final String TAG = "AppLog";
    private static final String APP_TAG = "JLogSimple";
    private static final String LOG_FILE_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    private static final String LOG_FILE_NAME = "jlog_app.log";
    private static final String LOG_DIR_NAME = "log";
    private static final long LOG_FILE_MAX_SIZE = 1024 * 1024 * 5;     // 5MB
    private static final long LOG_FILE_DEBUG_MAX_SIZE = 1024 * 1024 * 20;    // 20MB, debug mode
    private static final long LOG_MAX_TIME_MILLIS = 1000 * 60 * 60 * 24; // 24H

    /**
     * Initialize default log.
     */
    public static void init() {
        JLog.init(ALL);
    }

    /**
     * Initialize log with logLevel.
     *
     * @param logLevel The logLevel to print log level.
     */
    public static void init(int logLevel) {
        JLog.init(logLevel);
    }

    /**
     * Initialize log with logLevel and logTag.
     *
     * @param logLevel
     * @param logTag
     */
    public static void init(int logLevel, String logTag) {
        LogConfiguration.Builder builder = new LogConfiguration.Builder();
        builder.logLevel(logLevel);
        builder.tag(isEmptyStr(logTag) ? TAG : logTag);
        JLog.init(logLevel);
    }

    /**
     * Initialize log related configuration.
     *
     * @param context Android Context.
     */
    public static void initAndroidAndFileLogConfig(Context context, boolean isDebug) {
        LogConfiguration.Builder builder = new LogConfiguration.Builder();
        // specify log level, logs below this level won't be printed, default: LogLevel.ALL
        builder.logLevel(isDebug ? ALL : INFO);
        // specify TAG, default: "J-LOG"
        builder.tag(APP_TAG);
        LogConfiguration config = builder
                .build();
        // init the log file dir path
        String logFileDirPath = context.getFilesDir().getAbsolutePath();
        logFileDirPath += File.separator + LOG_DIR_NAME;
        // printer that print the log using android.util.Log
        Printer androidPrinter = new AndroidPrinter();
        // printer that print(save) the log to file
        Printer filePrinter = new ConditionMonitorFilePrinter
                // specify the directory path of log file(s)
                .Builder(logFileDirPath)
                // default: ChangelessFileNameGenerator("jlog.log")
                .fileNameGenerator(new ChangelessFileNameGenerator(LOG_FILE_NAME))
                // default: FileSizeBackupStrategy(1024 * 1024)
                .backupStrategy(new NeverBackupStrategy())
                // default: NeverCleanStrategy()
                .cleanStrategy(new FileMaxSizeAndMillisCleanStrategy(
                        isDebug ? LOG_FILE_DEBUG_MAX_SIZE : LOG_FILE_MAX_SIZE,
                        LOG_MAX_TIME_MILLIS))
                // default: DefaultFlattener
                .flattener(new DateFormatFlattener(LOG_FILE_DATE_FORMAT))
                .build();
        // init log config, specify log configuration and printers
        JLog.init(config, androidPrinter, filePrinter);

        // print app versionInfo and logFilePath
        String logFilePath = "App LogFilePath: " + logFileDirPath + File.separator + LOG_FILE_NAME;
        ib(TAG, "init: " + SystemCompat.lineSeparator
                + getAppVersionInfo() + SystemCompat.lineSeparator
                + logFilePath);
    }

    /**
     * Initialize log related configuration.
     *
     * @param context          Android Context.
     * @param logLevel         log Level.
     * @param logTag           log logTag.
     * @param logFileDirName   log File Dir Name.
     * @param logFileName      log File Name.
     * @param logFileMaxSize   log File MaxSize.
     * @param logMaxTimeMillis log MaxTimeMillis.
     * @param logDateFormat    log DateFormat.
     */
    public static void initAndroidAndFileLogConfig(Context context,
                                                   int logLevel,
                                                   String logTag,
                                                   String logFileDirName,
                                                   String logFileName,
                                                   long logFileMaxSize,
                                                   long logMaxTimeMillis,
                                                   String logDateFormat) {
        LogConfiguration config = new LogConfiguration.Builder()
                .logLevel(logLevel)
                .tag(isEmptyStr(logTag) ? TAG : logTag)
                .build();
        // init the log file dir path
        String logFileDirPath = context.getFilesDir().getAbsolutePath();
        logFileDirPath += File.separator
                + (isEmptyStr(logFileDirName) ? LOG_DIR_NAME : logFileDirName);
        // printer that print the log using android.util.Log
        Printer androidPrinter = new AndroidPrinter();
        // printer that print(save) the log to file
        Printer filePrinter = new ConditionMonitorFilePrinter
                .Builder(logFileDirPath)
                .fileNameGenerator(new ChangelessFileNameGenerator(
                        isEmptyStr(logFileName) ? LOG_FILE_NAME : logFileName))
                .backupStrategy(new NeverBackupStrategy())
                .cleanStrategy(new FileMaxSizeAndMillisCleanStrategy(logFileMaxSize, logMaxTimeMillis))
                .flattener(new DateFormatFlattener(
                        isEmptyStr(logDateFormat) ? LOG_FILE_DATE_FORMAT : logDateFormat))
                .build();
        // init log config, specify log configuration and printers
        JLog.init(config, androidPrinter, filePrinter);
        // print app versionInfo and logFilePath
        String logFilePath = "App LogFilePath: " + logFileDirPath + File.separator
                + (isEmptyStr(logFileName) ? LOG_FILE_NAME : logFileName);
        ib(TAG, "init: " + SystemCompat.lineSeparator
                + getAppVersionInfo() + SystemCompat.lineSeparator
                + logFilePath);
    }

    //***************************** verbose log *******************************//

    public static void v(Object msg) {
        JLog.v(msg);
    }

    public static void v(String msg) {
        JLog.v(msg);
    }

    public static void v(Object[] array) {
        JLog.v(array);
    }

    public static void v(String format, Object... args) {
        JLog.v(format, args);
    }

    public static void v(String msg, Throwable tw) {
        JLog.v(msg, tw);
    }

    public static void v(String prefix, Object msg) {
        JLog.v(prefix, msg);
    }

    public static void v(String prefix, String msg) {
        JLog.v(prefix, msg);
    }

    public static void v(String prefix, String msg, Throwable tw) {
        JLog.v(prefix, msg, tw);
    }

    public static void vb(Object msg) {
        JLog.enableBorder().v(msg);
    }

    public static void vb(String msg) {
        JLog.enableBorder().v(msg);
    }

    public static void vb(Object[] array) {
        JLog.enableBorder().v(array);
    }

    public static void vb(String format, Object... args) {
        JLog.enableBorder().v(format, args);
    }

    public static void vb(String msg, Throwable tw) {
        JLog.enableBorder().v(msg, tw);
    }

    public static void vb(String prefix, Object msg) {
        JLog.enableBorder().v(prefix, msg);
    }

    public static void vb(String prefix, String msg) {
        JLog.enableBorder().v(prefix, msg);
    }

    public static void vb(String prefix, String msg, Throwable tw) {
        JLog.enableBorder().v(prefix, msg, tw);
    }

    //***************************** debug log *******************************//

    public static void d(Object msg) {
        JLog.d(msg);
    }

    public static void d(String msg) {
        JLog.d(msg);
    }

    public static void d(Object[] array) {
        JLog.d(array);
    }

    public static void d(String format, Object... args) {
        JLog.d(format, args);
    }

    public static void d(String msg, Throwable tw) {
        JLog.d(msg, tw);
    }

    public static void d(String prefix, Object msg) {
        JLog.d(prefix, msg);
    }

    public static void d(String prefix, String msg) {
        JLog.d(prefix, msg);
    }

    public static void d(String prefix, String msg, Throwable tw) {
        JLog.d(prefix, msg, tw);
    }

    public static void db(Object msg) {
        JLog.enableBorder().d(msg);
    }

    public static void db(String msg) {
        JLog.enableBorder().d(msg);
    }

    public static void db(Object[] array) {
        JLog.enableBorder().d(array);
    }

    public static void db(String format, Object... args) {
        JLog.enableBorder().d(format, args);
    }

    public static void db(String msg, Throwable tw) {
        JLog.enableBorder().d(msg, tw);
    }

    public static void db(String prefix, Object msg) {
        JLog.enableBorder().d(prefix, msg);
    }

    public static void db(String prefix, String msg) {
        JLog.enableBorder().d(prefix, msg);
    }

    public static void db(String prefix, String msg, Throwable tw) {
        JLog.enableBorder().d(prefix, msg, tw);
    }

    //**************************** info log *******************************//

    public static void i(Object msg) {
        JLog.i(msg);
    }

    public static void i(String msg) {
        JLog.i(msg);
    }

    public static void i(Object[] array) {
        JLog.i(array);
    }

    public static void i(String format, Object... args) {
        JLog.i(format, args);
    }

    public static void i(String msg, Throwable tw) {
        JLog.i(msg, tw);
    }

    public static void i(String prefix, Object msg) {
        JLog.i(prefix, msg);
    }

    public static void i(String prefix, String msg) {
        JLog.i(prefix, msg);
    }

    public static void i(String prefix, String msg, Throwable tw) {
        JLog.i(prefix, msg, tw);
    }

    public static void ib(Object msg) {
        JLog.enableBorder().i(msg);
    }

    public static void ib(String msg) {
        JLog.enableBorder().i(msg);
    }

    public static void ib(Object[] array) {
        JLog.enableBorder().i(array);
    }

    public static void ib(String format, Object... args) {
        JLog.enableBorder().i(format, args);
    }

    public static void ib(String msg, Throwable tw) {
        JLog.enableBorder().i(msg, tw);
    }

    public static void ib(String prefix, Object msg) {
        JLog.enableBorder().i(prefix, msg);
    }

    public static void ib(String prefix, String msg) {
        JLog.enableBorder().i(prefix, msg);
    }

    public static void ib(String prefix, String msg, Throwable tw) {
        JLog.enableBorder().i(prefix, msg, tw);
    }

    //***************************** warn log *******************************//

    public static void w(Object msg) {
        JLog.w(msg);
    }

    public static void w(String msg) {
        JLog.w(msg);
    }

    public static void w(Object[] array) {
        JLog.w(array);
    }

    public static void w(String format, Object... args) {
        JLog.w(format, args);
    }

    public static void w(String msg, Throwable tw) {
        JLog.w(msg, tw);
    }

    public static void w(String prefix, Object msg) {
        JLog.w(prefix, msg);
    }

    public static void w(String prefix, String msg) {
        JLog.w(prefix, msg);
    }

    public static void w(String prefix, String msg, Throwable tw) {
        JLog.w(prefix, msg, tw);
    }

    public static void wb(Object msg) {
        JLog.enableBorder().w(msg);
    }

    public static void wb(String msg) {
        JLog.enableBorder().w(msg);
    }

    public static void wb(Object[] array) {
        JLog.enableBorder().w(array);
    }

    public static void wb(String format, Object... args) {
        JLog.enableBorder().w(format, args);
    }

    public static void wb(String msg, Throwable tw) {
        JLog.enableBorder().w(msg, tw);
    }

    public static void wb(String prefix, Object msg) {
        JLog.enableBorder().w(prefix, msg);
    }

    public static void wb(String prefix, String msg) {
        JLog.enableBorder().w(prefix, msg);
    }

    public static void wb(String prefix, String msg, Throwable tw) {
        JLog.enableBorder().w(prefix, msg, tw);
    }

    //***************************** error log *******************************//

    public static void e(Object msg) {
        JLog.e(msg);
    }

    public static void e(String msg) {
        JLog.e(msg);
    }

    public static void e(Object[] array) {
        JLog.e(array);
    }

    public static void e(String format, Object... args) {
        JLog.e(format, args);
    }

    public static void e(String msg, Throwable tw) {
        JLog.e(msg, tw);
    }

    public static void e(String prefix, Object msg) {
        JLog.e(prefix, msg);
    }

    public static void e(String prefix, String msg) {
        JLog.e(prefix, msg);
    }

    public static void e(String prefix, String msg, Throwable tw) {
        JLog.e(prefix, msg, tw);
    }

    public static void eb(Object msg) {
        JLog.enableBorder().e(msg);
    }

    public static void eb(String msg) {
        JLog.enableBorder().e(msg);
    }

    public static void eb(Object[] array) {
        JLog.enableBorder().e(array);
    }

    public static void eb(String format, Object... args) {
        JLog.enableBorder().e(format, args);
    }

    public static void eb(String msg, Throwable tw) {
        JLog.enableBorder().e(msg, tw);
    }

    public static void eb(String prefix, Object msg) {
        JLog.enableBorder().e(prefix, msg);
    }

    public static void eb(String prefix, String msg) {
        JLog.enableBorder().e(prefix, msg);
    }

    public static void eb(String prefix, String msg, Throwable tw) {
        JLog.enableBorder().e(prefix, msg, tw);
    }

    //***************************** XML format log *******************************//

    public static void xml(String xml) {
        if (!TextUtils.isEmpty(xml)) {
            try {
                JLog.xml(xml);
            } catch (Exception ex) {
                e(TAG, "xml", ex);
            }
        }
    }

    public static void xml(String prefix, String xml) {
        if (!TextUtils.isEmpty(xml)) {
            try {
                JLog.xml(prefix, xml);
            } catch (Exception ex) {
                e(TAG, "xml", ex);
            }
        }
    }

    public static void xmlb(String xml) {
        if (!TextUtils.isEmpty(xml)) {
            try {
                JLog.enableBorder().xml(xml);
            } catch (Exception ex) {
                e(TAG, "xmlb", ex);
            }
        }
    }

    public static void xmlb(String prefix, String xml) {
        if (!TextUtils.isEmpty(xml)) {
            try {
                JLog.enableBorder().xml(prefix, xml);
            } catch (Exception ex) {
                e(TAG, "xmlb", ex);
            }
        }
    }

    //***************************** JSON format log  *******************************//

    public static void json(String json) {
        if (!TextUtils.isEmpty(json)) {
            try {
                JLog.json(json);
            } catch (Exception ex) {
                e(TAG, "json", ex);
            }
        }
    }

    public static void json(String prefix, String json) {
        if (!TextUtils.isEmpty(json)) {
            try {
                JLog.json(prefix, json);
            } catch (Exception ex) {
                e(TAG, "json", ex);
            }
        }
    }

    public static void jsonb(String json) {
        if (!TextUtils.isEmpty(json)) {
            try {
                JLog.enableBorder().json(json);
            } catch (Exception ex) {
                e(TAG, "jsonb", ex);
            }
        }
    }

    public static void jsonb(String prefix, String json) {
        if (!TextUtils.isEmpty(json)) {
            try {
                JLog.enableBorder().json(prefix, json);
            } catch (Exception ex) {
                e(TAG, "jsonb", ex);
            }
        }
    }

    //***************************** enableBorder log *******************************//

    public static void b(int logLevel, Object msg) {
        JLog.enableBorder().log(logLevel, msg);
    }

    public static void b(int logLevel, String msg) {
        JLog.enableBorder().log(logLevel, msg);
    }

    public static void b(int logLevel, Object[] array) {
        JLog.enableBorder().log(logLevel, array);
    }

    public static void b(int logLevel, String format, Object... args) {
        JLog.enableBorder().log(logLevel, format, args);
    }

    public static void b(int logLevel, String msg, Throwable tw) {
        JLog.enableBorder().log(logLevel, msg, tw);
    }

    public static void b(int logLevel, String prefix, Object msg) {
        JLog.enableBorder().log(logLevel, prefix, msg);
    }

    public static void b(int logLevel, String prefix, String msg) {
        JLog.enableBorder().log(logLevel, prefix, msg);
    }

    public static void b(int logLevel, String prefix, String msg, Throwable tw) {
        JLog.enableBorder().log(logLevel, prefix, msg, tw);
    }

    private static boolean isEmptyStr(String str) {
        return TextUtils.isEmpty(str);
    }

}

```

## Thanks

Thanks to [Orhan Obut](https://github.com/orhanobut)'s [logger](https://github.com/orhanobut/logger), it give me a better understanding of the logger.

Thanks to [Elvis Hew](https://github.com/elvishew)'s [XLog](https://github.com/elvishew/xLog), it give me a good logcat framework, this JLog project is based on XLog(com.elvishew:xlog:1.7.2) to redevelopment.

## License

<pre>
Copyright 2020 Jiang Ming

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
</pre>