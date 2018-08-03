package com.tkachuk.cardholderapp.ui.auth.recoverPassword

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.tkachuk.cardholderapp.R
import com.tkachuk.cardholderapp.ui.auth.AuthActivity

class RecoverPasswordFragment : Fragment() {

    private lateinit var root: View
    private lateinit var recoverPasswordPresenter: RecoverPasswordPresenter
    private lateinit var btnRecover: Button
    private lateinit var tvLogin:TextView
    private lateinit var tvGoToSignIn: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragmen_recoverpassword, container, false)
        recoverPasswordPresenter = RecoverPasswordPresenter(activity as AuthActivity)
        initView()
        initListener()
        return root
    }

    private fun initView() {
        btnRecover = root.findViewById(R.id.btn_recover)
        tvGoToSignIn = root.findViewById(R.id.tv_go_to_signIn_recover_password)
        tvLogin = root.findViewById(R.id.tv_login_recover_password)
    }

    private fun initListener() {
        btnRecover.setOnClickListener{
            tvLogin.error = null
            if(tvLogin.text.isEmpty()){
                tvLogin.error = getString(R.string.empty)
            }else recoverPasswordPresenter.recover(tvLogin.text.toString())
        }
        tvGoToSignIn.setOnClickListener {
            recoverPasswordPresenter.showSignIn()
        }
    }
}