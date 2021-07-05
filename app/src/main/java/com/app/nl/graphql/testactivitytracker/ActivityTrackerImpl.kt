package com.app.nl.graphql.testactivitytracker

import android.app.Activity
import java.util.LinkedList

class ActivityTrackerImpl constructor() : ActivityTracker {

    private val resumedActivityList = LinkedList<ActivityRecord>()
    private val actionList = LinkedList<(currentActivity: Activity) -> Unit>()

    override val runningActivityCount: Int
        get() = resumedActivityList.size

    override var createdActivityCount: Int = 0
        private set

    var wasStoppedActivityChangingConfiguration: Boolean = false
        private set

    override val hasForegroundActivity: Boolean
        get() = runningActivityCount > 0

    /**
     * If submitted in [onResume, onPause) action will run in current activity,
     * Otherwise it will be executed in next activity
     */
    override fun post(action: (currentActivity: Activity) -> Unit) {
        actionList.add(action)
        val current = currentActivity
        if (current != null) {
            runActions(current)
            return
        }
    }

    private fun runActions(activity: Activity) {
        val iterator = actionList.listIterator()
        while (iterator.hasNext()) {
            val action = iterator.next()
            if (activity.isFinishing.not()) {
                action(activity)
                iterator.remove()
            }
        }
    }

    private val lastResumedActivity: Activity?
        get() = resumedActivityList.maxByOrNull { it.lastResumedTime }?.activity

    override val currentActivity: Activity?
        get() = lastResumedActivity


    fun activityCreated(activity: Activity) {
        createdActivityCount++
    }

    fun activityStarted(activity: Activity) {
    }

    fun activityResumed(activity: Activity) {
        resumedActivityList.removeAll { it.activity == activity }
        resumedActivityList.addFirst(ActivityRecord(activity, System.currentTimeMillis()))
    }

    fun activityPaused(activity: Activity) {
        resumedActivityList.removeAll { it.activity == activity }
    }

    fun activityStopped(activity: Activity) {
        wasStoppedActivityChangingConfiguration = activity.isChangingConfigurations
    }

    fun activityDestroyed(activity: Activity) {
        createdActivityCount--
    }

    private data class ActivityRecord(
        val activity: Activity,
        val lastResumedTime: Long
    )
}