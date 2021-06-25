package com.app.nl.graphql.testactivitytracker

import android.app.Activity
import java.util.LinkedList

class ActivityTrackerImpl : ActivityTracker {

    //private val createdActivityList = LinkedList<Activity>()
    private val resumedActivityList = LinkedList<Activity>()
    private val actionList = LinkedList<(currentActivity: Activity) -> Unit>()

    override var runningActivityCount: Int = 0
    private set
    override var createdActivityCount: Int = 0
    private set

    var wasStoppedActivityChangingConfiguration: Boolean = false
    private set

    override val hasForegroundActivity: Boolean
        get() = runningActivityCount > 0

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

    private val lastResumedActivity : Activity?
        get() = resumedActivityList.peekFirst()

    override val currentActivity: Activity?
        get() = lastResumedActivity


    fun activityCreated(activity: Activity) {
        createdActivityCount++
        //createdActivityList.addFirst(activity)
    }

    fun activityStarted(activity: Activity) {
    }

    fun activityResumed(activity: Activity) {
        runningActivityCount++
        resumedActivityList.addFirst(activity)
    }

    fun activityPaused(activity: Activity) {
        runningActivityCount--
        resumedActivityList.remove(activity)
    }

    fun activityStopped(activity: Activity) {
        wasStoppedActivityChangingConfiguration = activity.isChangingConfigurations
    }

    fun activityDestroyed(activity: Activity) {
        createdActivityCount--
        //createdActivityList.remove(activity)
    }

}