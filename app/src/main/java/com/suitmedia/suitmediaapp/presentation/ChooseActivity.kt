package com.suitmedia.suitmediaapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.suitmedia.suitmediaapp.R
import com.suitmedia.suitmediaapp.data.User
import com.suitmedia.suitmediaapp.databinding.ActivityChooseBinding

class ChooseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChooseBinding
    private lateinit var viewModel: ChooseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChooseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.thirdToolbar
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Third Screen"

        val layoutManager = LinearLayoutManager(this)
        binding.rvUsers.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvUsers.addItemDecoration(itemDecoration)

        val factory = ViewModelFactory.getInstance()
        viewModel = ViewModelProvider(this, factory)[ChooseViewModel::class.java]

        val adapter = ListUserAdapter(this)
        binding.rvUsers.adapter = adapter

        viewModel.users.observe(this) {
            Log.v("ChooseActivity", "Triggered")
            adapter.submitData(lifecycle, it)
        }

        val pullToRefresh = binding.pullRefresh

        pullToRefresh.setOnRefreshListener {
            viewModel.fetchUsers()
            pullToRefresh.isRefreshing = false
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed() // Call onBackPressed() to go back to the previous activity
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}