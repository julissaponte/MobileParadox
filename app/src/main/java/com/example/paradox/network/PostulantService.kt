package com.example.paradox.network

import com.example.paradox.models.Postulant
import com.example.paradox.models.ProfProfile
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface PostulantService {
    @GET("postulants/{id}")
    fun getPostulantById(@Path("id") format: Int) : Call<Postulant>

    @PUT("postulants/{id}")
    fun editPostulant(@Path("id") postulantId: Int, @Body postulant: Postulant): Call<Path>

    @GET("postulants/{postulantId}/profiles/{profileId}")
    fun getProfileByIdAndPostulantId(@Path("postulantId") postulantId: Int, @Path("profileId") profileId: Int): Call<ProfProfile>

    @PUT("postulants/{postulantId}/profiles/{profileId}")
    fun editProfileOfSpecificPostulant(@Path("postulantId") postulantId: Int, @Path("profileId") profileId: Int, @Body profProfile: ProfProfile): Call<Path>
}