# PluginTest
    两种Android插件框架测试比较

## dynamic-plugin-load
    该框架能够动态加载插件Apk，并可以实现插件中跳转Activity，前提是插件Apk中的Activity页面要
    继承DLBasePluginActivity或DLBasePluginFragmentActivity，启动方式需要使用DLIntent，并在宿
    主app中声明两种代理Activity或者service：
```
    宿主AndroidManifest.xml声明：
        <!--dynamic-load-lib配置-->
        <activity android:name="com.penck.dynamic.DLProxyActivity">
            <intent-filter>
                <action android:name="com.ryg.dynamicload.proxy.activity.VIEW" />

                <category android:name="android.intent.category.DEFAULT" />
            </intent-filter>
        </activity>
        <activity
            android:name="com.penck.dynamic.DLProxyFragmentActivity"
            android:label="@string/app_name">
            <intent-filter>
                <action android:name="com.ryg.dynamicload.proxy.fragmentactivity.VIEW" />

                <category android:name="android.intent.category.DEFAULT" />
            </intent-filter>
        </activity>

     插件Activity启动方式：
        DLIntent dlIntent = new DLIntent(getPackageName(), SecondActivity.class);
        startPluginActivityForResult(dlIntent,0);

```
## instrument-plugin-load
    该框架也可以动态加载插件Apk，但在测试时只能启动主Activity，不能在插件中启动另一个Activity，
    显示错误是没有在宿主app Manifest.xml中声明activity。使用该框架也需要在宿主AndroidManifest.xml
    声明：
```
    宿主AndroidManifest.xml声明：
     <activity android:name="com.penck.manager.DynamicActivity" />

```
## plugin-apk和plugin-apk2
   两个测试用的插件Apk，在工程主目录运行命令gradlew.bat assembleRelease，就会自动生成apk文件并发到
   宿主app的assets/plugin/目录下

## library
    该module仅仅为了测试宿主app从插件apk中获取信息
## Demo
   ![] (./art/plugin.gif)
## Thanks

  * https://github.com/singwhatiwanna/dynamic-load-apk
  * https://github.com/houkx/android-pluginmgr

## License
Copyright (c) 2017 QDPeng
