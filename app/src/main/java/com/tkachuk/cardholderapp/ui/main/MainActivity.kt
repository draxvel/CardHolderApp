package com.tkachuk.cardholderapp.ui.main

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.widget.Toast
import com.tkachuk.cardholderapp.R
import com.tkachuk.cardholderapp.data.model.BusinessCard
import kotlinx.android.synthetic.main.activity_main.*
import com.tkachuk.cardholderapp.ui.scanner.ScannerActivity

class MainActivity : AppCompatActivity(), IMainContract.IMainView {

    private lateinit var mainPresenter: MainPresenter
    private lateinit var cardAdapter: CardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initPresenter()
        initListener()
        initRecyclerView()
        mainPresenter.loadCardList()
    }

    private fun initListener() {
        floatingActionButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, ScannerActivity::class.java))
        }

        swipe_refresh.setOnRefreshListener {
            mainPresenter.loadCardList()
        }
    }

    private fun initPresenter() {
        mainPresenter = MainPresenter(this, applicationContext)
    }

    private fun initRecyclerView() {
        rv_card_list.layoutManager = LinearLayoutManager(this)
    }

    override fun setCardList(list: List<BusinessCard>) {
        cardAdapter = CardAdapter(list.reversed(), this)
        rv_card_list.adapter = cardAdapter

        val itemTouchHelper = ItemTouchHelper(SwipeToDeleteHandler(this) {
            if (it.adapterPosition == cardAdapter.itemCount) {
                it.setVisibleForEmtyView(true)
            }
            val id: String = cardAdapter.getIdByPosition(it.adapterPosition)
            mainPresenter.deleteCard(id)
            rv_card_list.adapter.notifyItemRemoved(it.adapterPosition)
        })
        itemTouchHelper.attachToRecyclerView(rv_card_list)
    }

    override fun showMsg(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
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
        } else super.onOptionsItemSelected(item)
    }

    override fun setVisibleRefresh(isVisible: Boolean) {
        swipe_refresh.isRefreshing = isVisible

        if (isVisible) {
            window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        } else {
            window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        }
    }
}
