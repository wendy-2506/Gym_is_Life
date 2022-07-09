package com.example.gym_is_life_admin.Inicio.AdapterUsuario

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.gym_is_life_admin.Inicio.ClasesActivity
import com.example.gym_is_life_admin.Inicio.ModelUsuario.Clases
import com.example.gym_is_life_admin.R
import com.google.firebase.firestore.FirebaseFirestore

class ClasesAdapter(private var lstClases: List<Clases>)
    : RecyclerView.Adapter<ClasesAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvDisciplina: TextView = itemView.findViewById(R.id.tvDisciplinaU)
        val tvInstructor: TextView = itemView.findViewById(R.id.tvInstructorU)
        val tvHorario: TextView = itemView.findViewById(R.id.tvHorarioU)
        val tvNivel: TextView = itemView.findViewById(R.id.tvNivelU)
        val tvAforo: TextView = itemView.findViewById(R.id.tvAforoU)
        val tvSalon: TextView = itemView.findViewById(R.id.tvSalonU)
        val tvReglas: TextView = itemView.findViewById(R.id.tvReglasU)
        val btnInscribirse: Button = itemView.findViewById(R.id.btnInscribirse)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClasesAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_clases,parent,false))
    }

    override fun onBindViewHolder(holder: ClasesAdapter.ViewHolder, position: Int) {
        val db = FirebaseFirestore.getInstance()
        val db2 = FirebaseFirestore.getInstance()
        val itemClases = lstClases[position]
        holder.tvDisciplina.text = itemClases.Disciplina
        holder.tvInstructor.text = itemClases.Instructor
        holder.tvNivel.text = itemClases.Nivel
        holder.tvHorario.text = itemClases.Horario
        holder.tvSalon.text = itemClases.Salon
        holder.tvAforo.text = itemClases.Aforo.toString()
        holder.tvReglas.text = itemClases.Reglas
        holder.btnInscribirse.setOnClickListener{
            val builder = AlertDialog.Builder(holder.tvNivel.context)
            val intent = Intent(holder.tvNivel.context, ClasesActivity::class.java)

            db.collection("clase")
                .get()
                .addOnSuccessListener { result ->
                    db2.collection("usuario_actual")
                        .get()
                        .addOnSuccessListener { result2 ->
                            for (document in result) {
                                for (document2 in result2) {

                                    if (holder.tvDisciplina.text == document.data["actividad"].toString() &&
                                        holder.tvInstructor.text == document.data["instructor"].toString() &&
                                        holder.tvNivel.text == document.data["nivel"].toString() &&
                                        holder.tvHorario.text == document.data["fecha"].toString() &&
                                        holder.tvSalon.text == document.data["salon"].toString() &&
                                        holder.tvAforo.text == document.data["cantidad"].toString() &&
                                        holder.tvReglas.text == document.data["reglas"].toString()
                                    ) {
                                        println("Entro IF")
                                        val idClase: String = document.id

                                        val claseUser = hashMapOf(
                                            "idClase" to idClase,
                                            "idUsuario" to document2.data["dni"].toString()
                                        )

                                        //val id: UUID = UUID.randomUUID()

                                        db.collection("clase_usuario")
                                            .add(claseUser)

                                    }

                                }
                            }
                        }

                }





            builder.setTitle("Androidly Alert")
            builder.setMessage("Se inscribió correctamente")
//builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

            builder.setPositiveButton("OK") { dialog, which ->

                holder.tvNivel.context.startActivity(intent)
            }
            builder.show()
        }

    }


    override fun getItemCount(): Int {
        return lstClases.size
    }


}
