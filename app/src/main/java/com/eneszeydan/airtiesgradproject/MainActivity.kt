package com.eneszeydan.airtiesgradproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.eneszeydan.airtiesgradproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)

        //This is to get rid of the shadow that was showing up in the bottom navigation view for some reason
        binding.bottomNavigationView.background = null
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        NavigationUI.setupWithNavController(
            binding.bottomNavigationView,
            navHostFragment.navController
        )

        supportActionBar?.title = "Food Delivery"

        binding.fab.setOnClickListener {
            try {
                Navigation.findNavController(this, R.id.fragmentContainerView)
                    .navigate(R.id.toAddNew)
            } catch (e: Exception) {
                Log.e(e.cause.toString(), e.localizedMessage as String)
            }
        }

        /* This piece of code is for controlling the visibility of the BottomNavigationView
        * depending on the fragment that's currently showing on the NavigationHost
         */
        navHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.addNewFragment || destination.id == R.id.detailFragment || destination.id == R.id.confirmOrderFragment) {
                binding.bottomNavigationView.visibility = View.GONE
                binding.fab.visibility = View.GONE
            } else {
                binding.bottomNavigationView.visibility = View.VISIBLE
                binding.fab.visibility = View.VISIBLE
            }
        }
    }

    fun signOutClicked() {
        val intent = Intent(this, LogInActivity::class.java)
        startActivity(intent)
    }
}