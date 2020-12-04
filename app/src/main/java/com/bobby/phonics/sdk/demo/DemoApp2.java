package com.bobby.phonics.sdk.demo;

import android.app.Application;

import com.bobbyen.sdk.phonics.core.BobbyPhonicsSDK;

/**
 * <pre>
 *     author : JayGoo
 *     e-mail : 1015121748@qq.com
 *     time   : 2020/12/04
 *     desc   :
 * </pre>
 */
class DemoApp2 extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        BobbyPhonicsSDK.instance.init(this);
        BobbyPhonicsSDK.instance.initSingSound("","");
    }
}
