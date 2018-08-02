package com.tkachuk.cardholderapp.ui.auth.recoverPassword

import com.tkachuk.cardholderapp.data.Auth
import com.tkachuk.cardholderapp.data.IAuth


class RecoverPasswordPresenter : IRecoverPasswordContract.IRecoverPasswordPresenter {
    override fun recover(login: String) {

       Auth.recoverPassword(login, callback = object : IAuth.RecoverPasswordCallback{
           override fun showMsg(msg: String) {
               TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
           }

           override fun onRecover() {
               TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
           }
       })

    }
}