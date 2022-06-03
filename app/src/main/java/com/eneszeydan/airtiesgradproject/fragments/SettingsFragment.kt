package com.eneszeydan.airtiesgradproject.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.eneszeydan.airtiesgradproject.MainActivity
import com.eneszeydan.airtiesgradproject.R
import com.eneszeydan.airtiesgradproject.databinding.FragmentSettingsBinding
import com.eneszeydan.airtiesgradproject.viewmodels.SettingsViewModel
import com.google.android.material.snackbar.Snackbar

class SettingsFragment : Fragment() {
    private lateinit var binding : FragmentSettingsBinding
    private lateinit var viewModel : SettingsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false)

        binding.settingsFragment = this

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : SettingsViewModel by viewModels()
        viewModel = tempViewModel
    }

    fun signOut() {
        Snackbar.make(binding.buttonSignOut, getString(R.string.sign_out_clicked), Snackbar.LENGTH_LONG)
            .setAction(getString(R.string.positive)){
                viewModel.signOut()
                (activity as MainActivity).signOutClicked()
            }.show()
    }

}