package com.tkachuk.cardholderapp.util

import android.content.Context
import com.tkachuk.cardholderapp.data.model.BusinessCard
import android.provider.ContactsContract

object PhoneBookManager {
    fun getListOfContacts(context: Context): MutableList<BusinessCard> {
        val list = mutableListOf<BusinessCard>()
        val phones = context.contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null)
        while (phones.moveToNext()) {
            val name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
            val phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
            list.add(BusinessCard("", name, "", "", "", phoneNumber, "", 0, false, false))
        }
        phones.close()
        return list
    }
}