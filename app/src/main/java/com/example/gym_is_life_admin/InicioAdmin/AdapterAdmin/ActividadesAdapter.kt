package com.example.gym_is_life_admin.InicioAdmin.AdapterAdmin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gym_is_life_admin.InicioAdmin.ModelAdmin.Actividades
import com.example.gym_is_life_admin.R

class ActividadesAdapter(private var lstActividades: List<Actividades>)
    : RecyclerView.Adapter<ActividadesAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvCodigoActi: TextView = itemView.findViewById(R.id.tvCodigoActi)
        val tvNombre: TextView = itemView.findViewById(R.id.tvActividad)
        val tvFecha: TextView = itemView.findViewById(R.id.tvFecha)
        val btnEditarClase: Button = itemView.findViewById(R.id.btnEditarClase)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActividadesAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_actividades,parent,false))
    }

    override fun onBindViewHolder(holder: ActividadesAdapter.ViewHolder, position: Int) {
        val itemActividad = lstActividades[position]
        holder.tvCodigoActi.text = itemActividad.id.toString()
        holder.tvNombre.text = itemActividad.nombre
        holder.tvFecha.text = itemActividad.fecha

    }

    override fun getItemCount(): Int {
        return lstActividades.size
    }


}
