package com.bobby.phonics.sdk.demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bobbyen.sdk.phonics.core.BobbyPhonicsSDK
import com.bobbyen.sdk.phonics.core.OnLearnCallback
import com.bobbyen.sdk.phonics.core.UserType
import com.hjq.permissions.OnPermission
import com.hjq.permissions.Permission
import com.hjq.permissions.XXPermissions
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject


class MainActivity : AppCompatActivity(), OnLearnCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestPermission()

        BobbyPhonicsSDK.instance.setOnLearnCallback(this)

        val accessToken = ""
        tv_start?.setOnClickListener {
            if (accessToken.isEmpty()){
                Toast.makeText(applicationContext,"请先获取AccessToken!",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            BobbyPhonicsSDK.instance.startLearn(
                this,
                accessToken,
                "1",
                "123",
                UserType.EXPERIENCE,
                true
            )
        }

        tv_clear?.setOnClickListener {
            BobbyPhonicsSDK.instance.clearCache()
            Toast.makeText(applicationContext,"清除完毕!",Toast.LENGTH_LONG).show()
        }
    }

    private fun requestPermission(){
        val list = arrayListOf(
            Permission.RECORD_AUDIO
        )
        XXPermissions.with(this)
            .permission(list)
            .request(object : OnPermission {
                override fun hasPermission(granted: List<String>, all: Boolean) {
                }

                override fun noPermission(denied: List<String>, never: Boolean) {
                }
            })
    }

    override fun onResume() {
        super.onResume()
        // 会大幅提高游戏启动速度
        BobbyPhonicsSDK.instance.preload()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        BobbyPhonicsSDK.instance.onActivityResult(requestCode, resultCode, data)
    }

    override fun onLearnResult(jsonObject: JSONObject?) {
		if (jsonObject == null) {
			Log.v("BobbyPhonicsSDK","没学习")
		} else {
			Log.v("BobbyPhonicsSDK", "学习结果：$jsonObject")
		}
    }
}