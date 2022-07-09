package com.example.gym_is_life_admin

//import android.R
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.gym_is_life_admin.InicioAdmin.AdminActivity
import com.example.gym_is_life_admin.R.layout.activity_admin_renovar_membresia
import com.example.gym_is_life_admin.R
import com.example.gym_is_life_admin.Registrarse.models.UsuarioModel
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

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
        val tvSaludoInicioAdmin2:TextView = findViewById(R.id.tvSaludoInicioAdmin2)

        val db = FirebaseFirestore.getInstance()
        val db1 = FirebaseFirestore.getInstance()
        val db2 = FirebaseFirestore.getInstance()
        db1.collection("usuario")
            .get()
            .addOnSuccessListener { result ->
                db2.collection("usuario_membresia")
                    .get()
                    .addOnSuccessListener { result2 ->
                        for (document in result){
                            for (document2 in result2){
                                if (document2.data["dni"].toString() == document.data["dni"].toString()){
                                    NombreU.text = document.data["nombre"].toString()
                                    ApellidosU.text = document.data["apellido"].toString()
                                    DniU.text = document.data["dni"].toString()
                                    correoU.text = document.data["correo"].toString()
                                    fechaFinMem.text = document.data["fechaFinMem"].toString()
                                    //tvSaludoInicioAdmin2.text = "¡Hola, " + document.data["nombre"].toString() + "!"
                                }
                            }
                        }
                    }
            }

            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents.", exception)
            }


        btnVolverAdmin.setOnClickListener(){
            this.onBackPressed();
        }

        btnActualizarM.setOnClickListener { view: View->
            db.collection("usuario")
                .get()
                .addOnSuccessListener { result ->
                    db2.collection("usuario_membresia")
                        .get()
                        .addOnSuccessListener { result2 ->
                            for (document in result){
                                for(document2 in result2){
                                    if (document.data["dni"].toString() == document2.data["dni"].toString() ){
                                        val id: String = document.id

                                        db.collection("usuario")
                                            .document(id)
                                            .update("nombre", NombreU.text.toString())
                                        db.collection("usuario")
                                            .document(id)
                                            .update("apellido", ApellidosU.text.toString())
                                        db.collection("usuario")
                                            .document(id)
                                            .update("dni", DniU.text.toString())
                                        db.collection("usuario")
                                            .document(id)
                                            .update("correo", correoU.text.toString())
                                        db.collection("usuario")
                                            .document(id)
                                            .update("fechaFinMem", fechaFinMem.text.toString())
                                    }
                                }
                            }
                        }
                }
            val builder = android.app.AlertDialog.Builder(DniU.context)
            val intent = Intent(DniU.context, AdminActivity::class.java)
            builder.setTitle("Androidly Alert")
            builder.setMessage("Se actualizaron los datos")
//builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

            builder.setPositiveButton("OK") { dialog, which ->
                startActivity(intent)
            }
            builder.show()
        }

        /*btnActualizarM.setOnClickListener(){view: View ->
            db.collection("usuario")
                .get()
                .addOnSuccessListener { result ->
                            for (document in result) {
                                if (document.data["dni"].toString() ==DniU.toString()) {
                                    val id: String = document.id
                                    db.collection("usuario")
                                        .document(id)
                                        .update("nombre", NombreU.text.toString())
                                }
                            }
                }
            val builder = android.app.AlertDialog.Builder(DniU.context)
            val intent = Intent(DniU.context, AdminActivity::class.java)
            builder.setTitle("Androidly Alert")
            builder.setMessage("Se actualizó su contraseña")
//builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))
            builder.setPositiveButton("OK") { dialog, which ->
                startActivity(intent)
            }
            builder.show()
        }*/
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