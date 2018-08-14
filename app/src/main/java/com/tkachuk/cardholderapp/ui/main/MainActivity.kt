package com.tkachuk.cardholderapp.ui.main

import android.app.ActionBar
import android.app.AlertDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.*
import com.tkachuk.cardholderapp.R
import com.tkachuk.cardholderapp.data.model.BusinessCard
import kotlinx.android.synthetic.main.activity_main.*
import com.tkachuk.cardholderapp.ui.scanner.ScannerActivity

class MainActivity : AppCompatActivity(), IMainContract.IMainView {

    private lateinit var mainPresenter: MainPresenter
    private lateinit var cardAdapter: CardAdapter
    private var searchView: SearchView? = null

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

        val reversedList: List<BusinessCard> = list.reversed()
        val mList = reversedList.toMutableList()

        cardAdapter = CardAdapter(mList, this)
        rv_card_list.adapter = cardAdapter

        val itemTouchHelper = ItemTouchHelper(SwipeToDeleteHandler(this) {
            val id: String = cardAdapter.getIdByPosition(it.adapterPosition)
            mainPresenter.deleteCard(id)
            cardAdapter.removeObject(it.adapterPosition)
            cardAdapter.notifyItemRemoved(it.adapterPosition)
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

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        super.onPrepareOptionsMenu(menu)
        searchView = menu?.findItem(R.id.item_search)?.actionView as SearchView
        searchView?.setIconifiedByDefault(true)
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    mainPresenter.searchCard(newText)
                }
                return false
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item!=null) {
            when {
                item.itemId == R.id.logout -> {
                    mainPresenter.logout()
                    finish()
                    return true
                }
                item.itemId == R.id.item_category -> {
                    val adp = ArrayAdapter.createFromResource(this,
                            R.array.contact_category, android.R.layout.simple_spinner_item)
                    adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                    val sp = Spinner(this@MainActivity)
                    sp.layoutParams = LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT)
                    sp.adapter = adp

                    val builder = AlertDialog.Builder(this@MainActivity)
                    builder.setView(sp)
                    val alert = builder.create()

                    alert.setOnCancelListener {}
                    alert.show()

                    sp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                        override fun onNothingSelected(parent: AdapterView<*>?) {}

                        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                            val category = parent?.adapter?.getItemId(position)!!.toInt()

                            if(category==0) {
                                mainPresenter.loadCardList()
                            }else{
                                mainPresenter.showListByCategory(
                                        parent.adapter?.getItemId(position)!!.toInt())
                            }
                        }
                    }

                    return true
                }
                else -> super.onOptionsItemSelected(item)
            }
        }
        return true
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
