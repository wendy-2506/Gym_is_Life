package com.example.gym_is_life_admin.InicioAdmin.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.gym_is_life_admin.InicioAdmin.AdminActivity
import com.example.gym_is_life_admin.R
import com.example.gym_is_life_admin.CrearClaseNueva
import com.example.gym_is_life_admin.EditarClase

class ActividadesFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_actividades, container, false)

        val btnCrearClase = view?.findViewById<View>(R.id.btnCrearClase) as Button
        val btnEditarClase = view?.findViewById<View>(R.id.btnEditarClase) as Button

        //Asigna listener para poder abrir Activity.
        btnCrearClase.setOnClickListener{ view: View ->
            val intent = Intent (activity , CrearClaseNueva::class.java)
            activity?.startActivity(intent)
        }

        btnEditarClase.setOnClickListener{ view: View ->
            val intent = Intent (activity , EditarClase::class.java)
            activity?.startActivity(intent)
        }
    }

}