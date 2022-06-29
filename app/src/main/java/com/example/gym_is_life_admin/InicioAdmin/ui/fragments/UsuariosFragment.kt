package com.example.gym_is_life_admin.InicioAdmin.ui.fragments

import android.os.Bundle
import android.content.Intent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gym_is_life_admin.AdminRenovarMembresia
import com.example.gym_is_life_admin.InicioAdmin.AdapterAdmin.ActividadesAdapter
import com.example.gym_is_life_admin.InicioAdmin.AdapterAdmin.UsuariosAdapter
import com.example.gym_is_life_admin.InicioAdmin.ModelAdmin.Actividades
import com.example.gym_is_life_admin.InicioAdmin.ModelAdmin.Usuarios
import com.example.gym_is_life_admin.R

class UsuariosFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_usuarios, container, false)

        val rvUsuarios: RecyclerView = view.findViewById(R.id.rvUsuarios)
        rvUsuarios.layoutManager = LinearLayoutManager(requireContext())
        rvUsuarios.adapter = UsuariosAdapter(listUsuarios())

        return view
    }
    private fun listUsuarios(): List<Usuarios>{
        var lstUsuario: ArrayList<Usuarios> = ArrayList()
        lstUsuario.add(Usuarios(1,"In the end", "Hybrid Theory"))
        return lstUsuario

    }
}