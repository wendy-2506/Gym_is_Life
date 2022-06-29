package com.example.gym_is_life_admin.InicioAdmin.ui.fragments

import android.os.Bundle
import android.content.Intent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.gym_is_life_admin.AdminRenovarMembresia
import com.example.gym_is_life_admin.R

class UsuariosFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_usuarios, container, false)

        val btnVer: Button = view.findViewById(R.id.btnEditarClase)

        //Asigna listener para poder abrir Activity.
        btnVer.setOnClickListener{ view: View ->
        val intent = Intent (activity , AdminRenovarMembresia::class.java)
        activity?.startActivity(intent)
        }
        return view
    }
}