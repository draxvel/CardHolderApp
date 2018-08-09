package com.tkachuk.cardholderapp.ui.auth.recoverPassword

import com.tkachuk.cardholderapp.data.auth.AuthFireBase
import com.tkachuk.cardholderapp.data.auth.IAuthFireBase
import com.tkachuk.cardholderapp.ui.auth.AuthActivity
import com.tkachuk.cardholderapp.ui.auth.IAuth

class RecoverPasswordPresenter(activity: AuthActivity) : IRecoverPasswordContract.IRecoverPasswordPresenter {

    private var iAuth: IAuth = activity

    override fun recover(login: String) {

        AuthFireBase.recoverPassword(login, callback = object : IAuthFireBase.RecoverPasswordCallback {
            override fun showMsg(msg: String) {
                iAuth.showMsg(msg)
            }

            override fun onRecover() {
                iAuth.showMsg("New password sent to your email.")
                iAuth.showMainActivity()
            }
        })
    }

    override fun showSignIn() {
        iAuth.showSignIn()
    }
}
