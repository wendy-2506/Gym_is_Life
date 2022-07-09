package com.example.gym_is_life_admin.Registrarse.models

data class UsuarioModel(
    val apellido: String,
    val nombre: String,
    val correo: String,
    val dni: Int,
    val contrase: String,
    val estado: String,
    val tipo_user: Boolean,
    val fechaFinMem: String,
)
