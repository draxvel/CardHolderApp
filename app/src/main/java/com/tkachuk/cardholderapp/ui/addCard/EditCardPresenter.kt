package com.tkachuk.cardholderapp.ui.addCard

import com.tkachuk.cardholderapp.data.carddata.CardDataFireBase
import com.tkachuk.cardholderapp.data.carddata.ICardDataFireBase
import com.tkachuk.cardholderapp.data.model.BusinessCard
import android.provider.ContactsContract
import android.content.Context
import android.content.Intent

class EditCardPresenter(private var context: Context, private var editView: EditCardContract.IEditView) : EditCardContract.IEditCardPresenter {

    override fun addToContactList(name: String, phone: String) {
        val intent = Intent(Intent.ACTION_INSERT)
        intent.type = ContactsContract.Contacts.CONTENT_TYPE

        intent.putExtra(ContactsContract.Intents.Insert.NAME, name)
        intent.putExtra(ContactsContract.Intents.Insert.PHONE, phone)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

    override fun addToServer(businessCard: BusinessCard, toFavoriteList: Boolean) {
        CardDataFireBase.save(businessCard, toFavoriteList, iSaveCallback = object : ICardDataFireBase.ISaveCallback {
            override fun onSave() {
                editView.showMsg("Saved to server")
            }

            override fun showMsg(msg: String) {
                editView.showMsg(msg)
            }
        })
    }

    override fun updateCard(businessCard: BusinessCard) {
        CardDataFireBase.updateCard(businessCard, iUpdateCallback = object : ICardDataFireBase.IUpdateCallback {
            override fun onEdit() {
                editView.showMsg("Updated")
            }

            override fun showMsg(msg: String) {
                editView.showMsg(msg)
            }
        })
    }
}