package com.example.gym_is_life_admin.Login

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.`AppCompatEditText$InspectionCompanion`
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.gym_is_life_admin.Inicio.InicioActivity
import com.example.gym_is_life_admin.InicioAdmin.AdminActivity
import com.example.gym_is_life_admin.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val plUsuario: EditText = findViewById(R.id.plUsuario)
        val plContraseña: EditText = findViewById(R.id.plContraseña)
        val btnIniciar: Button = findViewById(R.id.btnIniciar)

        val db = Firebase.firestore

        btnIniciar.setOnClickListener {
            var dni: Int = plUsuario.text.toString().toInt()
            var cont: String = plContraseña.text.toString()
            if (dni > 0 && cont.length != 0) {
                db.collection("usuario")
                    .get()
                    .addOnSuccessListener { result ->
                        for (document in result) {
                            //Prueba git
                            if (dni == document.data["dni"].toString()
                                    .toInt() && cont == document.data["contrase"].toString()
                            ) {
                                //Toast.makeText(plUsuario.context,"Incio de sesión exitoso", Toast.LENGTH_LONG).show()
                                if (document.data["tipo_user"].toString().toBoolean()) {
                                    val intent = Intent(this, AdminActivity::class.java)
                                    intent.putExtra("dni", document.data["dni"].toString().toInt())
                                    startActivity(intent)
                                    break
                                } else {
                                    val intent = Intent(this, InicioActivity::class.java)
                                    intent.putExtra("dni", document.data["dni"].toString().toInt())
                                    startActivity(intent)
                                }

                            }
                        }


                    }
                    .addOnFailureListener { exception ->
                        Log.w(TAG, "Error getting documents.", exception)
                    }
            }

        }
    }
}