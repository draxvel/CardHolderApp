package com.tkachuk.cardholderapp.ui.auth.singUp

import com.tkachuk.cardholderapp.ui.auth.IAuth

class ISignUpContract {

    interface ISingUpView : IAuth

    interface ISignUpPresenter{
        fun signUp(login: String, password: String)
    }
}