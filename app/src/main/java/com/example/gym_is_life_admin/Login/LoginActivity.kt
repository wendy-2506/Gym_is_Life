package com.example.gym_is_life_admin.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.widget.`AppCompatEditText$InspectionCompanion`
import com.example.gym_is_life_admin.Inicio.InicioActivity
import com.example.gym_is_life_admin.InicioAdmin.AdminActivity
import com.example.gym_is_life_admin.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val plUsuario: EditText = findViewById(R.id.plUsuario)
        val plContraseña: EditText = findViewById(R.id.plContraseña)
        val btnIniciar: Button = findViewById(R.id.btnIniciar)

        btnIniciar.setOnClickListener {
            val intent = Intent(this, AdminActivity::class.java)
            startActivity(intent)
        }

    }
}