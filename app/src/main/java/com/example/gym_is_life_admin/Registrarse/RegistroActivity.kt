package com.example.gym_is_life_admin.Registrarse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.gym_is_life_admin.Login.LoginActivity
import com.example.gym_is_life_admin.MainActivity
import com.example.gym_is_life_admin.R

class RegistroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        val txtApellidos: EditText = findViewById(R.id.txtApellidos)
        val txtNombres: EditText = findViewById(R.id.txtNombres)
        val txtCorreo: EditText = findViewById(R.id.txtCorreo)
        val txtDNI: EditText = findViewById(R.id.txtDNI)
        val txtContraseña: EditText = findViewById(R.id.txtContraseña)

        val btnRegistro: Button = findViewById(R.id.btnRegistro)
        val btnCancelar: Button = findViewById(R.id.btnCancelar)

        btnRegistro.setOnClickListener(){
            this.Registro();
        }

        btnCancelar.setOnClickListener(){
            this.Cancelar();
        }

    }


    private fun Cancelar(){
        val builder = AlertDialog.Builder(this)
        val intent = Intent(this, MainActivity::class.java)
        builder.setTitle("Androidly Alert")
        builder.setMessage("¿Está seguro que desea cancelar el registro?")
//builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

        builder.setPositiveButton("Si") { dialog, which ->
            startActivity(intent)
        }

        builder.setNegativeButton("No") { dialog, which ->
        }

        builder.show()
    }

    private fun Registro(){
        val builder = AlertDialog.Builder(this)
        val intent = Intent(this, LoginActivity::class.java)
        builder.setTitle("Androidly Alert")
        builder.setMessage("Se creo satisfactoriamente el usuario\n" +
                "Ingresar con su DNI y la contraseña\n" +
                "ingresada")
//builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

        builder.setPositiveButton("OK") { dialog, which ->
            startActivity(intent)
        }
        builder.show()
    }
}