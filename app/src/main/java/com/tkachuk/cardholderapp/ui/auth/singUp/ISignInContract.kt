package com.tkachuk.cardholderapp.ui.auth.singUp

import com.tkachuk.cardholderapp.ui.auth.IAuth

class ISignUpContract {

    interface ISignUpPresenter{
        fun signUp(login: String, password: String)
        fun showSignIn()
        fun showRecoverPassword()
    }
}