package com.example.gym_is_life_admin

import android.os.Bundle
import android.view.View
import android.content.Intent
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.gym_is_life_admin.InicioAdmin.ui.fragments.ActividadesFragment
import com.example.gym_is_life_admin.Login.LoginActivity
import com.example.gym_is_life_admin.ModelCreacion.NActividadModel
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class CrearClaseNueva : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_clase_nueva)
        val spnDisciplina: Spinner = findViewById(R.id.spinnerDisciplina2)
        val spnInstructor: Spinner = findViewById(R.id.spinnerInstructor)
        val txtDateClase: EditText = findViewById(R.id.TextDateClaseN)
        val spnSalon: Spinner = findViewById(R.id.spinnerSalon)
        val spnNivel: Spinner = findViewById(R.id.spinnerNivel)
        val txtCantidad: EditText = findViewById(R.id.TextCantidad)
        val txtReglas: EditText = findViewById(R.id.TextReglas)
        val btnAceptar: Button = findViewById(R.id.btnAceptar)
        var DisciplinaValue: String = ""
        var instructorValue: String = ""
        var nivelValue: String = ""
        var salonValue: String = ""

        ArrayAdapter.createFromResource(
            spnDisciplina.context,
            R.array.Disciplina,
            android.R.layout.simple_spinner_item
        ).also { adapter->
            adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
            spnDisciplina.adapter = adapter
        }
        spnDisciplina.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                DisciplinaValue = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }


        ArrayAdapter.createFromResource(
            spnInstructor.context,
            R.array.Instructor,
            android.R.layout.simple_spinner_item
        ).also { adapter->
            adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
            spnInstructor.adapter = adapter
        }
        spnInstructor.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                instructorValue = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }


        ArrayAdapter.createFromResource(
            spnNivel.context,
            R.array.Nivel,
            android.R.layout.simple_spinner_item
        ).also { adapter->
            adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
            spnNivel.adapter = adapter
        }
        spnNivel.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                nivelValue = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        ArrayAdapter.createFromResource(
            spnSalon.context,
            R.array.Salon,
            android.R.layout.simple_spinner_item
        ).also { adapter->
            adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
            spnSalon.adapter = adapter
        }
        spnSalon.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                salonValue = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        val db = FirebaseFirestore.getInstance()
        btnAceptar.setOnClickListener {
            val actividad = DisciplinaValue
            val cantidad = txtCantidad.text.toString().toInt()
            val fecha = txtDateClase.text.toString()
            val instructor = instructorValue
            val nivel = nivelValue
            val reglas = txtReglas.text.toString()
            val salon = salonValue
            if(actividad.length !=0 && cantidad > 0 && fecha.length != 0 && instructor.length != 0 &&
                nivel.length != 0 && reglas.length != 0 && salon.length != 0) {
                val nuevaClase =
                    NActividadModel(actividad, cantidad, fecha, instructor, nivel, reglas, salon)
                val id: UUID = UUID.randomUUID()

                db.collection("clase")
                    .document(id.toString())
                    .set(nuevaClase)
                this.AgregarClaseNueva();
            }
        }

    }

    private fun AgregarClaseNueva(){
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