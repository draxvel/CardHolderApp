package com.tkachuk.cardholderapp.data

import com.google.firebase.auth.FirebaseAuth
import com.google.android.gms.tasks.OnCompleteListener

object Auth: IAuth {

    private lateinit var instance: Auth

    private var auth: FirebaseAuth = FirebaseAuth.getInstance()

//        fun getInstance(): Auth {
//            if (instance == null) {
//                instance = Auth()
//                auth = FirebaseAuth.getInstance()
//            }
//            return instance
//        }


    override fun isSignIn(): Boolean {
        return auth.currentUser!=null
    }

    override fun signIn(login: String, password: String, callback: IAuth.SignInCallback) {
        auth.signInWithEmailAndPassword(login, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                callback.onSignIn()
            } else {
                if(task.exception!=null) {
                    callback.showMsg(task.exception!!.message.toString())
                }
            }
        }

    }

    override fun signUp(login: String, password: String, callback: IAuth.SignUpCallback) {
        auth.createUserWithEmailAndPassword(login, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                callback.onSignUp()
            } else {
                if(task.exception!=null) {
                    callback.showMsg(task.exception!!.message.toString())
                }
            }
        }

    }

    override fun recoverPassword(login: String, callback: IAuth.RecoverPasswordCallback) {
        auth.sendPasswordResetEmail(login)
                .addOnCompleteListener(OnCompleteListener<Void> {task ->
                    if (task.isSuccessful) {
                        callback.onRecover()
                    }
                })
    }

    override fun signOut() {
        auth.signOut()
    }
}
