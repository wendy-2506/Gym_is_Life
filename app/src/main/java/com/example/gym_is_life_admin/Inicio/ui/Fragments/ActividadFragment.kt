package com.example.gym_is_life_admin.Inicio.ui.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.gym_is_life_admin.Inicio.ClasesActivity
import com.example.gym_is_life_admin.R


class ActividadFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_actividad, container, false)
        val btnBuscarClase: Button = view.findViewById(R.id.btnHorario)
        btnBuscarClase.setOnClickListener { view: View ->
            val intent = Intent (activity, ClasesActivity::class.java)
            activity?.startActivity(intent)
        }
        return view
    }

}