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
import com.ps.eclip.utils.ViewCardBottomSheet

class HomeActivity : AppCompatActivity(), CardsAdapter.OnItemClickListener {

    private val binding: ActivityHomeBinding by lazy { ActivityHomeBinding.inflate(layoutInflater) }

    private val executors by lazy { AppExecutors.getInstance() }
    private val database by lazy { AppDatabase.getInstance(this) }

    private val list = ArrayList<EMVCardPreviewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        binding.recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.recyclerView.adapter = CardsAdapter(list).apply {
            setOnItemClickListener(this@HomeActivity)
        }
    }

    override fun onResume() {
        super.onResume()

        executors.diskIO().execute {
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

    override fun onClick(position: Int) {
        executors.diskIO().execute {
            database?.emvCardDao()?.getInfoWithId(list[position].id)?.let {
                val bottomSheet = ViewCardBottomSheet(it)
                bottomSheet.show(supportFragmentManager, "")
            }
        }
    }

    companion object {
        private const val TAG = "HomeActivity"
    }
}