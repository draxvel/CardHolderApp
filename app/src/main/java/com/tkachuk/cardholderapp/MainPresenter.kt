package com.tkachuk.cardholderapp

import com.tkachuk.cardholderapp.data.AuthFireBase

class MainPresenter {

    fun logout(){
        AuthFireBase.signOut();
    }
}