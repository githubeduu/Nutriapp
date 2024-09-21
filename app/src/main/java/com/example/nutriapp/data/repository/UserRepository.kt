package com.example.nutriapp.data.repository

import androidx.compose.runtime.mutableStateListOf


object UserRepository {
    val usuarios = mutableStateListOf<Usuario>()
}

data class Usuario(val nombre: String, val telefono: String, val correo: String, val contrasena: String)
