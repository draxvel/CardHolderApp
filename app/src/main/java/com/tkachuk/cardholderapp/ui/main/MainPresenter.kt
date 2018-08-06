package com.tkachuk.cardholderapp.ui.main

import com.tkachuk.cardholderapp.data.AuthFireBase

class MainPresenter {

    fun logout(){
        AuthFireBase.signOut();
    }
}