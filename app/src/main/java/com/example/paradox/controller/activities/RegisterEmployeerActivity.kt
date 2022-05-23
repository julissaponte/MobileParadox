package com.example.paradox.controller.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.example.paradox.R
import com.example.paradox.models.RequestEmployeer
import com.example.paradox.models.ResponseEmployeer
import com.example.paradox.network.RegisterService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterEmployeerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_employeer)

        val btRegister = findViewById<Button>(R.id.btRegistrarseEmp)

        btRegister.setOnClickListener {



            addCompany()



        }

    }

    private fun addCompany() {

        val txCreateNameEmployeer = findViewById<EditText>(R.id.txCreateNameEmp)
        val txCreateApellidoEmp = findViewById<EditText>(R.id.txCreateApellidoEmp)
        val txDocumentoIdentidadEmp = findViewById<EditText>(R.id.txDocumentoIdentidadEmp)
        val txtEstadoCivilEmpl = findViewById<EditText>(R.id.txtEstadoCivilEmpl)
        val txtPhoneEmp = findViewById<EditText>(R.id.txtPhoneEmp)
        val txtPosicionEmp = findViewById<EditText>(R.id.txtPosicionEmp)
        val txtEmailEmp = findViewById<EditText>(R.id.txtEmailEmp)
        val txtContraEmp = findViewById<EditText>(R.id.txtContraEmp)


        val nameEmp = txCreateNameEmployeer.text.toString()
        val apellidoEmp = txCreateApellidoEmp.text.toString()
        val identidadEmp = txDocumentoIdentidadEmp.text.toString()
        val phoneEmp = txtPhoneEmp.text.toString().toLong()
        val posicionEmp = txtPosicionEmp.text.toString()
        val emailEmp = txtEmailEmp.text.toString()
        val contraEmp = txtContraEmp.text.toString()
        val requestEmployeer = RequestEmployeer(nameEmp,apellidoEmp,emailEmp, phoneEmp,contraEmp,identidadEmp,
                posicionEmp
     )

        val request = RegisterService.registerInstance.createEmployeer(requestEmployeer)
        request.enqueue(object: Callback<ResponseEmployeer> {
            override fun onFailure(call: Call<ResponseEmployeer>, t: Throwable) {
                Log.d("AddEmployeerAddedActivity","Error in Adding Employeer")
            }

            override fun onResponse(call: Call<ResponseEmployeer>, response: Response<ResponseEmployeer>) {
                val employeerAdded = response.body()
                if (employeerAdded != null) {
                    Log.d("AddEmployeerActivity", employeerAdded.toString())
                }
            }
        })
    }
}