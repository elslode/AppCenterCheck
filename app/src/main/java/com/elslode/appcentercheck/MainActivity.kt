package com.elslode.appcentercheck

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.AppCenter.isConfigured
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            Log.d("startsappcenter", "onCreate: ${Crashes.getLastSessionCrashReport()}")
            Crashes.generateTestCrash()
        }
       }

    override fun onStart() {
        super.onStart()
        configureAndStart()
    }

    fun configureAndStart() {
        if (!isConfigured()) {
            val appId = "79e28be5-b204-4e7d-a641-46e0ca011a3a"
            AppCenter.configure(application, appId)
            AppCenter.setLogLevel(Log.VERBOSE)
            AppCenter.setEnabled(true)
            Crashes.setEnabled(true)
            AppCenter.start(Analytics::class.java, Crashes::class.java)
        }
    }
}