package com.example.gym_is_life_admin.InicioAdmin.ui.fragments

import android.os.Bundle
import android.content.Intent
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SearchView
import android.widget.TextView
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gym_is_life_admin.AdminRenovarMembresia
import com.example.gym_is_life_admin.InicioAdmin.AdapterAdmin.ActividadesAdapter
import com.example.gym_is_life_admin.InicioAdmin.AdapterAdmin.UsuariosAdapter
import com.example.gym_is_life_admin.InicioAdmin.ModelAdmin.Actividades
import com.example.gym_is_life_admin.InicioAdmin.ModelAdmin.Usuarios
import com.example.gym_is_life_admin.R
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*

class UsuariosFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_usuarios, container, false)

        val tvSaludoInicioAdmin: TextView = view.findViewById(R.id.tvSaludoInicioAdmin)
        val btnBuscar1: Button = view.findViewById(R.id.btnBuscar1);
        val rvUsuarios: RecyclerView = view.findViewById(R.id.rvUsuarios)
        val svUsuarios: SearchView = view.findViewById(R.id.svUsuarios)
        rvUsuarios.layoutManager = LinearLayoutManager(requireContext())

        val db = FirebaseFirestore.getInstance()
        var lstUsuario: ArrayList<Usuarios> = ArrayList()

        db.collection("usuario")
            .addSnapshotListener{ snapshots,e->
                if(e!=null){
                    Log.w("Firebase Warning","Error",e)
                }

                for(dc in snapshots!!.documentChanges){
                    when(dc.type){
                        DocumentChange.Type.ADDED -> {
                            lstUsuario.add(Usuarios(dc.document.data["dni"].toString().toInt(), dc.document.data["nombre"].toString(), dc.document.data["estado"].toString()))
                            rvUsuarios.adapter = UsuariosAdapter(lstUsuario)
                        }
                        DocumentChange.Type.MODIFIED -> {
                            lstUsuario.add(Usuarios(dc.document.data["dni"].toString().toInt(), dc.document.data["nombre"].toString(), dc.document.data["estado"].toString()))
                            rvUsuarios.adapter = UsuariosAdapter(lstUsuario)

                        }
                        DocumentChange.Type.REMOVED -> {
                            lstUsuario.add(Usuarios(8011566, "eliminado", "eliminado"))
                            rvUsuarios.adapter = UsuariosAdapter(lstUsuario)

                        }
                    }
                }

            }

        val db1 = FirebaseFirestore.getInstance()

        val db2 = FirebaseFirestore.getInstance()
        db1.collection("usuario")
            .get()
            .addOnSuccessListener { result ->
                db2.collection("usuario_actual")
                    .get()
                    .addOnSuccessListener { result2 ->
                        for (document in result){
                            for (document2 in result2){
                                if (document2.data["dni"].toString() == document.data["dni"].toString()){
                                    tvSaludoInicioAdmin.text = "¡Hola, " + document.data["nombre"].toString() + "!"
                                }
                            }
                        }
                    }
            }

        //val apellido = svUsuarios.get().toString();
        btnBuscar1.setOnClickListener(){

        }
        return view
    }


    private fun listUsuarios(): List<Usuarios>{
        val db = FirebaseFirestore.getInstance()
        var lstUsuario: ArrayList<Usuarios> = ArrayList()
        //Borretodo lo que habia aqui - No nos sirve
        return lstUsuario

    }
}