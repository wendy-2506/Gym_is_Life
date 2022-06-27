package com.example.gym_is_life_admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog

class EditarClase : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_clase)

        val btnGuardar = findViewById<View>(R.id.btnGuardar) as Button

        btnGuardar.setOnClickListener(){
            this.Guardar();
        }
    }

    private fun Guardar(){
        val builder = AlertDialog.Builder(this)
        val intent = Intent(this, MainActivity::class.java)
        builder.setTitle("Guardar clase editada")
        builder.setMessage("La clase editada ha sido actualizada correctamente.")
//builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))
        builder.setPositiveButton("Ok", null)
        builder.create()
        builder.show()

        //aqui deberia abrir de nuevo el fragment
    }
}