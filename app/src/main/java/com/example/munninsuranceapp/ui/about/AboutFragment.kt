package com.example.munninsuranceapp.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.munninsuranceapp.R
import com.example.munninsuranceapp.databinding.FragmentAboutBinding
import com.example.munninsuranceapp.ui.home.HomeViewModel


class AboutFragment : Fragment() {

    private lateinit var binding: FragmentAboutBinding
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAboutBinding.inflate(inflater, container, false)
        initListeners()
        return binding.root
    }



    //initListeners() - method sets onClickListeners the text view phone number
    private fun initListeners(){


        with(binding){

            txtPhoneNumber2.setOnClickListener {
                textClick(it)
            }

        }


    }

    //textClick - calls the openDialer() method in the Home View Model (openDialer method will open
    // the phone dialer with the dialer pre-filled with the number in the clicked text)

    private fun textClick(view: View){
        homeViewModel.openDialer(requireContext(), (view as TextView).text.toString())
    }


}