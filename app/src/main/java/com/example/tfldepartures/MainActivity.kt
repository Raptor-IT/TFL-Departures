package com.example.tfldepartures

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tfldepartures.ui.DepartureBoardViewModel  // ← add this
import com.example.tfldepartures.ui.DepartureAdapter         // ← add this

class MainActivity : AppCompatActivity() {

    private val viewModel: DepartureBoardViewModel by viewModels()
    private lateinit var adapter: DepartureAdapter

    // ← REPLACE THESE WITH YOUR OWN VALUES
    private val STOP_ID = "940GZZLUASL"   // Angel tube station
    private val API_KEY = "\n" + "17f32986a2cf4f269929b249a7e73747"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val progressBar  = findViewById<ProgressBar>(R.id.progressBar)
        val tvError      = findViewById<TextView>(R.id.tvError)

        adapter = DepartureAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        viewModel.departures.observe(this) { adapter.updateData(it) }
        viewModel.loading.observe(this) {
            progressBar.visibility = if (it) View.VISIBLE else View.GONE
        }
        viewModel.error.observe(this) {
            tvError.visibility = if (it != null) View.VISIBLE else View.GONE
            tvError.text = it
        }

        viewModel.startLiveUpdates(STOP_ID, API_KEY)
    }
}
