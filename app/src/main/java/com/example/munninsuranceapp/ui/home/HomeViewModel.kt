package com.example.munninsuranceapp.ui.home

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {



    //openDialer - a method that opens the dialer with the number with the number pre-filled with
    //the number in the text

    fun openDialer(mCtx:Context, number:String){
        val uri = Uri.parse("tel:$number")
        val intent = Intent(Intent.ACTION_DIAL, uri)
        mCtx.startActivity(intent)

    }


}