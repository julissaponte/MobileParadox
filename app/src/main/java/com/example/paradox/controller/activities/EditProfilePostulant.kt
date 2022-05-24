package com.example.paradox.controller.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.paradox.R
import com.example.paradox.models.Postulant
import com.example.paradox.network.PostulantService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EditProfilePostulant : AppCompatActivity() {

//    val etNameProfProfile = findViewById<EditText>(R.id.etNameProfProfile)
//    val etLastNameProfProfile = findViewById<EditText>(R.id.etLastNameProfProfile)
//    val etPhoneProfProfile = findViewById<EditText>(R.id.etPhoneProfProfile)
//    val etCivilStatusProfile = findViewById<EditText>(R.id.etCivilStatusProfile)
//    val etPhoneProfProfileEdit = findViewById<EditText>(R.id.etPhoneProfProfileEdit)
//    val etProfEmail = findViewById<EditText>(R.id.etProfEmail)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile_postulant)
        if (intent.extras != null) {
            loadData()
        }
        val buttonSaveEditProfile = findViewById<Button>(R.id.btSaveEdit)
        buttonSaveEditProfile.setOnClickListener {
            Log.d("bri", "button pressed");
            saveEditedPostulant()
        }
    }

    private fun saveEditedPostulant() {
        val postulant: Postulant = intent.getParcelableExtra("Postulant")!!
        val retrofit = Retrofit.Builder()
            .baseUrl("https://movilesback.herokuapp.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val etNameProfProfile = findViewById<EditText>(R.id.etNameProfProfile)
        val etLastNameProfProfile = findViewById<EditText>(R.id.etLastNameProfProfile)
        val etPhoneProfProfile = findViewById<EditText>(R.id.etPhoneProfProfile)
        val etCivilStatusProfile = findViewById<EditText>(R.id.etCivilStatusProfile)
        val etPhoneProfProfileEdit = findViewById<EditText>(R.id.etPhoneProfProfileEdit)
        val etProfEmail = findViewById<EditText>(R.id.etProfEmail)

        val postulantService: PostulantService
        postulantService = retrofit.create(PostulantService::class.java)

        val postulantEdited = Postulant(postulant.id, etNameProfProfile.text.toString(),
            etLastNameProfProfile.text.toString(), etProfEmail.text.toString(),
            etPhoneProfProfileEdit.text.toString().toInt(), postulant.password,etPhoneProfProfile.text.toString(),
            etCivilStatusProfile.text.toString())
        val request = postulantService.editPostulant(postulant.id, postulantEdited)
        request.enqueue(object : Callback<Postulant> {
            override fun onResponse(call: Call<Postulant>, response: Response<Postulant>) {
                if (response.isSuccessful){
                    val editedPostulant = response.body()
                    if (editedPostulant != null) {
                        Toast.makeText(this@EditProfilePostulant, "Successfully updated", Toast.LENGTH_LONG).show()
                    }
                }
            }

            override fun onFailure(call: Call<Postulant>, t: Throwable) {
                Toast.makeText(this@EditProfilePostulant, "There was a problem updating the info ", Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun loadData() {

        val postulant: Postulant = intent.getParcelableExtra("Postulant")!!


    val etNameProfProfile = findViewById<EditText>(R.id.etNameProfProfile)
    val etLastNameProfProfile = findViewById<EditText>(R.id.etLastNameProfProfile)
    val etPhoneProfProfile = findViewById<EditText>(R.id.etPhoneProfProfile)
    val etCivilStatusProfile = findViewById<EditText>(R.id.etCivilStatusProfile)
    val etPhoneProfProfileEdit = findViewById<EditText>(R.id.etPhoneProfProfileEdit)
    val etProfEmail = findViewById<EditText>(R.id.etProfEmail)
        etNameProfProfile.setText(postulant.firstName)
        etLastNameProfProfile.setText(postulant.lastName)
        etPhoneProfProfile.setText(postulant.document)
        etCivilStatusProfile.setText(postulant.civilStatus)
        etPhoneProfProfileEdit.setText(postulant.number.toString())
        etProfEmail.setText(postulant.email)
    }
}

