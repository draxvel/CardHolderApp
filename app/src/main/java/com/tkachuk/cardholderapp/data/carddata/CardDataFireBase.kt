package com.tkachuk.cardholderapp.data.carddata

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.tkachuk.cardholderapp.data.model.BusinessCard

import java.util.*
import kotlin.collections.HashMap

object CardDataFireBase : ICardDataFireBase {

    private var currentUser = FirebaseAuth.getInstance().currentUser

    private var db: DatabaseReference = FirebaseDatabase.getInstance().getReference("Cards")

    override fun save(businessCard: BusinessCard, iSaveCallback: ICardDataFireBase.ISaveCallback) {
        val cardMap = HashMap<String, Any>()
        val id: String = UUID.randomUUID().toString()
        cardMap["id"] = id
        cardMap["userId"] = currentUser!!.uid
        cardMap["name"] = businessCard.name
        cardMap["description"] = businessCard.description
        cardMap["location"] = businessCard.location
        cardMap["site"] = businessCard.site
        cardMap["phone"] = businessCard.phone
        cardMap["email"] = businessCard.email
        cardMap["timestamp"] = ServerValue.TIMESTAMP

        db.child(id).setValue(cardMap).addOnSuccessListener {
            iSaveCallback.onSave()
        }.addOnFailureListener { exception ->
            iSaveCallback.showMsg(exception.toString())
        }
    }

    override fun load(iLoadCallback: ICardDataFireBase.ILoadCallback) {
        db.orderByChild("timestamp").limitToLast(50).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val myList: MutableList<BusinessCard> = mutableListOf()

                for (d in dataSnapshot.children) {
                    val card: BusinessCard? = d.getValue(BusinessCard::class.java)
                    if (card != null) {
                        myList.add(card)
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

    override fun edit(businessCard: BusinessCard) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}