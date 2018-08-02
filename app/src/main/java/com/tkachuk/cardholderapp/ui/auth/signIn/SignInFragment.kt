package com.tkachuk.cardholderapp.ui.auth.signIn

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tkachuk.cardholderapp.R
import kotlinx.android.synthetic.main.fragment_singin.*

class SignInFragment : Fragment(), ISignInContract.ISingInView{

    private lateinit var root: View
    private lateinit var singInPresenter: SignInPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_singin, container, false)
        singInPresenter = SignInPresenter()
        initListener()
        return root
    }

    private fun initListener() {
        btn_singIn.setOnClickListener {
            singInPresenter.signIn(tv_login.text as String, tv_password.text as String)
        }
    }

    override fun showSignUp() {
        this.showSignUp()
    }

    override fun showRecoverPassword() {
        this.showRecoverPassword()
    }

    override fun showMsg(msg: String) {
        this.showMsg(msg)
    }

    override fun showSignIn() {
        //
    }

}