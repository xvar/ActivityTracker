package com.app.nl.graphql.testactivitytracker

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AbsActivity(R.layout.activity_main) {

    override fun openNextActivity() {
        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
    }

}