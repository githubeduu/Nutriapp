package com.example.nutriapp.repository

import androidx.compose.runtime.mutableStateListOf

object PrescriptionRepository {
    val recetas = mutableStateListOf(
        Receta(
            "Ensalada Chilena",
            "Una ensalada fresca y saludable hecha con tomate, cebolla, cilantro y aliñada con aceite de oliva y limón. Es ideal como acompañamiento o como una comida ligera por sí sola."
        ),
        Receta(
            "Ceviche de Salmón",
            "El ceviche de salmón es una opción ligera y rica en omega-3. Se prepara con salmón crudo marinado en jugo de limón, con cebolla morada, cilantro y ají. Perfecto para un almuerzo fresco."
        ),
        Receta(
            "Quinoa con Verduras al Horno",
            "La quinoa es una excelente fuente de proteínas y fibra. Esta receta incluye quinoa cocida mezclada con verduras como pimientos, zapallo y espárragos, todas asadas al horno con un toque de aceite de oliva."
        ),
        Receta(
            "Sopa de Porotos Verdes",
            "Una sopa nutritiva hecha con porotos verdes, zanahorias, apio y espinacas. Es rica en fibra y vitaminas, ideal para una comida reconfortante y saludable."
        ),
        Receta(
            "Pasta Integral con Salsa de Tomate y Albahaca",
            "Pasta integral acompañada de una salsa de tomate casera con albahaca fresca. Esta receta es una opción saludable que proporciona fibra y antioxidantes, ideal para una cena ligera."
        )
    )

    data class Receta(
        val nombre: String,
        val descripcion: String
    )
}