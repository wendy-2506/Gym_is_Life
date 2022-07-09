package com.example.gym_is_life_admin.Inicio.ui.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gym_is_life_admin.Inicio.AdapterUsuario.ClasesAdapter
import com.example.gym_is_life_admin.Inicio.AdapterUsuario.claseUserAdapter
import com.example.gym_is_life_admin.Inicio.ModelUsuario.Clases
import com.example.gym_is_life_admin.Inicio.ModelUsuario.clase_User
import com.example.gym_is_life_admin.R
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class HorarioFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_horario, container, false)
        val rvHorario: RecyclerView = view.findViewById(R.id.rvHorario)
        rvHorario.layoutManager = LinearLayoutManager(rvHorario.context)

        val db = FirebaseFirestore.getInstance()
        val db2 = FirebaseFirestore.getInstance()
        val db3 = FirebaseFirestore.getInstance()
        var lstClaseUsuarios: ArrayList<clase_User> = ArrayList()
        db.collection("clase")
            .addSnapshotListener{snapshots, e->
                if(e!=null){
                    Log.w("Firebase Warning", "Error", e)
                }
                db2.collection("clase_usuario")
                    .get()
                    .addOnSuccessListener { result ->
                        db3.collection("usuario_actual")
                            .get()
                            .addOnSuccessListener { result2 ->
                                for (dc in snapshots!!.documentChanges) {
                                    for (document in result) {
                                        when (dc.type) {
                                            DocumentChange.Type.ADDED -> {
                                                lstClaseUsuarios.add(
                                                    clase_User(
                                                        dc.document.data["actividad"].toString(),
                                                        dc.document.data["instructor"].toString(),
                                                        dc.document.data["fecha"].toString(),
                                                        dc.document.data["salon"].toString(),
                                                        dc.document.data["nivel"].toString()
                                                    )
                                                )
                                                rvHorario.adapter =
                                                    claseUserAdapter(lstClaseUsuarios)
                                            }
                                            DocumentChange.Type.MODIFIED -> {
                                                lstClaseUsuarios.add(
                                                    clase_User(
                                                        dc.document.data["actividad"].toString(),
                                                        dc.document.data["instructor"].toString(),
                                                        dc.document.data["fecha"].toString(),
                                                        dc.document.data["salon"].toString(),
                                                        dc.document.data["nivel"].toString()
                                                    )
                                                )
                                                rvHorario.adapter =
                                                    claseUserAdapter(lstClaseUsuarios)

                                            }
                                            DocumentChange.Type.REMOVED -> {
                                            }
                                        }
                                    }
                                }
                            }
                    }
            }



        return view
    }

}