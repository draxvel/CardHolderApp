package com.tkachuk.cardholderapp.ui.auth.signIn

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.tkachuk.cardholderapp.R
import com.tkachuk.cardholderapp.ui.auth.AuthActivity
import kotlinx.android.synthetic.main.fragment_signin.*

class SignInFragment : Fragment() {

    private lateinit var root: View
    private lateinit var signInPresenter: SignInPresenter
    private lateinit var btnsignIn: Button
    private lateinit var tvGoToRecoverPassword: TextView
    private lateinit var tvGoToSignUp: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_signin, container, false)
        signInPresenter = SignInPresenter(activity as AuthActivity)
        initView()
        initListener()
        return root
    }

    private fun initView() {
        btnsignIn = root.findViewById(R.id.btn_singIn)
        tvGoToRecoverPassword = root.findViewById(R.id.tv_go_to_recover_password)
        tvGoToSignUp = root.findViewById(R.id.tv_go_to_signUp)
    }

    private fun initListener() {
        btnsignIn.setOnClickListener {
            tv_login.error = null
            tv_password.error = null
            when {
                tv_login.text.isEmpty() -> tv_login.error = getString(R.string.empty)
                tv_password.text.isEmpty() -> tv_password.error = getString(R.string.empty)
                else -> signInPresenter.signIn(tv_login.text.toString(), tv_password.text.toString())
            }
        }

        tvGoToRecoverPassword.setOnClickListener {
            signInPresenter.showRecoverPassword()
        }

        tvGoToSignUp.setOnClickListener {
            signInPresenter.showSignUp()
        }
    }
}