package com.tkachuk.cardholderapp.ui.auth.recoverPassword

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.tkachuk.cardholderapp.R

class RecoverPasswordFragment : Fragment(), IRecoverPasswordContract.IRecoverPasswordView {

    private lateinit var root: View
    private lateinit var recoverPasswordPresenter: RecoverPasswordPresenter
    private lateinit var btnRecover: Button
    private lateinit var tvGoToSignInRecoverPassword: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragmen_recoverpassword, container, false)
        initView()
        initListener()
        return root
    }

    private fun initView() {
        btnRecover = root.findViewById(R.id.btn_recover)
        tvGoToSignInRecoverPassword = root.findViewById(R.id.tv_go_to_signIn_recover_password)
    }

    private fun initListener() {
        btnRecover.setOnClickListener{
            recoverPasswordPresenter.recover(tvGoToSignInRecoverPassword.text.toString())
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