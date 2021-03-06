package com.tkachuk.cardholderapp.ui.auth.singUp

import com.tkachuk.cardholderapp.data.auth.AuthFireBase
import com.tkachuk.cardholderapp.data.auth.IAuthFireBase
import com.tkachuk.cardholderapp.ui.auth.AuthActivity

class SignUpPresenter(activity: AuthActivity) : ISignUpContract.ISignUpPresenter {

    private var iAuth: com.tkachuk.cardholderapp.ui.auth.IAuth = activity

    override fun signUp(login: String, password: String) {
        AuthFireBase.signUp(login, password, callback = object : IAuthFireBase.SignUpCallback {
            override fun onSignUp() {
                iAuth.showMsg("SignUp")
                iAuth.showMainActivity()
            }

            override fun showMsg(msg: String) {
                iAuth.showMsg(msg)
            }
        })
    }

    override fun showSignIn() {
        iAuth.showSignIn()
    }

    override fun showRecoverPassword() {
        iAuth.showRecoverPassword()
    }
}