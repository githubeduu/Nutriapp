package com.example.nutriapp.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await


object UserRepository {
    private val firestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    // Obtener datos del usuario
    suspend fun fetchUserData(onResult: (String?, String?, String?, String?) -> Unit) {
        val user = auth.currentUser
        if (user != null) {
            try {
                val document = firestore.collection("usuarios").document(user.uid).get().await()
                val nombre = document.getString("nombre") ?: ""
                val telefono = document.getString("telefono") ?: ""
                val correo = document.getString("correo") ?: ""
                onResult(nombre, telefono, correo, null)
            } catch (e: Exception) {
                onResult(null, null, null, "Error al obtener datos del usuario")
            }
        } else {
            onResult(null, null, null, "Usuario no autenticado")
        }
    }

    // Actualizar datos del usuario
    suspend fun updateUserDetails(nombre: String, telefono: String, onComplete: (Boolean, String?) -> Unit) {
        val user = auth.currentUser
        if (user != null) {
            val updates = mapOf(
                "nombre" to nombre,
                "telefono" to telefono
            )
            try {
                firestore.collection("usuarios").document(user.uid).update(updates).await()
                onComplete(true, null)
            } catch (e: Exception) {
                onComplete(false, "Error al actualizar los datos")
            }
        } else {
            onComplete(false, "Usuario no autenticado")
        }
    }

    // Eliminar usuario de Firestore y Firebase Authentication
    suspend fun deleteUser(onComplete: (Boolean, String?) -> Unit) {
        val user = auth.currentUser
        if (user != null) {
            try {
                firestore.collection("usuarios").document(user.uid).delete().await()
                user.delete().await()
                onComplete(true, null)
            } catch (e: Exception) {
                onComplete(false, "Error al eliminar la cuenta")
            }
        } else {
            onComplete(false, "Usuario no autenticado")
        }
    }
}
