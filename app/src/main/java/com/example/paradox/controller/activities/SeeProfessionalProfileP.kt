package com.example.paradox.controller.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.paradox.R
import com.example.paradox.adapter.CompanyAdapter
import com.example.paradox.adapter.LanguageAdapter
import com.example.paradox.adapter.SkillAdapter
import com.example.paradox.adapter.StudyAdapter
import com.example.paradox.models.*
import com.example.paradox.network.PostulantService
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SeeProfessionalProfileP : AppCompatActivity() {

    lateinit var skillAdapter : SkillAdapter
    lateinit var languageAdapter: LanguageAdapter
    lateinit var studiesAdapter: StudyAdapter
    var professionalProfilePostulant = ProfProfile()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_see_professional_profile_p)
        loadProfessionalProfile()
        loadSkills()

        val buttonEditProfessional = findViewById<FloatingActionButton>(R.id.btGoToEditProfessional)
        buttonEditProfessional.setOnClickListener {
            val intent = Intent(this, EditProfessionalProfileP::class.java)
            intent.putExtra("ProfProfile", this.professionalProfilePostulant)
            startActivity(intent)
        }

    }
    fun loadSkills() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://movilesback.herokuapp.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val postulantService: PostulantService = retrofit.create(PostulantService::class.java)
        val request2 = postulantService.getSkillsByProfileId(1)
        val request3 = postulantService.getStudiesByProfileId(1)
        val request4 = postulantService.getLanguagesByProfileId(1)


        request2.enqueue(object : Callback<Skills> {
            override fun onResponse(call: Call<Skills>, response: Response<Skills>) {
                if (response.isSuccessful){
                    val rvSkills = findViewById<RecyclerView>(R.id.rcSkills)
                    val content = response.body()
                    if (content != null) {
                        skillAdapter = SkillAdapter(content.skills, this@SeeProfessionalProfileP)
                        rvSkills.adapter = skillAdapter
                        rvSkills.layoutManager = LinearLayoutManager(this@SeeProfessionalProfileP)
                    }
                }
            }

            override fun onFailure(call: Call<Skills>, t: Throwable) {
                Toast.makeText(this@SeeProfessionalProfileP, "Data could not be retrieved", Toast.LENGTH_LONG).show()
            }
        })

        request3.enqueue(object : Callback<Studies> {
            override fun onResponse(call: Call<Studies>, response: Response<Studies>) {
                if (response.isSuccessful){
                    val rvStudies = findViewById<RecyclerView>(R.id.rcStudies)
                    val content = response.body()
                    if (content != null) {
                        studiesAdapter = StudyAdapter(content.studies, this@SeeProfessionalProfileP)
                        rvStudies.adapter = studiesAdapter
                        rvStudies.layoutManager = LinearLayoutManager(this@SeeProfessionalProfileP)
                    }
                }
            }

            override fun onFailure(call: Call<Studies>, t: Throwable) {
                Toast.makeText(this@SeeProfessionalProfileP, "Data could not be retrieved", Toast.LENGTH_LONG).show()
            }
        })

        request4.enqueue(object : Callback<Languages> {
            override fun onResponse(call: Call<Languages>, response: Response<Languages>) {
                if (response.isSuccessful){
                    val rvLanguages = findViewById<RecyclerView>(R.id.rcLanguages)
                    val content = response.body()
                    if (content != null) {
                        languageAdapter = LanguageAdapter(content.languages, this@SeeProfessionalProfileP)
                        rvLanguages.adapter = languageAdapter
                        rvLanguages.layoutManager = LinearLayoutManager(this@SeeProfessionalProfileP)
                    }
                }
            }

            override fun onFailure(call: Call<Languages>, t: Throwable) {
                Toast.makeText(this@SeeProfessionalProfileP, "Data could not be retrieved", Toast.LENGTH_LONG).show()
            }
        })
    }
    fun loadProfessionalProfile() {
        val tvOcupacionTextProfProfile = findViewById<TextView>(R.id.tvOcupacionTextProfProfile)
        val tvDescriptionTextProfProfile = findViewById<TextView>(R.id.tvDescriptionTextProfProfile)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://movilesback.herokuapp.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val postulantService: PostulantService = retrofit.create(PostulantService::class.java)

        val request = postulantService.getProfileByIdAndPostulantId(4, 1)

        request.enqueue(object : Callback<ProfProfile> {
            override fun onResponse(call: Call<ProfProfile>, response: Response<ProfProfile>) {
                if (response.isSuccessful){
                    val profProfileRetrieved = response.body()
                    if (profProfileRetrieved != null){
                        tvOcupacionTextProfProfile.text = profProfileRetrieved.ocupation
                        tvDescriptionTextProfProfile.text = profProfileRetrieved.description
                        professionalProfilePostulant = ProfProfile(profProfileRetrieved.id, profProfileRetrieved.ocupation, profProfileRetrieved.video, profProfileRetrieved.description)
                    }
                }
            }
            override fun onFailure(call: Call<ProfProfile>, t: Throwable) {
                 Toast.makeText(this@SeeProfessionalProfileP, "Data could not be retrieved", Toast.LENGTH_LONG).show()
            }
        })
    }
}