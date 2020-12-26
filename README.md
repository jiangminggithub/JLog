# JLog

Powerful and flexible Android and Java log libraries that print logs to logcat, console and files, or wherever you like.

## Dependency

Latest: [![](https://jitpack.io/v/jiangminggithub/JLog.svg)](https://jitpack.io/#jiangminggithub/JLog) 

1. [GitHub dependency config reference link](https://jitpack.io/#jiangminggithub/JLog/1.0.1) 

2. [Gitee dependency config reference link](https://jitpack.io/#jiangming_gitee/JLog/1.0.1) 

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
    // the following Github(1) and Gitee(2) can select any dependency
	dependencies {    
        // 1. JLog: https://github.com/jiangminggithub/JLog
        implementation 'com.github.jiangminggithub:JLog:1.0.1'
        // 2. JLog: https://gitee.com/jiangming_gitee/JLog
        implementation 'com.gitee.jiangming_gitee:JLog:1.0.1'
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

### 1. simple reference link

[Go to example project](https://github.com/jiangminggithub/JLog/tree/master/simpleApp)

[Go to how to use with AppLog](https://github.com/jiangminggithub/JLog/blob/master/simpleApp/src/main/java/com/jm/jlog/simple/utils/AppLog.java)

### 2. simple use code

```java

/**
 * 1. Default way to use.
 */
private void defaultLogTest() {
    Integer[] arrayTest = {1, 2, 3};
    JLog.v("---------------------------------- v default test -------------------------------------------");

    JLog.v(1);
    JLog.v("111");
    JLog.v(arrayTest);
    JLog.v("Error1", new NullPointerException("log test"));
    JLog.v("ame: %s, age = %d", "Jack", 18);
    JLog.v(TAG, 1);
    JLog.v(TAG, "111");
    JLog.v(TAG, "Error2", new NullPointerException("log test"));
    JLog.enableBorder().v(1);
    JLog.enableBorder().v("111");
    JLog.enableBorder().v(arrayTest);
    JLog.enableBorder().v("Error3", new NullPointerException("log test"));
    JLog.enableBorder().v("ame: %s, age = %d", "Jack", 18);
    JLog.enableBorder().v(TAG, 1);
    JLog.enableBorder().v(TAG, "111");
    JLog.enableBorder().v(TAG, "Error4", new NullPointerException("log test"));

    JLog.v("---------------------------------- d default test -------------------------------------------");

    JLog.d(1);
    JLog.d("111");
    JLog.d(arrayTest);
    JLog.d("Error1", new NullPointerException("log test"));
    JLog.d("ame: %s, age = %d", "Jack", 18);
    JLog.d(TAG, 1);
    JLog.d(TAG, "111");
    JLog.d(TAG, "Error2", new NullPointerException("log test"));
    JLog.enableBorder().d(1);
    JLog.enableBorder().d("111");
    JLog.enableBorder().d(arrayTest);
    JLog.enableBorder().d("Error3", new NullPointerException("log test"));
    JLog.enableBorder().d("ame: %s, age = %d", "Jack", 18);
    JLog.enableBorder().d(TAG, 1);
    JLog.enableBorder().d(TAG, "111");
    JLog.enableBorder().d(TAG, "Error4", new NullPointerException("log test"));

    JLog.v("---------------------------------- i default test -------------------------------------------");

    JLog.i(1);
    JLog.i("111");
    JLog.i(arrayTest);
    JLog.i("Error1", new NullPointerException("log test"));
    JLog.i("ame: %s, age = %d", "Jack", 18);
    JLog.i(TAG, 1);
    JLog.i(TAG, "111");
    JLog.i(TAG, "Error2", new NullPointerException("log test"));
    JLog.enableBorder().i(1);
    JLog.enableBorder().i("111");
    JLog.enableBorder().i(arrayTest);
    JLog.enableBorder().i("Error3", new NullPointerException("log test"));
    JLog.enableBorder().i("ame: %s, age = %d", "Jack", 18);
    JLog.enableBorder().i(TAG, 1);
    JLog.enableBorder().i(TAG, "111");
    JLog.enableBorder().i(TAG, "Error4", new NullPointerException("log test"));

    JLog.v("---------------------------------- w default test -------------------------------------------");

    JLog.w(1);
    JLog.w("111");
    JLog.w(arrayTest);
    JLog.w("Error1", new NullPointerException("log test"));
    JLog.w("ame: %s, age = %d", "Jack", 18);
    JLog.w(TAG, 1);
    JLog.w(TAG, "111");
    JLog.w(TAG, "Error2", new NullPointerException("log test"));
    JLog.enableBorder().w(1);
    JLog.enableBorder().w("111");
    JLog.enableBorder().w(arrayTest);
    JLog.enableBorder().w("Error3", new NullPointerException("log test"));
    JLog.enableBorder().w("ame: %s, age = %d", "Jack", 18);
    JLog.enableBorder().w(TAG, 1);
    JLog.enableBorder().w(TAG, "111");
    JLog.enableBorder().w(TAG, "Error4", new NullPointerException("log test"));

    JLog.v("---------------------------------- e default test -------------------------------------------");

    JLog.e(1);
    JLog.e("111");
    JLog.e(arrayTest);
    JLog.e("Error1", new NullPointerException("log test"));
    JLog.e("ame: %s, age = %d", "Jack", 18);
    JLog.e(TAG, 1);
    JLog.e(TAG, "111");
    JLog.e(TAG, "Error2", new NullPointerException("log test"));
    JLog.enableBorder().e(1);
    JLog.enableBorder().e("111");
    JLog.enableBorder().e(arrayTest);
    JLog.enableBorder().e("Error3", new NullPointerException("log test"));
    JLog.enableBorder().e("ame: %s, age = %d", "Jack", 18);
    JLog.enableBorder().e(TAG, 1);
    JLog.enableBorder().e(TAG, "111");
    JLog.enableBorder().e(TAG, "Error4", new NullPointerException("log test"));

    JLog.v("---------------------------------- b default test -------------------------------------------");

    JLog.log(LogLevel.INFO, 1);
    JLog.log(LogLevel.INFO, "111");
    JLog.log(LogLevel.INFO, arrayTest);
    JLog.log(LogLevel.INFO, "Error1", new NullPointerException("log test"));
    JLog.log(LogLevel.INFO, "ame: %s, age = %d", "Jack", 18);
    JLog.log(LogLevel.INFO, TAG, 1);
    JLog.log(LogLevel.INFO, TAG, "111");
    JLog.log(LogLevel.INFO, TAG, "Error2", new NullPointerException("log test"));

    JLog.v("---------------------------------- json default test -------------------------------------------");

    String json = getResources().getString(R.string.json);
    JLog.enableBorder().json(TAG, json);

    // json string format utils
    JLog.i(TAG, "----> json string format test \n" + LogUtils.formatJson(json));

    JLog.v("---------------------------------- xml default test -------------------------------------------");

    String xml = getResources().getString(R.string.xml);
    JLog.enableBorder().xml(TAG, xml);

    // xml string format utils
    JLog.i(TAG, "----> xml string format test \n" + LogUtils.formatXml(xml));
}


/**
 * 2. Custom configuration to use.
 * <p>
 * Note: the {@link AppLog} is packaged based on {@link JLog} according to the situation of "android app".
 * Of course, you can also use {@link JLog} directly.
 * 
 * <p>
 * Reference: AppLog link
 *            https://github.com/jiangminggithub/JLog/blob/master/simpleApp/src/main/java/com/jm/jlog/simple/utils/AppLog.java}
 */
private void configLogTest() {
    // Use configuration to use with AppLog
    AppLog.initAndroidAndFileLogConfig(getApplicationContext(), BuildConfig.DEBUG);

    Integer[] arrayTest = {1, 2, 3};
    AppLog.v("---------------------------------- v config test -------------------------------------------");

    AppLog.v(1);
    AppLog.v("111");
    AppLog.v(arrayTest);
    AppLog.v("Error1", new NullPointerException("log test"));
    AppLog.v("ame: %s, age = %d", "Jack", 18);
    AppLog.v(TAG, 1);
    AppLog.v(TAG, "111");
    AppLog.v(TAG, "Error2", new NullPointerException("log test"));
    AppLog.vb(1);
    AppLog.vb("111");
    AppLog.vb(arrayTest);
    AppLog.vb("Error3", new NullPointerException("log test"));
    AppLog.vb("ame: %s, age = %d", "Jack", 18);
    AppLog.vb(TAG, 1);
    AppLog.vb(TAG, "111");
    AppLog.vb(TAG, "Error4", new NullPointerException("log test"));

    AppLog.v("---------------------------------- d config test -------------------------------------------");

    AppLog.d(1);
    AppLog.d("111");
    AppLog.d(arrayTest);
    AppLog.d("Error1", new NullPointerException("log test"));
    AppLog.d("ame: %s, age = %d", "Jack", 18);
    AppLog.d(TAG, 1);
    AppLog.d(TAG, "111");
    AppLog.d(TAG, "Error2", new NullPointerException("log test"));
    AppLog.db(1);
    AppLog.db("111");
    AppLog.db(arrayTest);
    AppLog.db("Error3", new NullPointerException("log test"));
    AppLog.db("ame: %s, age = %d", "Jack", 18);
    AppLog.db(TAG, 1);
    AppLog.db(TAG, "111");
    AppLog.db(TAG, "Error4", new NullPointerException("log test"));

    AppLog.v("---------------------------------- i config test -------------------------------------------");

    AppLog.i(1);
    AppLog.i("111");
    AppLog.i(arrayTest);
    AppLog.i("Error1", new NullPointerException("log test"));
    AppLog.i("ame: %s, age = %d", "Jack", 18);
    AppLog.i(TAG, 1);
    AppLog.i(TAG, "111");
    AppLog.i(TAG, "Error2", new NullPointerException("log test"));
    AppLog.ib(1);
    AppLog.ib("111");
    AppLog.ib(arrayTest);
    AppLog.ib("Error3", new NullPointerException("log test"));
    AppLog.ib("ame: %s, age = %d", "Jack", 18);
    AppLog.ib(TAG, 1);
    AppLog.ib(TAG, "111");
    AppLog.ib(TAG, "Error4", new NullPointerException("log test"));

    AppLog.v("---------------------------------- w config test -------------------------------------------");

    AppLog.w(1);
    AppLog.w("111");
    AppLog.w(arrayTest);
    AppLog.w("Error1", new NullPointerException("log test"));
    AppLog.w("ame: %s, age = %d", "Jack", 18);
    AppLog.w(TAG, 1);
    AppLog.w(TAG, "111");
    AppLog.w(TAG, "Error2", new NullPointerException("log test"));
    AppLog.wb(1);
    AppLog.wb("111");
    AppLog.wb(arrayTest);
    AppLog.wb("Error3", new NullPointerException("log test"));
    AppLog.wb("ame: %s, age = %d", "Jack", 18);
    AppLog.wb(TAG, 1);
    AppLog.wb(TAG, "111");
    AppLog.wb(TAG, "Error4", new NullPointerException("log test"));

    AppLog.v("---------------------------------- e config test -------------------------------------------");

    AppLog.e(1);
    AppLog.e("111");
    AppLog.e(arrayTest);
    AppLog.e("Error1", new NullPointerException("log test"));
    AppLog.e("ame: %s, age = %d", "Jack", 18);
    AppLog.e(TAG, 1);
    AppLog.e(TAG, "111");
    AppLog.e(TAG, "Error2", new NullPointerException("log test"));
    AppLog.eb(1);
    AppLog.eb("111");
    AppLog.eb(arrayTest);
    AppLog.eb("Error3", new NullPointerException("log test"));
    AppLog.eb("ame: %s, age = %d", "Jack", 18);
    AppLog.eb(TAG, 1);
    AppLog.eb(TAG, "111");
    AppLog.eb(TAG, "Error4", new NullPointerException("log test"));

    AppLog.v("---------------------------------- b config test -------------------------------------------");

    AppLog.b(LogLevel.INFO, 1);
    AppLog.b(LogLevel.INFO, "111");
    AppLog.b(LogLevel.INFO, arrayTest);
    AppLog.b(LogLevel.INFO, "Error1", new NullPointerException("log test"));
    AppLog.b(LogLevel.INFO, "ame: %s, age = %d", "Jack", 18);
    AppLog.b(LogLevel.INFO, TAG, 1);
    AppLog.b(LogLevel.INFO, TAG, "111");
    AppLog.b(LogLevel.INFO, TAG, "Error2", new NullPointerException("log test"));

    AppLog.v("---------------------------------- json config test -------------------------------------------");

    String json = getResources().getString(R.string.json);
    AppLog.jsonb(TAG, json);

    // json string format utils
    AppLog.i(TAG, "----> json string format test \n" + LogUtils.formatJson(json));

    AppLog.v("---------------------------------- xml config test -------------------------------------------");

    String xml = getResources().getString(R.string.xml);
    AppLog.xmlb(TAG, xml);

    // xml string format utils
    AppLog.i(TAG, "----> xml string format test \n" + LogUtils.formatXml(xml));
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