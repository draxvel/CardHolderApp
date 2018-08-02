package com.tkachuk.cardholderapp.ui.auth.recoverPassword

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tkachuk.cardholderapp.R

class RecoverPasswordFragment : Fragment() {

    private lateinit var root: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragmen_recoverpassword, container, false)
        return root
    }
}