package com.example.greeknews.base;

import android.os.Environment;

import java.io.File;

public interface Constants {  //Constants 常量

    boolean isDebug = true;

    /*
    * Environment 环境 外界
    * getExternalStorageDirectory 获得外部存储目录
    * getAbsolutePath 获取相对路径
    * separator分离器 分隔符
    * FILE_PROVIDER_AUTHORITY  文件提供权威
    *
    * getCacheDir  获取缓存Dir
    */

    String PATH_SDCARD = Environment.getExternalStorageDirectory()
            .getAbsolutePath() + File.separator + "codeest"
            + File.separator + "GeekNews";

    String FILE_PROVIDER_AUTHORITY = "com.baidu.geek.fileprovider";

    //网络缓存地址
    String PATH_DATA = BaseApp.getInstance().getCacheDir()
            .getAbsolutePath()
            +File.separator + "data";

    String PATH_CACHE = PATH_DATA + "/NetCache";
    String DATA = "data";

    //夜间模式
    String MODE = "mode";
    String NIGHT_CURRENT_FRAG_POS = "fragment_pos";

}
