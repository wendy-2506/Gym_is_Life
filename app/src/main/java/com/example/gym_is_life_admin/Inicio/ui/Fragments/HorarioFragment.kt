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
        rvHorario.layoutManager = LinearLayoutManager(requireContext())
        val db = FirebaseFirestore.getInstance()
        val db2 = FirebaseFirestore.getInstance()
        val db3 = FirebaseFirestore.getInstance()
        var lstClaseUser: ArrayList<clase_User> = ArrayList()
        db.collection("usuario_actual")
            .get()
            .addOnSuccessListener { result ->
                db2.collection("clase_usuario")
                    .get()
                    .addOnSuccessListener { result2 ->
                        for (document in result) {
                            for (document2 in result2) {
                                if (document.data["dni"].toString() == document2.data["idUsuario"].toString()){

                                    val idClase: String = document2.data["idClase"].toString()

                                    db3.collection("clase")
                                        .get()
                                        .addOnSuccessListener { result3 ->
                                            for (document3 in result3){
                                                if (idClase == document3.id){
                                                    lstClaseUser.add(
                                                        clase_User(
                                                            document3.data["actividad"].toString(),
                                                            document3.data["instructor"].toString(),
                                                            document3.data["fecha"].toString(),
                                                            document3.data["salon"].toString(),
                                                            document3.data["nivel"].toString()
                                                        )
                                                    )
                                                    rvHorario.adapter = claseUserAdapter(lstClaseUser)

                                                }
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