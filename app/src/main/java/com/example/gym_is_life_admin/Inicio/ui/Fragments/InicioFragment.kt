package com.example.gym_is_life_admin.Inicio.ui.Fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.example.gym_is_life_admin.R
import com.google.firebase.firestore.FirebaseFirestore


class InicioFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_inicio, container, false)

        val tvSaludoInicio: TextView = view.findViewById(R.id.tvSaludoInicio)
        val imgBtnBuscarClase: ImageButton = view.findViewById(R.id.imgBtnBuscarClases)
        val tvFechaFinMem: TextView = view.findViewById(R.id.tvFechaFinMem)

        val db = FirebaseFirestore.getInstance()

        val db2 = FirebaseFirestore.getInstance()


        db.collection("usuario")
            .get()
            .addOnSuccessListener { result1 ->
                db2.collection("usuario_actual")
                    .get()
                    .addOnSuccessListener { result2 ->
                        for (document in result1) {
                            for (document2 in result2) {
                                //println(document2.data["dni"].toString())
                                //println(document.data["dni"].toString())
                                if (document2.data["dni"].toString() == document.data["dni"].toString()) {
                                    //println("Entroooooooo")
                                    tvFechaFinMem.text =
                                        "Estado de membresía: " + document.data["fechaFinMem"].toString()
                                    tvSaludoInicio.text =
                                        "¡Hola, " + document.data["nombre"].toString() + "!"
                                    break
                                }
                            }
                        }
                    }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }








        return view
    }

    fun leerDniUserActual(){

    }


}