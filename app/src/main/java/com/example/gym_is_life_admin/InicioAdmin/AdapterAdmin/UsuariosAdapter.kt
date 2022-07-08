package com.example.gym_is_life_admin.InicioAdmin.AdapterAdmin

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gym_is_life_admin.AdminRenovarMembresia
import com.example.gym_is_life_admin.EditarClase
import com.example.gym_is_life_admin.InicioAdmin.ModelAdmin.Usuarios
import com.example.gym_is_life_admin.R

class UsuariosAdapter (private var lstUsuarios: List<Usuarios>)
: RecyclerView.Adapter<UsuariosAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvDNI: TextView = itemView.findViewById(R.id.tvDNI)
        val tvNombre: TextView = itemView.findViewById(R.id.tvUsuario)
        val tvEstado: TextView = itemView.findViewById(R.id.tvEstado)
        val btnVer: Button = itemView.findViewById(R.id.btnVer)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuariosAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_usuario, parent, false))
    }

    override fun onBindViewHolder(holder: UsuariosAdapter.ViewHolder, position: Int) {
        val itemUsuarios = lstUsuarios[position]
        holder.tvDNI.text = itemUsuarios.dni.toString()
        holder.tvNombre.text = itemUsuarios.nombre
        holder.tvEstado.text = itemUsuarios.estado
        holder.btnVer.setOnClickListener {
            val intent = Intent(holder.tvNombre.context, AdminRenovarMembresia::class.java)
            intent.putExtra("dni", holder.tvDNI.text )
            holder.tvNombre.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return lstUsuarios.size
    }
}
