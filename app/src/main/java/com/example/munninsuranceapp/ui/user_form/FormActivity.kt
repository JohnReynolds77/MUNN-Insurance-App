package com.example.munninsuranceapp.ui.user_form

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.example.munninsuranceapp.R
import com.example.munninsuranceapp.databinding.ActivityFormBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.progressindicator.LinearProgressIndicator

class FormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFormBinding
    private lateinit var dialog: AlertDialog
    private lateinit var completeDialog: AlertDialog
    private lateinit var formViewModel: FormViewModel
    private lateinit var mCtx: Context

    private val mailTo:String by lazy {
        "john.reynolds77@ed.cna.nl.ca"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.formToolbar)

        mCtx = this
        formViewModel = FormViewModel(this.application)
        initClicks()

    }

    //validateFields() - The actual validation methods are in the Form ViewModel,
    //whereas here it is saying if a given input isn't valid (based on the view model validation),
    //then return an error message (given as a string).

    //This method calls on the validation methods from the view model, and
    //returns a Boolean value - (either true or false)
    //The method returns true if all fields are valid, and false if any fields are not valid.

    private fun validateFields() : Boolean{

        with(binding){

            if(!formViewModel.validatePostalCode(edtPostalAddress.text.toString())) {
                tilPostalCode.error = mCtx.getString(R.string.invalid_postal_address)
                return false
            }
            if (!formViewModel.validateEmail(edtEmail.text.toString())) {
                tilemail.error = mCtx.getString(R.string.invalid_email)
                return false
            }

            if(!formViewModel.validateName(edtFirstName.text.toString())){
                tilfname.error = mCtx.getString(R.string.invalid_name)
                return false
            }

            if(!formViewModel.validateName(edtLastName.text.toString())){
                tillname.error = mCtx.getString(R.string.invalid_name)
                return false
            }


            if(!formViewModel.validatePhone(edtPhone.text.toString())){
                tilphone.error = mCtx.getString(R.string.invalid_phone)
                return false
            }

            if(!formViewModel.validatePolicyYear(edtPolicyYear.text.toString())){
                edtPolicyYear.error = mCtx.getString(R.string.invalid_year)
                return false
            }

            if(!formViewModel.validateStreetAddress(edtStreet.text.toString())){
                tilStreet.error = mCtx.getString(R.string.invalid_postal_address)
                return false
            }

            if(!formViewModel.validateCurrency(edtItemsWorth.text.toString())){
                tilItemsWorth.error = mCtx.getString(R.string.invalid_currency)
                return false
            }

            if(!formViewModel.validateYearsWithInsurance(edtYearBuilt.text.toString())){
                tilYearBuilt.error = mCtx.getString(R.string.invalid_year)
                return false
            }

            if(!formViewModel.validatePolicyYear(edtBirthYear.text.toString())){
                edtBirthYear.error = mCtx.getString(R.string.invalid_year)
                return false
            }

            if(!formViewModel.validateYearsWithInsurance(edtYearsInsurance.text.toString())){
                tilYearsOfInsurance.error = mCtx.getString(R.string.invalid_years)
                return false
            }

            if(!formViewModel.validateYearsWithInsurance(edtYearsDwelling.text.toString())){
                tilyearsAtDwelling.error = mCtx.getString(R.string.invalid_year)
                return false
            }

        }

        return true

    }


    //initClicks is a method that initializes all OnClickListeners
    private fun initClicks (){

        dialog = createProgressDialog()

        completeDialog = createDialog("sample@mail.com")

        binding.btnGetQuote.setOnClickListener {

            if (validateFields()){
                formViewModel.insuranceData = formViewModel.createInsuranceData(binding)
                formViewModel.sendEmail(mailTo, "Sender name here")

            }
        }

        formViewModel.insurance.observe(this){
            completeDialog = createDialog(it.email)
        }

        formViewModel.isLoading.observe(this) {
            if (it) dialog.show() else dialog.dismiss()

        }

        formViewModel.isSent.observe(this){
            if(it) completeDialog.show() else completeDialog.dismiss()
        }


    }


    //createDialog sets up the dialog that shows after the form has been filled out and sent
    private fun createDialog(message:String) : AlertDialog {
        val builder = MaterialAlertDialogBuilder(mCtx)
            .setTitle(mCtx.getString(R.string.dialog_title))
            .setMessage(mCtx.getString(R.string.dialog_message)+message)
            .setCancelable(false)
            .setPositiveButton(mCtx.getString(R.string.dialog_btn)) { _, _ -> }
        return builder.create()
    }


    //createProgressDialog is the loading dialog when the email is being processed
    private fun createProgressDialog() : AlertDialog {
        val progressBar = LinearProgressIndicator(mCtx)
        progressBar.minimumWidth = ViewGroup.LayoutParams.MATCH_PARENT
        progressBar.isIndeterminate = true


        val builder = MaterialAlertDialogBuilder(mCtx)
            .setView(progressBar)
            .setCancelable(false)

        return builder.create()
    }

}
