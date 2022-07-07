package com.example.gym_is_life_admin

import android.os.Bundle
import android.view.View
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.gym_is_life_admin.ModelCreacion.NActividadModel
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class CrearClaseNueva : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_clase_nueva)
        val spnDisciplina: Spinner = findViewById(R.id.spinnerDisciplina2)
        val spnInstructo: Spinner = findViewById(R.id.spinnerInstructor)
        val txtDateClase: EditText = findViewById(R.id.TextDateClaseN)
        val spnSalon: Spinner = findViewById(R.id.spinnerSalon)
        val spnNivel: Spinner = findViewById(R.id.spinnerNivel)
        val txtCantidad: EditText = findViewById(R.id.TextCantidad)
        val txtReglas: EditText = findViewById(R.id.TextReglas)
        val btnAceptar: Button = findViewById(R.id.btnAceptar)
        val db = FirebaseFirestore.getInstance()
        btnAceptar.setOnClickListener {
            val actividad = "Judo"
            val cantidad = txtCantidad.text.toString().toInt()
            val fecha = txtDateClase.text.toString()
            val instructor = "Fiorella Gomez"
            val nivel = "Intermedio"
            val reglas = txtReglas.text.toString()
            val salon = "SalonA"
            if(actividad.length !=0 && cantidad > 0 && fecha.length != 0 && instructor.length != 0 &&
                nivel.length != 0 && reglas.length != 0 && salon.length != 0) {
                val nuevaClase =
                    NActividadModel(actividad, cantidad, fecha, instructor, nivel, reglas, salon)
                val id: UUID = UUID.randomUUID()

                db.collection("clase")
                    .document(id.toString())
                    .set(nuevaClase)
                AgregarClaseNueva();
            }
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