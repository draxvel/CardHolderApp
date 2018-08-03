package com.tkachuk.cardholderapp.ui.auth.recoverPassword

class IRecoverPasswordContract {

    interface IRecoverPasswordPresenter{
        fun recover(login: String)
        fun showSignIn()
    }
}