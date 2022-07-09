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
import com.example.gym_is_life_admin.Inicio.InicioActivity
import com.example.gym_is_life_admin.InicioAdmin.AdminActivity
import com.example.gym_is_life_admin.InicioAdmin.ModelAdmin.Usuarios
import com.example.gym_is_life_admin.R
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

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

        val db = Firebase.firestore
        holder.btnVer.setOnClickListener {
            var dni: Int = itemUsuarios.dni.toString().toInt()
            db.collection("usuario")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        //Prueba git
                        if(itemUsuarios.dni.toString().toInt() == document.data["dni"].toString().toInt()){
                            //Toast.makeText(plUsuario.context,"Incio de sesión exitoso", Toast.LENGTH_LONG).show()
                            val intent = Intent(holder.tvNombre.context, AdminRenovarMembresia::class.java)
                            //intent.putExtra("dni", document.data["dni"].toString().toInt())
                            saveDniUser(document.data["dni"].toString().toInt())
                            holder.tvNombre.context.startActivity(intent)

                        }
                    }
                }
        }
    }

    fun saveDniUser(dni: Int){
        val db = Firebase.firestore
        db.collection("usuario_membresia")
            .document("elBVYXqLXqK1oWcp0l8J")
            .update("dni", dni)
    }

    override fun getItemCount(): Int {
        return lstUsuarios.size
    }
}
