package com.pjpsystems.grameentest.architecture.application

import android.app.Application
import android.util.Log
import androidx.viewbinding.BuildConfig
import timber.log.Timber

class GTApplication: Application() {
    override fun onCreate() {
        super.onCreate()
      //  if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
       // } else {
         //   Timber.plant(CrashReportingTree())
        //}
    }

    private class CrashReportingTree : Timber.Tree() {

        private val KEY_PRIORITY = "key_priority"
        private val KEY_TAG = "tag"
        private val KEY_MESSAGE = "message"

        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
            when (priority) {
                Log.VERBOSE, Log.DEBUG, Log.INFO -> return

                else -> {
              /*      FirebaseCrashlytics.getInstance().setCustomKey(KEY_PRIORITY, priority)
                    tag?.let { FirebaseCrashlytics.getInstance().setCustomKey(KEY_TAG, it) }
                    FirebaseCrashlytics.getInstance().setCustomKey(KEY_MESSAGE, message)

                    if (t == null) {
                        FirebaseCrashlytics.getInstance().recordException(Exception(message))
                    } else {
                        FirebaseCrashlytics.getInstance().recordException(t)
                    }*/
                }
            }
        }
    }
}