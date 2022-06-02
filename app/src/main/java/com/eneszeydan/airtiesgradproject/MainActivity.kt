package com.eneszeydan.airtiesgradproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.eneszeydan.airtiesgradproject.databinding.ActivityMainBinding
import com.eneszeydan.airtiesgradproject.fragments.HomepageFragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)
        //This is to get rid of the shadow that was showing up in the bottom navigation view for some reason
        binding.bottomNavigationView.background = null
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navHostFragment.navController)



        binding.fab.setOnClickListener {
            try {
                Navigation.findNavController(this, R.id.fragmentContainerView).navigate(R.id.toAddNew)
            }catch (e: Exception){
                Log.e(e.cause.toString(), e.localizedMessage as String)
            }
        }

        navHostFragment.navController.addOnDestinationChangedListener{_, destination, _ ->
            if(destination.id == R.id.addNewFragment || destination.id == R.id.detailFragment || destination.id == R.id.confirmOrderFragment){
                binding.bottomNavigationView.visibility = View.GONE
                binding.fab.visibility = View.GONE
            }else{
                binding.bottomNavigationView.visibility = View.VISIBLE
                binding.fab.visibility = View.VISIBLE
            }

        }


    }


}