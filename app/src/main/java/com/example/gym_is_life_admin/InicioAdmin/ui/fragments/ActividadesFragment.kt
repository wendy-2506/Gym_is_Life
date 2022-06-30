package com.example.gym_is_life_admin.InicioAdmin.ui.fragments

import android.content.Intent
import android.os.Bundle
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
import com.example.gym_is_life_admin.InicioAdmin.ModelAdmin.Actividades

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
        rvActividades.adapter = ActividadesAdapter(listActividades())


        //Asigna listener para poder abrir Activity.
        btnCrearClase.setOnClickListener{ view: View ->
            val intent = Intent (activity , CrearClaseNueva::class.java)
            activity?.startActivity(intent)
        }


        return view
    }
    private fun listActividades(): List<Actividades>{
        var lstActividad: ArrayList<Actividades> = ArrayList()
        lstActividad.add(Actividades(1,"In the end", "Hybrid Theory"))
        lstActividad.add(Actividades(2,"In the end", "Hybrid Theory"))
        lstActividad.add(Actividades(3,"In the end", "Hybrid Theory"))
        lstActividad.add(Actividades(4,"In the end", "Hybrid Theory"))
        lstActividad.add(Actividades(5,"In the end", "Hybrid Theory"))
        return lstActividad

    }

}