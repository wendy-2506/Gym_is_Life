package com.example.gym_is_life_admin.Inicio

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gym_is_life_admin.Inicio.ModelUsuario.Clases
import com.example.gym_is_life_admin.InicioAdmin.AdapterAdmin.ActividadesAdapter
import com.example.gym_is_life_admin.InicioAdmin.ModelAdmin.Actividades
import com.example.gym_is_life_admin.R
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore

class ClasesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clases)
        val rvClases: RecyclerView = findViewById(R.id.rvClases)
        val db = FirebaseFirestore.getInstance()
        rvClases.layoutManager = LinearLayoutManager(requireContext())
        db.collection("clase")
            .addSnapshotListener{ snapshot, e->
                if(e!=null){
                    Log.w("Firebase Warning","Error",e)
                }

                for(dc in snapshots!!.documentChanges){
                    when(dc.type){
                        DocumentChange.Type.ADDED -> {

                        }
                        DocumentChange.Type.MODIFIED -> {

                        }
                        DocumentChange.Type.REMOVED -> {

                        }
                    }
                }

            }

    }
    private fun listClases(): List<Clases>{
        val db = FirebaseFirestore.getInstance()
        var lstClases: ArrayList<Actividades> = ArrayList()
        return lstClases

    }
}