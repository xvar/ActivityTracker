package com.app.nl.graphql.testactivitytracker

import android.app.Activity
import android.app.Application
import android.os.Bundle

class App : Application() {

    companion object Singleton {
        lateinit var application: App
    }

    private val _activityTracker = ActivityTrackerImpl()
    val activityTracker : ActivityTracker = _activityTracker

    override fun onCreate() {
        super.onCreate()
        Singleton.application = this
        this.registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                _activityTracker.activityCreated(activity)
            }

            override fun onActivityStarted(activity: Activity) {
                if (_activityTracker.runningActivityCount == 1 &&
                    !_activityTracker.wasStoppedActivityChangingConfiguration
                ) {
                    //app inside check
                }
            }

            override fun onActivityResumed(activity: Activity) {
                _activityTracker.activityResumed(activity)
            }

            override fun onActivityPaused(activity: Activity) {
                _activityTracker.activityPaused(activity)
            }

            override fun onActivityStopped(activity: Activity) {
                _activityTracker.activityStopped(activity)
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

            }

            override fun onActivityDestroyed(activity: Activity) {
                _activityTracker.activityDestroyed(activity)
            }
        })
    }
}