package com.example.gym_is_life_admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.gym_is_life_admin.Login.LoginActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnIniciarSesión: Button = findViewById(R.id.btnIniciarSesion)
        btnIniciarSesión.setOnClickListener{
            this.irActividad();
        }
    }
    private fun irActividad(){
        val intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)
    }
}