package com.tkachuk.cardholderapp.data.carddata

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.tkachuk.cardholderapp.data.model.BusinessCard

import java.util.*
import kotlin.collections.HashMap

object CardDataFireBase : ICardDataFireBase {

    private var db: DatabaseReference = FirebaseDatabase.getInstance().getReference("Cards")
            .child(FirebaseAuth.getInstance().currentUser!!.uid)

    override fun save(businessCard: BusinessCard, toFavoriteList: Boolean, iSaveCallback: ICardDataFireBase.ISaveCallback) {
        val cardMap = HashMap<String, Any>()
        val id: String = UUID.randomUUID().toString()
        cardMap["id"] = id
        cardMap["userId"] = FirebaseAuth.getInstance().currentUser!!.uid
        cardMap["name"] = businessCard.name
        cardMap["category"] = businessCard.category
        cardMap["description"] = businessCard.description
        cardMap["location"] = businessCard.location
        cardMap["site"] = businessCard.site
        cardMap["phone"] = businessCard.phone
        cardMap["email"] = businessCard.email
        cardMap["timestamp"] = ServerValue.TIMESTAMP
        cardMap["isFavorite"] = businessCard.isFavorite
        cardMap["isServerValue"] = businessCard.isServerValue
        db.child(id).setValue(cardMap).addOnSuccessListener {
            iSaveCallback.onSave()
        }.addOnFailureListener { exception ->
            iSaveCallback.showMsg(exception.toString())
        }
    }

    override fun load(fromFavoriteList: Boolean, iLoadCallback: ICardDataFireBase.ILoadCallback) {
        db.orderByChild("timestamp").limitToLast(50).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val myList: MutableList<BusinessCard> = mutableListOf()

                if(fromFavoriteList){
                    for (d in dataSnapshot.children) {
                        val card: BusinessCard? = d.getValue(BusinessCard::class.java)
                        if (card != null) {
                            if (fromFavoriteList && d.child("isFavorite").value == true)
                                myList.add(card)
                        }
                    }
                }else{
                    for (d in dataSnapshot.children) {
                        val card: BusinessCard? = d.getValue(BusinessCard::class.java)
                        if (card != null){
                            card.isFavorite = d.child("isFavorite").value as Boolean
                            myList.add(card)
                        }
                    }
                }

                if (myList.isNotEmpty()) {
                    iLoadCallback.onLoad(myList)
                } else iLoadCallback.showMsg("Empty!")
            }

            override fun onCancelled(error: DatabaseError) {
                iLoadCallback.showMsg(error.message)
            }
        })
    }

    override fun delete(id: String, iDeleteCallback: ICardDataFireBase.IDeleteCallback) {
        db.child(id).removeValue().addOnSuccessListener {
            iDeleteCallback.onDelete()
        }
                .addOnFailureListener {
                    iDeleteCallback.showMsg(it.message.toString())
                }
    }

    override fun updateCard(businessCard: BusinessCard, iUpdateCallback: ICardDataFireBase.IUpdateCallback) {
        val cardMap = HashMap<String, Any>()
        cardMap["id"] = businessCard.id
        cardMap["userId"] = FirebaseAuth.getInstance().currentUser!!.uid
        cardMap["name"] = businessCard.name
        cardMap["description"] = businessCard.description
        cardMap["location"] = businessCard.location
        cardMap["category"] = businessCard.category
        cardMap["site"] = businessCard.site
        cardMap["phone"] = businessCard.phone
        cardMap["email"] = businessCard.email
        cardMap["isFavorite"] = businessCard.isFavorite
        cardMap["isServerValue"] = businessCard.isServerValue

        db.child(businessCard.id).updateChildren(cardMap).addOnSuccessListener {
            iUpdateCallback.onEdit()
        }.addOnFailureListener { exception ->
            iUpdateCallback.showMsg(exception.toString())
        }
    }
}