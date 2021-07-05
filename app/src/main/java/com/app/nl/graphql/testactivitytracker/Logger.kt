package com.app.nl.graphql.testactivitytracker

import android.app.Activity
import android.util.Log

class Logger(
    private val createdInActivity: Activity
) {

    private val activityTracker = App.application.activityTracker

    private fun logMethod(methodName: String, activity: Activity) {
        Log.d("TrackerTest", "created in ${createdInActivity::class.java.simpleName} : $methodName, running in $activity ")
        Log.d("TrackerTest", "created = ${activityTracker.createdActivityCount}, resumed = ${activityTracker.runningActivityCount} ")
    }

    fun log(methodName: String) {
        activityTracker.post { logMethod(methodName, it) }
    }

}