package com.tkachuk.cardholderapp.ui.main

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.tkachuk.cardholderapp.R
import kotlinx.android.synthetic.main.activity_main.*
import com.tkachuk.cardholderapp.ui.scanner.ScannerActivity


class MainActivity : AppCompatActivity() {

    private val mainPresenter: MainPresenter = MainPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initListener()
    }

    private fun initListener() {
        floatingActionButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, ScannerActivity::class.java))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return if (item != null && item.itemId == R.id.logout) {
            mainPresenter.logout()
            finish()
            true
        }
        else super.onOptionsItemSelected(item)
    }
}
