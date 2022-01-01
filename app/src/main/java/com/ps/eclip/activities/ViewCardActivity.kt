package com.ps.eclip.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.ps.eclip.R
import com.ps.eclip.dao.AppDatabase
import com.ps.eclip.dao.AppExecutors
import com.ps.eclip.databinding.ActivityViewCardBinding
import com.ps.eclip.models.EMVCard

class ViewCardActivity : AppCompatActivity() {

    private val binding by lazy { ActivityViewCardBinding.inflate(layoutInflater) }

    private val executors by lazy { AppExecutors.getInstance() }
    private val database by lazy { AppDatabase.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val id = intent.extras?.getInt("card_id")


    }

    private fun loadCard(card: EMVCard) {

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}