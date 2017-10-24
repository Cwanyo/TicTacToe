package com.wvn.cwan.tictactoe

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import android.util.Log
import android.widget.EditText
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    var TAG: String = "wvn:Loin"
    var mAuth: FirebaseAuth? = null
    private var mAuthListener: FirebaseAuth.AuthStateListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mAuth = FirebaseAuth.getInstance()

        mAuthListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
            if (user != null) {
                // User is signed in
                Toast.makeText(this, "User is signed in", Toast.LENGTH_LONG).show()
            } else {
                // User is signed out
                Toast.makeText(this, "User is signed out", Toast.LENGTH_LONG).show()
            }
            // ...
        }

        //SwithToGamePage()
    }

    override fun onStart() {
        super.onStart()
        mAuth!!.addAuthStateListener(mAuthListener!!)
    }

    override fun onStop() {
        super.onStop()
        if (mAuthListener != null) {
            mAuth!!.removeAuthStateListener(mAuthListener!!)
        }
    }

    fun OnButtonClickLogin(view: View) {
        Toast.makeText(this, "OnButtonClickLogin", Toast.LENGTH_LONG).show()
        Log.d(TAG, et_email!!.text.toString())
        Log.d(TAG, et_password!!.text.toString())
        mAuth!!.signInWithEmailAndPassword(et_email!!.text.toString(), et_password!!.text.toString())
                .addOnCompleteListener(this) { task ->
                    Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful)
                    Toast.makeText(this, "signInWithEmail:onComplete", Toast.LENGTH_LONG).show()
                    // If sign in fails, display a message to the user. If sign in succeeds
                    // the auth state listener will be notified and logic to handle the
                    // signed in user can be handled in the listener.
                    if (!task.isSuccessful) {
                        Log.w(TAG, "signInWithEmail:failed", task.exception)
                        Toast.makeText(this, "signInWithEmail:failed", Toast.LENGTH_LONG).show()
                        //Toast.makeText(this@EmailPasswordActivity, R.string.auth_failed,Toast.LENGTH_SHORT).show()
                    }

                    // ...
                }

        SwithToGamePage()
    }

    fun SwithToGamePage(){
        var user: FirebaseUser = FirebaseAuth.getInstance().currentUser!!
        if (user!=null){
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            this.finish()
        }else{
            Toast.makeText(this, "Please signIn", Toast.LENGTH_LONG).show()
        }
    }
}
