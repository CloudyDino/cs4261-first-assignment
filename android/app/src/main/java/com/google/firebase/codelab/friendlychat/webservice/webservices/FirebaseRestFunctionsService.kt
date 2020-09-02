package com.google.firebase.codelab.friendlychat.webservice.webservices

import com.google.firebase.codelab.friendlychat.models.DownloadMessageOptions
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface FirebaseRestFunctionsService {
    @GET("helloWorld")
    suspend fun helloWorld(): String

    @POST("getAllMessages")
    suspend fun getAllMessages(@Body downloadMessageOptions: DownloadMessageOptions): String
}
