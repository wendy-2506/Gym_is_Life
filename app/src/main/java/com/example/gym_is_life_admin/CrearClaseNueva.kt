package com.example.gym_is_life_admin

import android.os.Bundle
import android.view.View
import android.content.Intent
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class CrearClaseNueva : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_clase_nueva)

        val btnAceptar = findViewById<View>(R.id.btnAceptar) as Button

        btnAceptar.setOnClickListener(){
            this.AgregarClaseNueva();
        }

    }

    private fun AgregarClaseNueva(){
        val builder = AlertDialog.Builder(this)
        val intent = Intent(this, MainActivity::class.java)
        builder.setTitle("Clase Nueva creada")
        builder.setMessage("La clase nueva ha sido creada correctamente.")
//builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))
        builder.setPositiveButton("Ok", null)
        builder.create()
        builder.show()

        //aqui deberia abrir de nuevo el fragment
    }

    private fun ErrorAgregarClaseNueva(){
        val builder = AlertDialog.Builder(this)
        val intent = Intent(this, MainActivity::class.java)
        builder.setTitle("Clase Nueva creada")
        builder.setMessage("Falta completar algún dato.")
//builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))
        builder.setPositiveButton("Volver", null)
        builder.create()
        builder.show()

        //aqui deberia abrir de nuevo el fragment
    }

}