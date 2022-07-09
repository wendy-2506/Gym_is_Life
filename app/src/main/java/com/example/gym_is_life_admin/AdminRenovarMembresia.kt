package com.example.gym_is_life_admin

//import android.R
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.gym_is_life_admin.InicioAdmin.AdminActivity
import com.example.gym_is_life_admin.R.layout.activity_admin_renovar_membresia
import com.example.gym_is_life_admin.R
import com.google.firebase.firestore.FirebaseFirestore

class AdminRenovarMembresia : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_admin_renovar_membresia)

        val btnVolverAdmin: Button = findViewById(R.id.btnVolverAdmin)
        val btnActualizarM: Button = findViewById(R.id.btnActualizarM)
        val dni = intent.getStringExtra("dni")
        val NombreU: TextView = findViewById(R.id.textNameUser)
        val ApellidosU: TextView = findViewById(R.id.textApellidosUser)
        val DniU: TextView = findViewById(R.id.textDniUser)
        val correoU: TextView = findViewById(R.id.textCorreoUser)
        val fechaFinMem: TextView = findViewById(R.id.textMembresíaUser)
        val contraseña: TextView = findViewById(R.id.textInicioMembresíaUser)
        val db = FirebaseFirestore.getInstance()

        db.collection("usuario")
            .get()
            .addOnSuccessListener { result ->
                for(document in result){
                    println(document.data["dni"].toString())
                    if(dni == document.data["dni"]){
                        NombreU.text = document.data["nombre"].toString()
                        ApellidosU.text = document.data["apellido"].toString()
                        DniU.text = document.data["dni"].toString()
                        correoU.text = document.data["correo"].toString()
                        fechaFinMem.text = document.data["fechaFinMem"].toString()
                        contraseña.text = document.data["contrase"].toString()
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents.", exception)
            }


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
        val intent = Intent(this, AdminActivity::class.java)
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