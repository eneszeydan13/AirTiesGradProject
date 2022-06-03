package com.eneszeydan.airtiesgradproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.databinding.DataBindingUtil
import com.eneszeydan.airtiesgradproject.databinding.ActivitySignUpBinding
import com.eneszeydan.airtiesgradproject.entity.User
import com.eneszeydan.airtiesgradproject.viewmodels.SignUpViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var viewModel: SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        setContentView(binding.root)
        auth = Firebase.auth

        binding.apply {
            signUpButton.setOnClickListener {
                val email = emailEditText2.text.toString()
                val password = passwordEditText2.text.toString()

                signUpClicked(email, password)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

    }

    fun signUpClicked(email:String, password:String){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    val intent = Intent(this@SignUpActivity, MainActivity::class.java)
                    startActivity(intent)
                }
            }
            .addOnFailureListener {
                Snackbar.make(binding.signUpButton, it.localizedMessage as String, Snackbar.LENGTH_SHORT).show()
            }
    }
}