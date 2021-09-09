package com.ps.eclip.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.ps.eclip.R

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    fun openAddCardPage(view: View) {
        startActivity(Intent(this, EnterCardDetailsActivity::class.java))
    }

    companion object {
        private const val TAG = "HomeActivity"
    }
}