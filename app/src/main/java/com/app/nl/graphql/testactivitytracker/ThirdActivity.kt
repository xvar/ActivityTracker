package com.app.nl.graphql.testactivitytracker

import android.content.Intent

class ThirdActivity : AbsActivity(R.layout.activity_third) {

    override fun openNextActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = intent.flags or Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
        startActivity(intent)
    }

}