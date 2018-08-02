package com.tkachuk.cardholderapp.ui.auth.recoverPassword

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tkachuk.cardholderapp.R
import kotlinx.android.synthetic.main.fragmen_recoverpassword.*

class RecoverPasswordFragment : Fragment(), IRecoverPasswordContract.IRecoverPasswordView {

    private lateinit var root: View
    private lateinit var recoverPasswordPresenter: RecoverPasswordPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragmen_recoverpassword, container, false)
        initListener()
        return root
    }

    private fun initListener() {
        btn_recover.setOnClickListener{
            recoverPasswordPresenter.recover(tv_login_recover_password.text as String)
        }

    }

    override fun showSignIn() {
        this.showSignIn()
    }

    override fun showSignUp() {
        this.showSignUp()
    }

    override fun showRecoverPassword() {
        //
    }

    override fun showMsg(msg: String) {
        this.showMsg(msg)
    }
}