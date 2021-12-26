package com.ps.eclip.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ps.eclip.CardsAdapter
import com.ps.eclip.R
import com.ps.eclip.dao.AppDatabase
import com.ps.eclip.dao.AppExecutors
import com.ps.eclip.databinding.ActivityHomeBinding
import com.ps.eclip.models.EMVCardPreviewModel

class HomeActivity : AppCompatActivity() {

    private val binding: ActivityHomeBinding by lazy { ActivityHomeBinding.inflate(layoutInflater) }
    private val database by lazy { AppDatabase.getInstance(this) }

    private val list = ArrayList<EMVCardPreviewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        binding.recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.recyclerView.adapter = CardsAdapter(list)
    }

    override fun onResume() {
        super.onResume()

        AppExecutors.getInstance().diskIO().execute {
            database?.emvCardPreviewDao()?.loadAllPreviews()?.let {
                list.clear()
                list.addAll(it)
            }
            runOnUiThread { binding.recyclerView.adapter?.notifyDataSetChanged() }
        }
    }

    fun openAddCardPage(view: View) {
        startActivity(Intent(this, EnterCardDetailsActivity::class.java))
    }

    companion object {
        private const val TAG = "HomeActivity"
    }
}