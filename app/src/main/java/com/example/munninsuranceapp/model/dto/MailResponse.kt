package com.example.munninsuranceapp.model.dto

data class MailResponse(
    val Sent: List<Sent>
)


//the files in the dto folder map the API response directly
//they can be considered as direct maps of the api response to Kotlin classes