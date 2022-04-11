package com.example.munninsuranceapp.ui.user_form

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.munninsuranceapp.Constants
import com.example.munninsuranceapp.R
import com.example.munninsuranceapp.databinding.ActivityFormBinding
import com.example.munninsuranceapp.model.InsuranceData
import com.example.munninsuranceapp.repository.MailRepository
import kotlinx.coroutines.launch

class FormViewModel
    constructor(private val mCtx: Application) : AndroidViewModel(mCtx) {

    //store insurance data
    var insuranceData:InsuranceData = InsuranceData()

    //repository object
    private val mailRepository = MailRepository()

    //mutable liva data
    private val _isloading:MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isloading

    private val _isSent: MutableLiveData<Boolean> = MutableLiveData(false)
    val isSent: LiveData <Boolean>
        get() = _isSent

    private val _insurance:MutableLiveData<InsuranceData> = MutableLiveData(insuranceData)
    val insurance:LiveData<InsuranceData>
        get() = _insurance




    //These functions validate the mandatory fields in the form, ensuring the information that is
    // filled out in the form conforms to the rules. regex is used to validate the email field:

    fun validateEmail(email:String) = email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex())

    fun validatePostalCode(postal:String) = postal.length > 5

    fun validatePolicyYear(year:String) = year.length > 3

    fun validateName(name:String) = name.isNotEmpty()

    fun validatePhone(phone:String) = phone.length > 9

    fun validateStreetAddress(street:String) = street.length > 4

    fun validateCurrency(amount:String) = amount.isNotEmpty()

    fun validateYearsWithInsurance(year: String) = year.isNotEmpty()





    //createInsuranceData - This function compiles the data that is filled out in the form.
    //It binds the user inputs that a user fills out in the edit text boxes and converts
    //the user inputs from the edit text boxes, spinners, radio buttons, and checkboxes to
    //strings, which are then passed to the 'Insurance Data' dataclass in the model

    fun createInsuranceData (binding: ActivityFormBinding) : InsuranceData {
        val context = mCtx.applicationContext
        with(binding){
            return InsuranceData(
                postalCode = edtPostalAddress.text.toString(),
                insurancePropertyType = context.resources.getStringArray(R.array.property_policy)[spinnerInsurancePolicy.selectedItemPosition],
                insuranceYear =
                with(context.resources){
                    getStringArray(R.array.policy_month)[spinnerInsuranceMonth.selectedItemPosition] +
                            Constants.HYPHEN +
                            getStringArray(R.array.policy_day)[spinnerInsuranceDay.selectedItemPosition] +
                            Constants.HYPHEN +
                            edtPolicyYear.text.toString()
                },
                fname = binding.edtFirstName.text.toString(),
                lname = binding.edtLastName.text.toString(),
                phone = binding.edtPhone.text.toString(),
                email = binding.edtEmail.text.toString(),
                streetAddress = binding.edtStreet.text.toString(),
                currentInsurance = binding.edtCompany.text.toString(),
                houseEstimate = binding.edtItemsWorth.text.toString().toDoubleOrNull(),
                distFromFireHydrant = context.resources.getStringArray(R.array.fire_hydrant)[spinnerFireHydrant.selectedItemPosition],
                distFromFireHall = context.resources.getStringArray(R.array.fire_hall)[spinnerFireHall.selectedItemPosition],
                buildingType = context.resources.getStringArray(R.array.building_type)[spinnerBuildingType.selectedItemPosition],
                constType = context.resources.getStringArray(R.array.construction_type)[spinnerConstructionType.selectedItemPosition],
                yearBuilt = binding.edtYearBuilt.text.toString().toInt(),
                primaryHeating = context.resources.getStringArray(R.array.primary_heating)[spinnerPrimaryHeater.selectedItemPosition],
                auxHeating = context.resources.getStringArray(R.array.auxiliary_heating)[spinnerAuxHeater.selectedItemPosition],
                burglarAlarm = if(rbBurglarNone.isChecked) context.getString(R.string.none) else if(rbBurglarLocal.isChecked) context.getString(R.string.local) else context.getString(R.string.monitored),
                fireAlarm = if(rbFireNone.isChecked) context.getString(R.string.none) else if(rbFireLocal.isChecked) context.getString(R.string.local) else context.getString(R.string.monitored),
                DOB =
                with(context.resources){
                    getStringArray(R.array.policy_month)[spinnerBirthYear.selectedItemPosition] +
                            Constants.HYPHEN +
                            getStringArray(R.array.policy_day)[spinnerBirthDay.selectedItemPosition] +
                            Constants.HYPHEN +
                            edtBirthYear.text.toString()
                },
                coApplicant = rbCoYes.isChecked,
                yearsWithResidential = edtYearsInsurance.text.toString(),
                numberOfClaims = if(rbZero.isChecked) "0" else if(rbOne.isChecked) "1" else if(rbTwo.isChecked) "2" else "Three or more",
                numberOfFamiliesAtDwelling = when {
                    rbZeroF.isChecked -> "0"
                    rbOneF.isChecked -> "1"
                    rbTwoF.isChecked -> "2"
                    rbThreeF.isChecked -> "3"
                    rbFourF.isChecked -> "4"
                    rbFiveF.isChecked -> "5"
                    else -> "6 or more"
                },
                nonSmokers = rbNonYes.isChecked,
                yearsAtDwelling = edtYearsDwelling.text.toString()


            )

        }
    }


    //this function calls the send email method in the Repository Class
    fun sendEmail(mail: String, name:String){

        _insurance.value = insuranceData

        viewModelScope.launch {

            _isloading.value = true

            val data =  mailRepository.sendEmail(mail, name, insuranceData)
            Log.d("TAG", "sendEmail: $data")

            _isloading.value = false

            _isSent.value = true

        }

    }



}