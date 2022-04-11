package com.example.munninsuranceapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


//The kotlin-parcelize plugin (added in the build.gradle app file) provides a
//parcelable implementation generator

@Parcelize
data class InsuranceData(

    var postalCode:String? = null,
    var insurancePropertyType:String? = null,
    var insuranceYear:String? = null,
    var fname:String? = null,
    var lname:String? = null,
    var phone:String? = null,
    var email:String = "sample@mail.com",
    var streetAddress:String? = null,
    var currentInsurance:String? = null,
    var houseEstimate:Double? = 0.0,
    var distFromFireHydrant:String? = null,
    var distFromFireHall:String? = null,
    var buildingType:String? = null,
    val constType:String? = null,
    var yearBuilt:Int? = 0,
    var primaryHeating:String? = null,
    var auxHeating:String? = "None",
    var burglarAlarm:String? = "None",
    var fireAlarm:String? = "Num",
    var DOB:String? = null,
    var coApplicant:Boolean? = false,
    var yearsWithResidential:String? = null,
    var numberOfClaims:String? = null,
    var nonSmokers:Boolean = false,
    var yearsAtDwelling:String = "0",
    var numberOfFamiliesAtDwelling:String = "1",

):Parcelable{

    fun getContent(): String {
        return "" +
                "Postal code: $postalCode \n" +
                "Property Insurance Type: $insurancePropertyType \n" +
                "Policy begin date: $insuranceYear \n" +
                "Applicant: $fname $lname \n" +
                "Phone: $phone \n" +
                "Email: $email \n" +
                "Street Address: $streetAddress \n" +
                "Current Insurance Company: $currentInsurance \n" +
                "House items estimate: $$houseEstimate \n" +
                "Distance from fire hydrant: $distFromFireHydrant\n" +
                "Distance from fire hall: $distFromFireHall\n" +
                "Years at dwelling: $yearsAtDwelling\n" +
                "Number of families at dwelling: $numberOfFamiliesAtDwelling\n" +
                "Building Type: $buildingType \n" +
                "Construction Type: $constType\n" +
                "Year Built: $yearBuilt\n" +
                "Primary Heating Source: $primaryHeating\n" +
                "Auxiliary Heating Source: $auxHeating\n" +
                "Burglar Alarm: $burglarAlarm\n" +
                "Fire Alarm: $fireAlarm\n" +
                "Date of Birth: $DOB\n" +
                "Are you a co-applicant: $coApplicant\n" +
                "Years with residential insurance: $yearsWithResidential\n" +
                "Number of claims in past: $numberOfClaims\n" +
                "Are members in house-hold nonsmokers: $nonSmokers"
    }

}
