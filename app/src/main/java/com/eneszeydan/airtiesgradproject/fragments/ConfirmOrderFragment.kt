package com.eneszeydan.airtiesgradproject.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.eneszeydan.airtiesgradproject.R
import com.eneszeydan.airtiesgradproject.databinding.FragmentConfirmOrderBinding
import com.eneszeydan.airtiesgradproject.entity.Order
import com.eneszeydan.airtiesgradproject.viewmodels.ConfirmOrderViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*

class ConfirmOrderFragment : Fragment() {
    private lateinit var binding : FragmentConfirmOrderBinding
    private lateinit var viewModel: ConfirmOrderViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_confirm_order, container, false)
        binding.confirmOrderFragment = this

        val bundle: ConfirmOrderFragmentArgs by navArgs()
        binding.amount = bundle.orderAmount

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : ConfirmOrderViewModel by viewModels()
        viewModel = tempViewModel
    }

    fun saveOrder(){
        val address = binding.addressEditText.text.toString()
        val telNo = binding.telEditText.text.toString()
        val date = Calendar.getInstance().time
        val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm")
        val dateTime = simpleDateFormat.format(date).toString()
        val uid:String = Firebase.auth.uid.toString()


        binding.apply {
            if (address.isEmpty() || telNo.isEmpty()) {
                Toast.makeText(
                    requireContext(),
                    "Adres veya telefon boş bırakılamaz!",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val order = Order(uid, binding.amount, address, telNo, dateTime)
                viewModel.saveOrderToFirebase(order)
                viewModel.getCart(uid)
                viewModel.orderList.observe(viewLifecycleOwner){
                    it.forEach { item ->
                        viewModel.deleteFromCart(item.cartId, item.userName)
                    }
                }
                Handler(Looper.getMainLooper()).postDelayed({
                    Navigation.findNavController(binding.button).navigateUp()
                }, 2500)
            }
        }
    }

}