package com.tkachuk.cardholderapp.ui.auth.singUp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.tkachuk.cardholderapp.R
import com.tkachuk.cardholderapp.ui.auth.AuthActivity

class SignUpFragment : Fragment() {

    private lateinit var root: View
    private lateinit var signUpPresenter: SignUpPresenter
    private lateinit var btnSignUp: Button
    private lateinit var tvLogin: TextView
    private lateinit var tvPassword: TextView
    private lateinit var tvRepeatPassword: TextView
    private lateinit var tvGoToSignIn: TextView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_signup, container, false)
        signUpPresenter = SignUpPresenter(activity as AuthActivity)
        initView()
        initListener()
        return root
    }

    private fun initView() {
        btnSignUp = root.findViewById(R.id.btn_signUp)
        tvLogin = root.findViewById(R.id.tv_login_signUp)
        tvPassword = root.findViewById(R.id.tv_password_signUp)
        tvRepeatPassword = root.findViewById(R.id.tv_repeat_password)
        tvGoToSignIn = root.findViewById(R.id.tv_go_to_signIn)
    }

    private fun initListener() {
        btnSignUp.setOnClickListener {
            tvLogin.error = null
            tvPassword.error = null
            tvRepeatPassword.error = null
            when {
                tvLogin.text.isEmpty() -> tvLogin.error = getString(R.string.empty)
                tvPassword.text.isEmpty() -> tvPassword.error = getString(R.string.empty)
                tvRepeatPassword.text.isEmpty() -> tvRepeatPassword.error = getString(R.string.empty)
                tvPassword.text.toString() != tvRepeatPassword.text.toString() -> tvRepeatPassword.error = getString(R.string.not_mismatch)
                else -> signUpPresenter.signUp(tvLogin.text.toString(), tvRepeatPassword.text.toString())
            }
        }

        tvGoToSignIn.setOnClickListener {
            signUpPresenter.showSignIn()
        }
    }

}