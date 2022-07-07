package com.example.gym_is_life_admin.InicioAdmin.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gym_is_life_admin.InicioAdmin.AdminActivity
import com.example.gym_is_life_admin.R
import com.example.gym_is_life_admin.CrearClaseNueva
import com.example.gym_is_life_admin.EditarClase
import com.example.gym_is_life_admin.InicioAdmin.AdapterAdmin.ActividadesAdapter
import com.example.gym_is_life_admin.InicioAdmin.AdapterAdmin.UsuariosAdapter
import com.example.gym_is_life_admin.InicioAdmin.ModelAdmin.Actividades
import com.example.gym_is_life_admin.InicioAdmin.ModelAdmin.Usuarios
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class ActividadesFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_actividades, container, false)
        val rvActividades: RecyclerView = view.findViewById(R.id.rvActividades)
        val btnCrearClase: Button = view.findViewById(R.id.btnCrearClase)

        rvActividades.layoutManager = LinearLayoutManager(requireContext())


        val db = FirebaseFirestore.getInstance()
        var lstActividades: ArrayList<Actividades> = ArrayList()

        db.collection("clase")
            .addSnapshotListener{ snapshots,e->
                if(e!=null){
                    Log.w("Firebase Warning","Error",e)
                }

                for(dc in snapshots!!.documentChanges){
                    when(dc.type){
                        DocumentChange.Type.ADDED -> {
                            lstActividades.add(Actividades(dc.document.data["salon"].toString(), dc.document.data["actividad"].toString(), dc.document.data["fecha"].toString()))
                            rvActividades.adapter = ActividadesAdapter(lstActividades)
                        }
                        DocumentChange.Type.MODIFIED -> {
                            lstActividades.add(Actividades(dc.document.data["salon"].toString(), dc.document.data["actividad"].toString(), dc.document.data["fecha"].toString()))
                            rvActividades.adapter = ActividadesAdapter(lstActividades)

                        }
                        DocumentChange.Type.REMOVED -> {
                            lstActividades.add(Actividades("SalonB", "eliminado", "eliminado"))
                            rvActividades.adapter = ActividadesAdapter(lstActividades)

                        }
                    }
                }

            }

        //Asigna listener para poder abrir Activity.
        btnCrearClase.setOnClickListener{ view: View ->
            val intent = Intent (activity , CrearClaseNueva::class.java)
            activity?.startActivity(intent)
        }


        return view
    }
    private fun listActividades(): List<Actividades>{
        val db = FirebaseFirestore.getInstance()
        var lstActividad: ArrayList<Actividades> = ArrayList()
        return lstActividad

    }

}