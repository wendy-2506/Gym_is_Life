package com.example.gym_is_life_admin.ModelCreacion

data class NActividadModel(
    val actividad: String,
    val cantidad: Int,
    val fecha: String,
    val instructor: String,
    val nivel: String,
    val reglas: String,
    val salon: String
)
