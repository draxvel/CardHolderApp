package com.tkachuk.cardholderapp.data.auth

import com.google.firebase.auth.FirebaseAuth

object AuthFireBase : IAuthFireBase {

    private var auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun isSignIn(): Boolean {
        return auth.currentUser != null
    }

    override fun signIn(login: String, password: String, callback: IAuthFireBase.SignInCallback) {
        auth.signInWithEmailAndPassword(login, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                callback.onSignIn()
            } else {
                if (task.exception != null) {
                    callback.showMsg(task.exception!!.message.toString())
                }
            }
        }

    }

    override fun signUp(login: String, password: String, callback: IAuthFireBase.SignUpCallback) {
        auth.createUserWithEmailAndPassword(login, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                callback.onSignUp()
            } else {
                if (task.exception != null) {
                    callback.showMsg(task.exception!!.message.toString())
                }
            }
        }
    }

    override fun recoverPassword(login: String, callback: IAuthFireBase.RecoverPasswordCallback) {
        auth.sendPasswordResetEmail(login)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        callback.onRecover()
                    } else {
                        if (task.exception != null) {
                            callback.showMsg(task.exception!!.message.toString())
                        }
                    }
                }
    }

    override fun signOut() {
        auth.signOut()
    }
}
