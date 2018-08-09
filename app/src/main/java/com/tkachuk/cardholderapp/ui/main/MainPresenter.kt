package com.tkachuk.cardholderapp.ui.main

import android.content.Context
import com.tkachuk.cardholderapp.data.auth.AuthFireBase
import com.tkachuk.cardholderapp.data.carddata.CardDataFireBase
import com.tkachuk.cardholderapp.data.carddata.ICardDataFireBase
import com.tkachuk.cardholderapp.data.model.BusinessCard
import com.tkachuk.cardholderapp.util.InternetConnection

class MainPresenter(val iMainView: IMainContract.IMainView, val context: Context)
                    : IMainContract.IMainPresenter {

    override fun logout(){
        AuthFireBase.signOut()
    }

    override fun loadCardList() {

        if (InternetConnection.isNetworkAvailable(context)) {

            iMainView.setVisibleRefresh(true)

            CardDataFireBase.load(iLoadCallback = object : ICardDataFireBase.ILoadCallback {
                override fun onLoad(list: List<BusinessCard>) {
                    iMainView.setCardList(list)
                    iMainView.setVisibleRefresh(false)
                }

                override fun showMsg(msg: String) {
                    iMainView.showMsg(msg)
                    iMainView.setVisibleRefresh(false)
                }
            })
        }else iMainView.showMsg("No Internet connection")
    }
}