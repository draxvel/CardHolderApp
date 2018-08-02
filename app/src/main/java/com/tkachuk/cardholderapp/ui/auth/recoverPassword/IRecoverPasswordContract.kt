package com.tkachuk.cardholderapp.ui.auth.recoverPassword

import com.tkachuk.cardholderapp.ui.auth.IAuth

class IRecoverPasswordContract {

    interface IRecoverPasswordView : IAuth

    interface IRecoverPasswordPresenter{
        fun recover(login: String)
    }
}