package com.tkachuk.cardholderapp.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.tkachuk.cardholderapp.data.model.BusinessCard
import com.google.firebase.database.DatabaseReference

import java.util.*
import kotlin.collections.HashMap

object CardDataFireBase: ICardDataFireBase {

    private var currentUser = FirebaseAuth.getInstance().currentUser

    private var db: DatabaseReference = FirebaseDatabase.getInstance().getReference("Cards")

    override fun save(businessCard: BusinessCard, iSaveCallback: ICardDataFireBase.ISaveCallback) {
        val cardMap = HashMap<String, Any>()
        cardMap["id"] = UUID.randomUUID().toString()
        cardMap["userId"] = currentUser!!.uid
        cardMap["name"] = businessCard.name
        cardMap["description"] = businessCard.description
        cardMap["location"] = businessCard.location
        cardMap["site"] = businessCard.site
        cardMap["phone"] = businessCard.phone
        cardMap["email"] = businessCard.email

        db.setValue(cardMap).addOnSuccessListener {
            iSaveCallback.onSave()
        }.addOnFailureListener {
            exception -> iSaveCallback.showMsg(exception.toString())
        }
    }

    override fun load(iLoadCallback: ICardDataFireBase.ILoadCallback): List<BusinessCard> {
        return listOf()
    }

    override fun delete(id: String, iDeleteCallback: ICardDataFireBase.IDeleteCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun edit(businessCard: BusinessCard) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}