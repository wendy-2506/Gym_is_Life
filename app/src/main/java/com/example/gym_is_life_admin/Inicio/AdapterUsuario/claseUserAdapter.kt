package com.example.gym_is_life_admin.Inicio.AdapterUsuario

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gym_is_life_admin.Inicio.ModelUsuario.clase_User
import com.example.gym_is_life_admin.R
import com.google.firebase.firestore.FirebaseFirestore

class claseUserAdapter (private var lstClaseUser: List<clase_User>)
    : RecyclerView.Adapter<claseUserAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvDisciplina: TextView = itemView.findViewById(R.id.tvDisUsuario)
        val tvInstructor: TextView = itemView.findViewById(R.id.tvInsUsuario)
        val tvHorario: TextView = itemView.findViewById(R.id.tvHrUsuario)
        val tvNivel: TextView = itemView.findViewById(R.id.tvNvlUsuario)
        val tvSalon: TextView = itemView.findViewById(R.id.tvSalUsuario)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): claseUserAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return claseUserAdapter.ViewHolder(layoutInflater.inflate(R.layout.item_horario, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemClasesUsuario = lstClaseUser[position]
        holder.tvDisciplina.text = itemClasesUsuario.discUser
        holder.tvHorario.text = itemClasesUsuario.horUser
        holder.tvInstructor.text = itemClasesUsuario.insUser
        holder.tvNivel.text = itemClasesUsuario.nvlUser
        holder.tvSalon.text = itemClasesUsuario.salUser

    }

    override fun getItemCount(): Int {
        return lstClaseUser.size
    }

}

