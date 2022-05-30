package com.eneszeydan.airtiesgradproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.eneszeydan.airtiesgradproject.databinding.ActivitySignUpBinding
import com.eneszeydan.airtiesgradproject.entity.User
import com.eneszeydan.airtiesgradproject.preferences.AppPreferences
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var ref : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        setContentView(binding.root)
        auth = Firebase.auth

        binding.apply {
            signUpButton.setOnClickListener {
                val name = usernameEditText.text.toString()
                val email = emailEditText2.text.toString()
                val password = passwordEditText2.text.toString()

                signUpClicked(name, email, password)
            }
        }
    }

    fun signUpClicked(name:String, email:String, password:String){
        if (name.isNotEmpty()){
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        val user = auth.currentUser
                        val db = FirebaseDatabase.getInstance()

                        user?.let {
                            ref = db.getReference("users/${user.uid}")
                            val newUser = User(name, it.email, it.uid)
                            ref.push().setValue(newUser)
                        }
                        val ap = AppPreferences(this)
                        CoroutineScope(Dispatchers.Main).launch {
                            ap.saveName(name)
                        }
                        val intent = Intent(this@SignUpActivity, MainActivity::class.java)
                        startActivity(intent)
                    }
                }
                .addOnFailureListener {
                    Snackbar.make(binding.signUpButton, it.localizedMessage, Snackbar.LENGTH_SHORT).show()
                }
        }

    }
}