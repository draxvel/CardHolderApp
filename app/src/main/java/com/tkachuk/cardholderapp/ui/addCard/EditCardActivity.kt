package com.tkachuk.cardholderapp.ui.addCard

import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.tkachuk.cardholderapp.R
import com.tkachuk.cardholderapp.data.model.BusinessCard
import com.tkachuk.cardholderapp.util.InternetConnection
import kotlinx.android.synthetic.main.activity_edit.*
import android.widget.ArrayAdapter

class EditCardActivity : AppCompatActivity(), EditCardContract.IEditView {

    private lateinit var editCardPresenter: EditCardPresenter
    private var isEdit: Boolean = false
    private var idForEditCard: String = ""
    private var isFavorite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        editCardPresenter = EditCardPresenter(applicationContext, this)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val adapter = ArrayAdapter.createFromResource(this,
                R.array.contact_category, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sp_tag.adapter = adapter

        if (intent.getSerializableExtra("map") != null) {
            val info: HashMap<String, String> =
                    intent.getSerializableExtra("map") as HashMap<String, String>
            setUpView(info)
            isEdit = false
        }

        if (intent.getSerializableExtra("card") != null) {
            val businessCard: BusinessCard =
                    intent.getSerializableExtra("card") as BusinessCard
            isEdit = true
            setUpView(businessCard)
        }

        iv_favorite.setOnClickListener {
            if(isFavorite){
                iv_favorite.setImageResource(R.mipmap.ic_star_blue_border)
            }else{
                iv_favorite.setImageResource(R.mipmap.ic_star_blue)
            }
        }
    }

    private fun setUpView(info: HashMap<String, String>) {
        et_name.setText(info["name"])
        et_description.setText(info["description"])
        et_location.setText(info["location"])
        et_site.setText(info["site"])
        et_phone.setText(info["phone"])
        et_email.setText(info["email"])
        iv_card.setImageURI(Uri.parse(info["photo"]))
    }

    private fun setUpView(card: BusinessCard) {
        et_name.setText(card.name)
        et_description.setText(card.description)
        et_location.setText(card.location)
        et_site.setText(card.site)
        et_phone.setText(card.phone)
        et_email.setText(card.email)
        sp_tag.setSelection(card.category, true)
        idForEditCard = card.id

        isFavorite = card.isFavorite
        if(card.isFavorite){
            iv_favorite.setImageResource(R.mipmap.ic_star_blue)
        }else iv_favorite.setImageResource(R.mipmap.ic_star_blue_border)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_add_card, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        et_name.error = null
        et_phone.error = null

        if (item != null && item.itemId == R.id.item_check) {
            when {
                et_name.text.isEmpty() -> et_name.error = getString(R.string.empty)
                et_phone.text.isEmpty() -> et_phone.error = getString(R.string.empty)
                else -> {
                    if (InternetConnection.isNetworkAvailable(applicationContext)) {

                        val businessCard = BusinessCard(
                                "",
                                et_name.text.toString(),
                                et_description.text.toString(),
                                et_site.text.toString(),
                                et_email.text.toString(),
                                et_phone.text.toString(),
                                et_location.text.toString(),
                                sp_tag.selectedItemPosition,
                                isFavorite,
                                true)
                        when {
                            !isEdit -> {
                                editCardPresenter.addToServer(businessCard, false)
                            }
                            isEdit -> {
                                businessCard.id = idForEditCard
                                editCardPresenter.updateCard(businessCard)
                            }
                        }

                        if (cb_phone.isChecked) {
                            editCardPresenter.addToContactList(et_name.text.toString(), et_phone.text.toString())
                        }
                        finish()
                        return true
                    } else showMsg("No Internet connection")
                }
            }
        }

        if (item != null && item.itemId == android.R.id.home) {
            finish()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun showMsg(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}