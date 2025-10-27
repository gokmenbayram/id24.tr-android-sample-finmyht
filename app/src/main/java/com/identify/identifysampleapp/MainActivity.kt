package com.identify.identifysampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.identify.sdk.IdentifySdk
import com.identify.sdk.IdentityOptions
import com.identify.sdk.repository.model.enums.IdentifyModuleTypes
import com.identify.sdk.repository.model.mrz.DocType

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val identityOptions = IdentityOptions.Builder()
            .setIdentityType(
                listOf(
                    IdentifyModuleTypes.VALIDATE_ADDRESS,
                    IdentifyModuleTypes.PREPARE,
                    IdentifyModuleTypes.SPEECH_TEST,
                    IdentifyModuleTypes.IDENTIFICATION_INFORMATION_WITH_CARD_PHOTO,
                    IdentifyModuleTypes.IDENTIFICATION_INFORMATION_WITH_NFC,
                    IdentifyModuleTypes.TAKE_SELFIE,
                    IdentifyModuleTypes.LIVENESS_TEST,
                    IdentifyModuleTypes.VIDEO_RECORD,
                    IdentifyModuleTypes.SIGNATURE,
                    IdentifyModuleTypes.AGENT_CALL
                )
            )
            .setNfcExceptionCount(3)
            .setCallConnectionTimeOut(20000)
            .setOpenIntroPage(false)
            .setDocumentType(DocType.ID_CARD)
            .setOpenThankYouPage(false)
            .setVideoRecordTime(5000)
            .setCallConnectionTimeOut(5000)
            .setAutoSelfieWithLivenessDetection(true)
            .setEnablePDFInAddress(true)
            .setPDFMaxFileSizeInAddress(5120)   // max 5 MB
            .setEnableFaceControlInSelfie(true)
            .setEnableLightInformationInCall(true)
//            .setEnableLivenessWrongActionListener(true)
//            .setStatusOfAutoCrop(true)
            //.setSecretKeyBase64("SEATSJ8kk0v8+A1LeQsAMbOgL+fSj9pOaUKI5cDMITU=")
            .build()


        val identifyObject = IdentifySdk.Builder()
            .api("https://apiqa.identify.com.tr/")
            .lifeCycle(this.lifecycle)
            .options(identityOptions)
            .build()

        identifyObject.startIdentification(this,"60f8f48ea6a586e58664ad3e1f391b3d61a25cbb","tr")
    }
}