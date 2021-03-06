package com.tkachuk.cardholderapp.ui.auth

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.tkachuk.cardholderapp.ui.main.MainActivity
import com.tkachuk.cardholderapp.R
import com.tkachuk.cardholderapp.data.auth.AuthFireBase
import com.tkachuk.cardholderapp.ui.auth.recoverPassword.RecoverPasswordFragment
import com.tkachuk.cardholderapp.ui.auth.signIn.SignInFragment
import com.tkachuk.cardholderapp.ui.auth.singUp.SignUpFragment

class AuthActivity : AppCompatActivity(), IAuth {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
    }

    override fun onStart() {
        super.onStart()

        if (AuthFireBase.isSignIn()) {
            showMainActivity()
            finish()
        } else {
            replaceFragment(SignInFragment())
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fl_container, fragment)
                .commit()
    }

    override fun showSignIn() {
        replaceFragment(SignInFragment())
    }

    override fun showSignUp() {
        replaceFragment(SignUpFragment())
    }

    override fun showRecoverPassword() {
        replaceFragment(RecoverPasswordFragment())
    }

    override fun showMsg(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun showMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
    }
}
