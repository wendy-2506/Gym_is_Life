package com.example.gym_is_life_admin.InicioAdmin.ui.fragments

import android.os.Bundle
import android.content.Intent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.gym_is_life_admin.AdminRenovarMembresia
import com.example.gym_is_life_admin.CrearClaseNueva
import com.example.gym_is_life_admin.R

class UsuariosFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_usuarios, container, false)

        val btnVer = view?.findViewById<View>(R.id.btnVer) as Button

        //Asigna listener para poder abrir Activity.
        btnVer.setOnClickListener{ view: View ->
        val intent = Intent (activity , AdminRenovarMembresia::class.java)
        activity?.startActivity(intent)
        }
}
}