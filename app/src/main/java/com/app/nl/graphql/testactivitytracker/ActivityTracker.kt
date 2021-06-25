package com.app.nl.graphql.testactivitytracker

import android.app.Activity
import androidx.annotation.UiThread

@UiThread //i.e. non thread-safe)
interface ActivityTracker : CurrentActivityProvider {

    val runningActivityCount: Int
    val createdActivityCount: Int
    val hasForegroundActivity : Boolean

    /**
     * Run this action immediately, if the current activity is up and running,
     * queue the action otherwise
     */
    fun post(
        action: (currentActivity: Activity) -> Unit
    )

    //todo smth for action handler context

}