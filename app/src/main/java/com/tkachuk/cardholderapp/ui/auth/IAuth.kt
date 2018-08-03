package com.tkachuk.cardholderapp.ui.auth

import com.tkachuk.cardholderapp.ui.IView

interface IAuth : IView {
    fun showSignIn()
    fun showSignUp()
    fun showRecoverPassword()
    fun showMainActivity()
}