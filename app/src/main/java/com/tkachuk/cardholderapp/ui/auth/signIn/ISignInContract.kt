package com.tkachuk.cardholderapp.ui.auth.signIn

class ISignInContract {

    interface ISignInPresenter {
        fun signIn(login: String, password: String)
        fun showSignUp()
        fun showRecoverPassword()
    }
}