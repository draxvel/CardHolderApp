package com.tkachuk.cardholderapp.ui.addCard

import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.tkachuk.cardholderapp.R
import com.tkachuk.cardholderapp.data.model.BusinessCard
import kotlinx.android.synthetic.main.activity_addnewcard.*

class AddNewCardActivity: AppCompatActivity(), IAddNewCardContract.IAddNewView {

    private lateinit var addNewCardPresenter: AddNewCardPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addnewcard)

        addNewCardPresenter = AddNewCardPresenter(applicationContext, this)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        if(intent.getSerializableExtra("map")!=null) {
            val info: HashMap<String, String> =
                    intent.getSerializableExtra("map") as HashMap<String, String>
            setUpView(info)
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
                    addNewCardPresenter.addToServer(BusinessCard(
                            0,
                            et_name.text.toString(),
                            et_description.text.toString(),
                            et_site.text.toString(),
                            et_email.text.toString(),
                            et_phone.text.toString(),
                            et_location.toString()))

                    if(cb_phone.isChecked){
                        addNewCardPresenter.addToContactList(et_name.text.toString(), et_phone.text.toString())
                    }
                    finish()
                    return true
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