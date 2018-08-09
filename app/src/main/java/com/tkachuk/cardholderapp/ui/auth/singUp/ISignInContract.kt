package com.tkachuk.cardholderapp.ui.auth.singUp

class ISignUpContract {

    interface ISignUpPresenter {
        fun signUp(login: String, password: String)
        fun showSignIn()
        fun showRecoverPassword()
    }
}