package com.tkachuk.cardholderapp.ui.addCard

import com.tkachuk.cardholderapp.data.carddata.CardDataFireBase
import com.tkachuk.cardholderapp.data.carddata.ICardDataFireBase
import com.tkachuk.cardholderapp.data.model.BusinessCard
import android.provider.ContactsContract
import android.content.Context
import android.content.Intent

class AddNewCardPresenter(private var context: Context, private var iAddNewView: IAddNewCardContract.IAddNewView) : IAddNewCardContract.IAddNewPresenter {

    override fun addToContactList(name: String, phone: String) {
        val intent = Intent(Intent.ACTION_INSERT)
        intent.type = ContactsContract.Contacts.CONTENT_TYPE

        intent.putExtra(ContactsContract.Intents.Insert.NAME, name)
        intent.putExtra(ContactsContract.Intents.Insert.PHONE, phone)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

    override fun addToServer(businessCard: BusinessCard) {
        CardDataFireBase.save(businessCard, iSaveCallback = object : ICardDataFireBase.ISaveCallback {
            override fun onSave() {
                iAddNewView.showMsg("Saved to server")
            }

            override fun showMsg(msg: String) {
                iAddNewView.showMsg(msg)
            }
        })
    }
}