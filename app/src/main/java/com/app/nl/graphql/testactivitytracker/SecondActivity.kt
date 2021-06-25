package com.app.nl.graphql.testactivitytracker

import android.content.Intent

class SecondActivity : AbsActivity(R.layout.activity_second) {

    override fun openNextActivity() {
        val intent = Intent(this, ThirdActivity::class.java)
        startActivity(intent)
    }

}