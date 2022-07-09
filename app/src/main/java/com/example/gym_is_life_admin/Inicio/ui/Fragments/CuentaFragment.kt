package com.example.gym_is_life_admin.Inicio.ui.Fragments

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.gym_is_life_admin.Inicio.InicioActivity
import com.example.gym_is_life_admin.InicioAdmin.AdminActivity
import com.example.gym_is_life_admin.R
import com.google.firebase.firestore.FirebaseFirestore

class CuentaFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_cuenta, container, false)
        val tvNombre: TextView = view.findViewById(R.id.tvNomUser)
        val tvApellido: TextView = view.findViewById(R.id.tvApelUser)
        val tvDni: TextView = view.findViewById(R.id.tvDniUser)
        val tvCuenta: TextView = view.findViewById(R.id.tvCorreoUser)
        val plContraNew: TextView = view.findViewById(R.id.plNuevaContra)
        val btnActualizar: Button = view.findViewById(R.id.btnAcUsuario)
        val db = FirebaseFirestore.getInstance()

        val db2 = FirebaseFirestore.getInstance()
        db.collection("usuario")
            .get()
            .addOnSuccessListener { result ->
                db2.collection("usuario_actual")
                    .get()
                    .addOnSuccessListener { result2 ->
                        for (document in result){
                            for (document2 in result2){
                                if (document2.data["dni"].toString() == document.data["dni"].toString()) {
                                    tvNombre.text = document.data["nombre"].toString()
                                    tvApellido.text = document.data["apellido"].toString()
                                    tvDni.text = document.data["dni"].toString()
                                    tvCuenta.text = document.data["correo"].toString()
                                }
                            }
                        }
                    }
            }
        btnActualizar.setOnClickListener { view: View->
            db.collection("usuario")
                .get()
                .addOnSuccessListener { result ->
                    db2.collection("usuario_actual")
                        .get()
                        .addOnSuccessListener { result2 ->
                            for (document in result){
                                for(document2 in result2){
                                    if (document.data["dni"].toString() == document2.data["dni"].toString() ){
                                        val id: String = document.id

                                        db.collection("usuario")
                                            .document(id)
                                            .update("contrase", plContraNew.text.toString() )

                                    }
                                }

                            }


                        }

                }
            val builder = AlertDialog.Builder(tvDni.context)
            val intent = Intent(tvDni.context, InicioActivity::class.java)
            builder.setTitle("Androidly Alert")
            builder.setMessage("Se actualizó su contraseña")
//builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

            builder.setPositiveButton("OK") { dialog, which ->
                startActivity(intent)
            }
            builder.show()


        }


        return view
    }


}