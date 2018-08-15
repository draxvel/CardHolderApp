package com.tkachuk.cardholderapp.ui.main

import android.content.Context
import com.tkachuk.cardholderapp.data.auth.AuthFireBase
import com.tkachuk.cardholderapp.data.carddata.CardDataFireBase
import com.tkachuk.cardholderapp.data.carddata.ICardDataFireBase
import com.tkachuk.cardholderapp.data.model.BusinessCard
import com.tkachuk.cardholderapp.util.InternetConnection
import com.tkachuk.cardholderapp.util.PhoneBookManager

class MainPresenter(val iMainView: IMainContract.IMainView, private val context: Context)
    : IMainContract.IMainPresenter {

    private var localList: MutableList<BusinessCard>? = null

    override fun deleteCard(id: String) {
        CardDataFireBase.delete(id, iDeleteCallback = object : ICardDataFireBase.IDeleteCallback {
            override fun onDelete() {
                iMainView.showMsg("Deleted")
            }

            override fun showMsg(msg: String) {
                iMainView.showMsg(msg)
            }
        })
    }

    override fun logout() {
        AuthFireBase.signOut()
    }

    override fun loadCardList() {

        if (InternetConnection.isNetworkAvailable(context)) {

            iMainView.setVisibleRefresh(true)

            CardDataFireBase.load(iLoadCallback = object : ICardDataFireBase.ILoadCallback {
                override fun onLoad(list: List<BusinessCard>) {
                    iMainView.setCardList(list)
                    iMainView.setVisibleRefresh(false)
                    localList = list.toMutableList()
                }

                override fun showMsg(msg: String) {
                    iMainView.setVisibleRefresh(false)
                    iMainView.showMsg(msg)
                }
            })
        } else iMainView.showMsg("No Internet connection")
    }

    override fun searchCard(query: String) {
        val filteredOutPut: MutableList<BusinessCard> = mutableListOf()

        if (localList != null) {
            if (localList != null && localList!!.isNotEmpty()) {
                for (item in localList!!) {
                    if (item.name.toLowerCase().startsWith(query.toLowerCase())) {
                        filteredOutPut.add(item)
                    }
                }
                if (filteredOutPut.isNotEmpty()) {
                    iMainView.setCardList(filteredOutPut)
                }
            } else iMainView.showMsg("Empty List")
        }
    }

    override fun showListByCategory(category: Int) {
        val filteredOutPut: MutableList<BusinessCard> = mutableListOf()

        CardDataFireBase.load(iLoadCallback = object : ICardDataFireBase.ILoadCallback {
            override fun onLoad(list: List<BusinessCard>) {
                for (item in list) {
                    if (item.category == category) {
                        filteredOutPut.add(item)
                    }
                }

                iMainView.setCardList(filteredOutPut)
                iMainView.setVisibleRefresh(false)
                localList = list.toMutableList()
            }

            override fun showMsg(msg: String) {
                iMainView.setVisibleRefresh(false)
                iMainView.showMsg(msg)
            }
        })
    }

    override fun setPhoneBookList() {
        val phoneBookList: MutableList<BusinessCard>? = PhoneBookManager.getListOfContacts(context)
        if (phoneBookList != null) {
            localList?.addAll(phoneBookList)
        }
    }
}