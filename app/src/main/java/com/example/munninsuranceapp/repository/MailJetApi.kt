package com.example.munninsuranceapp.repository

import com.example.munninsuranceapp.model.dto.MailResponse
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

interface MailJetApi {


    //sendMail() - Retrofit method to make a call from the API

    @POST("send")
    suspend fun sendMail (@Body requestBody: RequestBody) : MailResponse

}