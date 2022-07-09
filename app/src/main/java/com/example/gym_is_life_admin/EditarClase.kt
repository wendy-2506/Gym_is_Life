package com.example.gym_is_life_admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.example.gym_is_life_admin.InicioAdmin.ui.fragments.ActividadesFragment
import com.example.gym_is_life_admin.ModelCreacion.NActividadModel
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class EditarClase : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_clase)

        val btnVolverAdmin: Button = findViewById(R.id.btVolver)
        val spinnerDisciplina2: Spinner = findViewById(R.id.spinnerDisciplina2)
        val spinnerInstructor2: Spinner = findViewById(R.id.spinnerInstructor2)
        val textDateClaseN2: TextView = findViewById(R.id.textDateClaseN2)
        val spinnerSalon2: Spinner = findViewById(R.id.spinnerSalon2)
        val spinnerNivel2: Spinner = findViewById(R.id.spinnerNivel2)
        val textCantidad: TextView = findViewById(R.id.textCantidad)
        val textReglas: TextView = findViewById(R.id.textReglas)

        val btnGuardar = findViewById<View>(R.id.btnGuardar) as Button
        val db1 = FirebaseFirestore.getInstance()
        val db2 = FirebaseFirestore.getInstance()

        var DisciplinaValue: String = ""
        var instructorValue: String = ""
        var nivelValue: String = ""
        var salonValue: String = ""

        btnVolverAdmin.setOnClickListener{
            val intent = Intent(this, ActividadesFragment::class.java)
            startActivity(intent)
        }

        ArrayAdapter.createFromResource(
            spinnerDisciplina2.context,
            R.array.Disciplina,
            android.R.layout.simple_spinner_item
        ).also { adapter->
            adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
            spinnerDisciplina2.adapter = adapter
        }
        spinnerDisciplina2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                DisciplinaValue = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }


        ArrayAdapter.createFromResource(
            spinnerInstructor2.context,
            R.array.Instructor,
            android.R.layout.simple_spinner_item
        ).also { adapter->
            adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
            spinnerInstructor2.adapter = adapter
        }
        spinnerInstructor2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                instructorValue = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }


        ArrayAdapter.createFromResource(
            spinnerNivel2.context,
            R.array.Nivel,
            android.R.layout.simple_spinner_item
        ).also { adapter->
            adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
            spinnerNivel2.adapter = adapter
        }
        spinnerNivel2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                nivelValue = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        ArrayAdapter.createFromResource(
            spinnerSalon2.context,
            R.array.Salon,
            android.R.layout.simple_spinner_item
        ).also { adapter->
            adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
            spinnerSalon2.adapter = adapter
        }
        spinnerSalon2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                salonValue = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }







        db1.collection("clase")
            .get()
            .addOnSuccessListener { result ->
                db2.collection("clase_admin")
                    .get()
                    .addOnSuccessListener { result2 ->
                        for (document in result){
                            for (document2 in result2){
                                if (document2.data["nombre"].toString() == document.id.toString()){
                                    spinnerDisciplina2.getItemAtPosition(1)
                                    spinnerInstructor2.getItemAtPosition(1)
                                    textDateClaseN2.text = document.data["fecha"].toString()
                                    spinnerSalon2.getItemAtPosition(1)
                                    spinnerNivel2.getItemAtPosition(1)
                                    textCantidad.text = document.data["cantidad"].toString()
                                    textReglas.text = document.data["reglas"].toString()

                                    //tvSaludoInicioAdmin2.text = "¡Hola, " + document.data["nombre"].toString() + "!"
                                }
                            }
                        }
                    }
            }

        val db = FirebaseFirestore.getInstance()
        btnGuardar.setOnClickListener(){
            val actividad = DisciplinaValue
            val cantidad = textCantidad.text.toString().toInt()
            val fecha = textDateClaseN2.text.toString()
            val instructor = instructorValue
            val nivel = nivelValue
            val reglas = textReglas.text.toString()
            val salon = salonValue
            if(actividad.length !=0 && cantidad > 0 && fecha.length != 0 && instructor.length != 0 &&
                nivel.length != 0 && reglas.length != 0 && salon.length != 0) {
                val nuevaClase =
                    NActividadModel(actividad, cantidad, fecha, instructor, nivel, reglas, salon)
                val id: UUID = UUID.randomUUID()

                db.collection("clase")
                    .document(id.toString())
                    .set(nuevaClase)
                this.ActualizarClaseNueva();
            }
        }
    }
    private fun ActualizarClaseNueva(){
        val builder = AlertDialog.Builder(this)
        val intent = Intent(this, ActividadesFragment::class.java)
        builder.setTitle("Androidly Alert")
        builder.setMessage("Se creo satisfactoriamente la clase")
//builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

        builder.setPositiveButton("OK") { dialog, which ->
            startActivity(intent)
        }
        builder.show()
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

