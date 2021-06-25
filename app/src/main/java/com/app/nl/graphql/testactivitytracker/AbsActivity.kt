package com.app.nl.graphql.testactivitytracker

import android.os.Bundle
import android.widget.Button
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

abstract class AbsActivity(@LayoutRes contentId: Int) : AppCompatActivity(contentId) {

    private val logger = Logger(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        findViewById<Button>(R.id.next).setOnClickListener {
            openNextActivity()
        }
        logger.log("onCreate")
    }

    override fun onStart() {
        super.onStart()
        logger.log("onStart")
    }

    override fun onResume() {
        super.onResume()
        logger.log("onResume")
    }

    override fun onPause() {
        super.onPause()
        logger.log("onPause")
    }

    override fun onStop() {
        super.onStop()
        logger.log("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        logger.log("onDestroy")
    }

    abstract fun openNextActivity()

}