package com.example.gym_is_life_admin

//import android.R
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.gym_is_life_admin.R.layout.activity_admin_renovar_membresia
import com.example.gym_is_life_admin.R

class AdminRenovarMembresia : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_admin_renovar_membresia)

        val btnVolverAdmin: Button = findViewById(R.id.btnVolverAdmin)
        val btnActualizarM: Button = findViewById(R.id.btnActualizarM)

        btnVolverAdmin.setOnClickListener(){
            this.onBackPressed();
        }

        btnActualizarM.setOnClickListener(){
            this.ActualizarM();
        }
    }

    //Como volver de una actividad al fragment usuarios?

    private fun ActualizarM(){
        val builder = AlertDialog.Builder(this)
        val intent = Intent(this, MainActivity::class.java)
        builder.setTitle("Actualizar Membresía")
        builder.setMessage("¿Estás seguro que desea actualizar la membresía?")
//builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

        builder.setPositiveButton("Si") { dialog, which ->
            startActivity(intent)
        }

        builder.setNegativeButton("No") { dialog, which ->
        }

        builder.show()
    }

}