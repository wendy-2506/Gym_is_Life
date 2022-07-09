package com.example.gym_is_life_admin.Inicio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gym_is_life_admin.Inicio.AdapterUsuario.ClasesAdapter
import com.example.gym_is_life_admin.Inicio.ModelUsuario.Clases
import com.example.gym_is_life_admin.Login.LoginActivity
import com.example.gym_is_life_admin.R
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore

class ClasesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clases)
        val rvClases: RecyclerView = findViewById(R.id.rvClases)
        rvClases.layoutManager = LinearLayoutManager(rvClases.context)


        val db = FirebaseFirestore.getInstance()
        var lstClases: ArrayList<Clases> = ArrayList()
        db.collection("clase")
            .addSnapshotListener{ snapshots, e->
                if(e!=null){
                    Log.w("Firebase Warning", "Error", e)
                }
                for(dc in snapshots!!.documentChanges){
                    when(dc.type){
                        DocumentChange.Type.ADDED -> {
                            lstClases.add(Clases(dc.document.data["actividad"].toString(), dc.document.data["instructor"].toString(), dc.document.data["fecha"].toString(),
                                dc.document.data["salon"].toString(),dc.document.data["nivel"].toString(),dc.document.data["cantidad"].toString().toInt(),
                            dc.document.data["reglas"].toString()))
                            rvClases.adapter = ClasesAdapter(lstClases)
                        }
                        DocumentChange.Type.MODIFIED -> {
                            lstClases.add(Clases(dc.document.data["actividad"].toString(), dc.document.data["instructor"].toString(), dc.document.data["fecha"].toString(),
                                dc.document.data["salon"].toString(),dc.document.data["nivel"].toString(),dc.document.data["cantidad"].toString().toInt(),
                                dc.document.data["reglas"].toString()))
                            rvClases.adapter = ClasesAdapter(lstClases)

                        }
                        DocumentChange.Type.REMOVED -> {
                        }
                    }
                }
            }


    }
    private fun listClases(): List<Clases>{
        val db = FirebaseFirestore.getInstance()
        var lstClases: ArrayList<Clases> = ArrayList()
        return lstClases

    }

}