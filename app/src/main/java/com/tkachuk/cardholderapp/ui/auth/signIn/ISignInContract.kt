package com.tkachuk.cardholderapp.ui.auth.signIn

import com.tkachuk.cardholderapp.ui.auth.IAuth

class ISignInContract {

    interface ISingInView : IAuth

    interface ISignInPresenter{
        fun signIn(login: String, password: String)
    }
}