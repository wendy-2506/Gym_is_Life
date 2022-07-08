package com.example.gym_is_life_admin.InicioAdmin.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.gym_is_life_admin.InicioAdmin.AdapterAdmin.ActividadesAdapter
import com.example.gym_is_life_admin.InicioAdmin.ModelAdmin.Actividades
import com.example.gym_is_life_admin.Login.LoginActivity
import com.example.gym_is_life_admin.Login.RecuperarActivity
import com.example.gym_is_life_admin.R
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class PerfilFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_perfil, container, false)
        val tvNombreAdmin: TextView = view.findViewById(R.id.tvNombreAdmin)
        val tvApellidoAdmin: TextView = view.findViewById(R.id.tvApellidoAdmin)
        val tvDni: TextView = view.findViewById(R.id.tvDniAdmin)
        val tvCuenta:TextView = view.findViewById(R.id.tvCuenta)
        val btnActualizar: Button = view.findViewById(R.id.btnActualizarContra)
        val db = Firebase.firestore


        db.collection("usuario")
            .addSnapshotListener { snapshots, e ->
                if (e != null) {
                    Log.w("Firebase Warning", "Error", e)
                }

            }
        btnActualizar.setOnClickListener { view: View->
            val intent = Intent (activity, RecuperarActivity::class.java)
            activity?.startActivity(intent)
        }
        return view
    }
}