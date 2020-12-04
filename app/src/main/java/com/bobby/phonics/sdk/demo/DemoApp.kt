package com.bobby.phonics.sdk.demo

import android.app.Application
import com.bobbyen.sdk.phonics.core.BobbyPhonicsSDK

/**
 * <pre>
 *     author : JayGoo
 *     e-mail : 1015121748@qq.com
 *     time   : 2020/11/30
 *     desc   :
 * </pre>
 */
class DemoApp : Application() {
    override fun onCreate() {
        super.onCreate()
        BobbyPhonicsSDK.instance.init(this)
        BobbyPhonicsSDK.instance.initSingSound("","")
    }
}