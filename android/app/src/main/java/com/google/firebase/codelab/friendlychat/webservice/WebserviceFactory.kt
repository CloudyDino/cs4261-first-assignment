package com.google.firebase.codelab.friendlychat.webservice

import com.google.firebase.codelab.friendlychat.webservice.webservices.FirebaseRestFunctionsService
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class WebserviceFactory {
    companion object {
        fun getFirebaseRestFunctionsService(): FirebaseRestFunctionsService {
            val retrofit = Retrofit.Builder()
                    .baseUrl("https://us-central1-cs4261-first-assignment-c900e.cloudfunctions.net/")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(GsonBuilder()
                            .setLenient()
                            .create()))
                    .build()
            return retrofit.create(FirebaseRestFunctionsService::class.java)
        }
    }
}
