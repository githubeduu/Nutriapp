package com.example.nutriapp.data.repository

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import com.example.nutriapp.data.model.Receta
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

object NutritionalRepository{
    private val firestore = FirebaseFirestore.getInstance()
    val recetas = mutableStateListOf<Receta>()

    suspend fun fetchRecetas() {
        recetas.clear()
        try {
            val snapshot = firestore.collection("recetas").get().await()
            for (document in snapshot.documents) {
                val recetaList = document.get("receta") as? List<Map<String, Any>>
                recetaList?.forEach { recetaMap ->
                    val nombre = recetaMap["nombre"] as? String
                    val descripcion = recetaMap["descripcion"] as? String
                    if (nombre != null && descripcion != null) {
                        val receta = Receta(nombre, descripcion)
                        recetas.add(receta)
                    }
                }
            }
        }catch (e: Exception) {
            Log.e("NutritionalRepository", "Error fetching recetas: ${e.message}", e)
        }
    }
}