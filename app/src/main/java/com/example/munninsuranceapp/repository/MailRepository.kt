package com.example.munninsuranceapp.repository

import com.example.munninsuranceapp.Constants
import com.example.munninsuranceapp.model.InsuranceData
import com.example.munninsuranceapp.model.dto.MailResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject

class MailRepository {


    //sendEmail - this method calls the api method to send the email using MailJet api. The data is
    //converted into a JSON object that can be sent to the API.
    //The method receives three arguments: 1. mail; 2. name; 3. insurance data.
            //1. Mail - the email address to send the email to
            //2. Name - the name that should appear in the email
            //3. Insurance Data - the information retrieved from the form
    //This method converts all the information provided in the Insurance form into a JSON object,
    //since we can't send kotlin classes over the internet.
    //The method also returns a MailResponse object, which contains information about the sent email

    suspend fun sendEmail(mail: String, name:String, insuranceData: InsuranceData) : MailResponse {
        val recipientsArray = JSONArray()

        val recipients = JSONObject()
        recipients.put("Email", mail)
        recipients.put("Name", name)

        recipientsArray.put(recipients)

        val apiBody = JSONObject()
        apiBody.put("FromEmail", mail)
        apiBody.put("FromName", insuranceData.fname)
        apiBody.put("Recipients", recipientsArray)
        apiBody.put("Subject", "${insuranceData.fname}: Insurance Data")
        apiBody.put("Text-part", insuranceData.getContent())
        apiBody.put("Html-part", "")

        return Constants.getRetrofit().sendMail(
            apiBody.toString().toRequestBody("application/json".toMediaTypeOrNull())
        )

    }
}