package com.example.paradox

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btVer=findViewById<Button>(R.id.btVer)
        val btAgregar=findViewById<Button>(R.id.btAgregar)


        btVer.setOnClickListener {
            val intent = Intent(this, ViewCompaniesEmployerActivity::class.java)
            startActivity(intent)
        }
        btAgregar.setOnClickListener {
            val intent = Intent(this, AddCompanyActivity::class.java)
            startActivity(intent)
        }
    }
}