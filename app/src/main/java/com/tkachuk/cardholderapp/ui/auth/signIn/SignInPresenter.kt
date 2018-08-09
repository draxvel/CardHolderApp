package com.tkachuk.cardholderapp.ui.auth.signIn

import com.tkachuk.cardholderapp.data.auth.AuthFireBase
import com.tkachuk.cardholderapp.data.auth.IAuthFireBase
import com.tkachuk.cardholderapp.ui.auth.AuthActivity

class SignInPresenter(activity: AuthActivity) : ISignInContract.ISignInPresenter {

    private  var iAuth: com.tkachuk.cardholderapp.ui.auth.IAuth = activity

    override fun signIn(login: String, password: String) {
        AuthFireBase.signIn(login, password, callback = object : IAuthFireBase.SignInCallback{
            override fun onSignIn() {
                iAuth.showMainActivity()
            }

            override fun showMsg(msg: String) {
                iAuth.showMsg(msg)
            }
        })
    }

    override fun showRecoverPassword() {
        iAuth.showRecoverPassword()
    }

    override fun showSignUp() {
        iAuth.showSignUp()
    }
}