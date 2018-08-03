package com.tkachuk.cardholderapp.ui.auth.recoverPassword

import com.tkachuk.cardholderapp.data.AuthFireBase
import com.tkachuk.cardholderapp.data.IAuthFireBase


class RecoverPasswordPresenter : IRecoverPasswordContract.IRecoverPasswordPresenter {

    constructor()

    override fun recover(login: String) {

       AuthFireBase.recoverPassword(login, callback = object : IAuthFireBase.RecoverPasswordCallback{
           override fun showMsg(msg: String) {
               TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
           }

           override fun onRecover() {
               TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
           }
       })

    }
}