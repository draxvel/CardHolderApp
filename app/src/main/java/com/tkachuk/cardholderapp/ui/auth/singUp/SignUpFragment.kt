package com.tkachuk.cardholderapp.ui.auth.singUp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tkachuk.cardholderapp.R
import kotlinx.android.synthetic.main.fragment_signin.*

class SignUpFragment : Fragment(), ISignUpContract.ISingUpView{

    private lateinit var root: View
    private lateinit var singUpPresenter: SignUpPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_signup, container, false)
        initListener()
        return root
    }

    private fun initListener() {
//        btn_singIn.setOnClickListener {
//            singUpPresenter.signUp(tv_login.text as String, tv_password.text as String)
//        }
    }

    override fun showSignIn() {
        this.showSignIn()
    }

    override fun showSignUp() {
        //
    }

    override fun showRecoverPassword() {
        this.showRecoverPassword()
    }

    override fun showMsg(msg: String) {
        this.showMsg(msg)
    }
}