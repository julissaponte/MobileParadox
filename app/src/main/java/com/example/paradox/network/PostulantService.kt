package com.example.paradox.network

import com.example.paradox.models.PostulantBri
import com.example.paradox.models.ProfProfile
import retrofit2.Call
import retrofit2.http.*

interface PostulantService {
    @GET("postulants/{id}")
    fun getPostulantById(@Path("id") format: Int) : Call<PostulantBri>

    @PUT("postulants/{id}")
    fun editPostulant(@Path("id") postulantId: Int, @Body postulantBri: PostulantBri): Call<PostulantBri>

    @GET("postulants/{postulantId}/profiles/{profileId}")
    fun getProfileByIdAndPostulantId(@Path("postulantId") postulantId: Int, @Path("profileId") profileId: Int): Call<ProfProfile>

    @GET("profiles/{profileId}/studies")
    fun getStudiesByProfileId(@Path("profileId") postulantId: Int): Call<ProfProfile>

    @GET("profiles/{profileId}/skills")
    fun getSkillsByProfileId(@Path("profileId") postulantId: Int): Call<ProfProfile>

    @GET("profiles/{profileId}/languages")
    fun getLanguagesByProfileId(@Path("profileId") postulantId: Int): Call<ProfProfile>

    @PUT("postulants/{postulantId}/profiles/{profileId}")
    fun editProfileOfSpecificPostulant(@Path("postulantId") postulantId: Int, @Path("profileId") profileId: Int, @Body profProfile: ProfProfile): Call<Path>

    @GET("studies/{studyId}/profiles")
    fun getStudiesByProfile(@Path("studyId") studyId: Int): Call<ProfProfile>

    @GET("studies/{skillId}/profiles")
    fun getSkillsByProfile(@Path("skillId") skillId: Int): Call<ProfProfile>

    @GET("languages/{languageId}/profiles")
    fun getLanguagesByProfile(@Path("languageId") languageId: Int): Call<ProfProfile>
}