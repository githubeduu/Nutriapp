package com.example.nutriapp.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PrescriptionScreen() {
    val scrollState = rememberScrollState()
    val recetas = listOf(
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = "Minuta Semanal", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        recetas.forEach { receta ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = receta.nombre, style = MaterialTheme.typography.headlineMedium)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = receta.descripcion, style = MaterialTheme.typography.bodyLarge)
                }
            }
        }
    }
}

data class Receta(val nombre: String, val descripcion: String)