package com.example.munninsuranceapp


import android.net.Credentials
import com.example.munninsuranceapp.repository.MailJetApi
import com.google.gson.GsonBuilder
//import okhttp3.Credentials
//import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Constants {


    //these constants are used for the api setup:

    const val HYPHEN = '-'

    private const val BASE_URL = "https://api.mailjet.com/v3/"

    private const val API_KEY = "TYPE API KEY HERE"
    private const val SECRET_KEY = "TYPE SECRET API KEY HERE"

    private val credential:String = okhttp3.Credentials.basic(API_KEY, SECRET_KEY)



    //getRetrofit method initializes the API using retrofit library:

    fun getRetrofit() : MailJetApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(apiClient())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
            .create(MailJetApi::class.java)
    }


    //apiClient() method sets up the network connection properties for use in the api
    //(for example the api key and secret key above):

    private fun apiClient() : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor {
                var request = it.request()
                request = request.newBuilder().header("Authorization", credential).build()
                it.proceed(request)
            }.build()
    }



    }