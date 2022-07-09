package com.example.gym_is_life_admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.google.firebase.firestore.FirebaseFirestore

class EditarClase : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_clase)
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