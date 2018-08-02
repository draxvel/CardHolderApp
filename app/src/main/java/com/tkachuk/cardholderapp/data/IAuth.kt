package com.tkachuk.cardholderapp.data

import com.tkachuk.cardholderapp.ui.IView

interface IAuth{

    interface SignInCallback: IView {
        fun onSignIn()
    }

    interface SignUpCallback: IView {
        fun onSignUp()
    }

    interface RecoverPasswordCallback: IView {
        fun onRecover()
    }

    fun isSignIn(): Boolean

    fun signIn(login: String, password: String, callback: SignInCallback)

    fun signUp(login: String, password: String, callback: SignUpCallback)

    fun recoverPassword(login: String, callback: RecoverPasswordCallback)

    fun signOut()
}
