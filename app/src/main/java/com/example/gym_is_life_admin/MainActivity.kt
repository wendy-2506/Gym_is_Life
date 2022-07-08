package com.example.gym_is_life_admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.gym_is_life_admin.Login.LoginActivity
import com.example.gym_is_life_admin.Registrarse.RegistroActivity


class MainActivity : AppCompatActivity() {
    //public val dniUsuario: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnIniciarSesión: Button = findViewById(R.id.btnIniciarSesion)
        btnIniciarSesión.setOnClickListener{
            this.irActividadLogin();
        }
        val btnRegistrarse: Button = findViewById(R.id.btnRegistrarse)
        btnRegistrarse.setOnClickListener(){
            this.irActividadRegistrarse();
        }
    }
    private fun irActividadLogin(){
        val intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)
    }
    private fun irActividadRegistrarse(){
        val intent = Intent(this, RegistroActivity::class.java)
        startActivity(intent)
    }
}