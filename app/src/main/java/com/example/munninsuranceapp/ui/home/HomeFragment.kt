package com.example.munninsuranceapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.munninsuranceapp.databinding.FragmentHomeBinding
import com.example.munninsuranceapp.ui.user_form.FormActivity

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        homeViewModel = HomeViewModel()

        initListeners()

        return binding.root

    }

    //initListeners() - method sets onClickListeners for buttons and text view Phone number

    private fun initListeners(){
        binding.btnHomeNL.setOnClickListener {
            requireContext().startActivity(
                Intent(requireActivity(),FormActivity::class.java)
            )
        }

        binding.btnHomeNS.setOnClickListener {  }   //empty onClickListeners on 3 buttons

        binding.btnAutoNL.setOnClickListener {  }

        binding.btnAutoNS.setOnClickListener {  }



        binding.txtPhoneNumber.setOnClickListener {
            textClick (it)
        }


    }


    //textClick - calls the openDialer() method in the Home View Model (openDialer method will open
    // the phone dialer with the dialer pre-filled with the number in the clicked text)
    private fun textClick(view: View){
        homeViewModel.openDialer(requireContext(), (view as TextView).text.toString())
    }



}