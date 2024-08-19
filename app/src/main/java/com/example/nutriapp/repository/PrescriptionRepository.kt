package com.example.nutriapp.repository

import androidx.compose.runtime.mutableStateListOf

object PrescriptionRepository {
    val recetas = mutableStateListOf(
        Receta(
            "Ensalada de Quinoa y Vegetales",
            "Una ensalada rica en proteínas y fibra. La quinoa proporciona aminoácidos esenciales, mientras que los vegetales frescos aportan vitaminas y minerales. Ideal para una comida ligera y nutritiva."
        ),
        Receta(
            "Smoothie Verde Detox",
            "Un batido lleno de antioxidantes y vitaminas. Los ingredientes principales son espinacas, manzana verde y pepino, que ayudan a desintoxicar el cuerpo y mejorar la digestión."
        ),
        Receta(
            "Salmón a la Parrilla con Espárragos",
            "El salmón es una excelente fuente de ácidos grasos omega-3, que son buenos para el corazón. Los espárragos añaden fibra y vitaminas. Perfecto para una cena saludable y equilibrada."
        ),
        Receta(
            "Tacos de Pollo con Salsa de Aguacate",
            "Estos tacos son ricos en proteínas y grasas saludables gracias al pollo y el aguacate. Acompañados de vegetales frescos, son una opción deliciosa y nutritiva para el almuerzo."
        ),
        Receta(
            "Pudding de Chía con Frutas",
            "Un postre nutritivo y bajo en calorías. Las semillas de chía son ricas en ácidos grasos omega-3 y fibra. Combínalo con frutas frescas para obtener un snack saludable y lleno de energía."
        )
    )

    data class Receta(
        val nombre: String,
        val descripcion: String
    )
}