package com.example.nutriapp.ui.screen

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nutriapp.data.repository.NutritionalRepository

@Composable
fun NutritionalRecipeScreen() {
    val scrollState = rememberScrollState()
    val recetas = NutritionalRepository.recetas

    LaunchedEffect(Unit) {
        NutritionalRepository.fetchRecetas()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Minuta Semanal",
            fontSize = 36.sp,
            color = Color(0xFF003366),
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Grouping the recipes by day
        val recetasPorDia = recetas.groupBy { it.dia }

        recetasPorDia.forEach { (dia, recetasDelDia) ->
            // Display the day as a header
            Text(
                text = dia,
                fontSize = 28.sp,
                color = Color(0xFF003366),
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(vertical = 12.dp)
            )

            recetasDelDia.forEach { receta ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = receta.nombre,
                            fontSize = 22.sp,
                            color = Color(0xFF000000),
                            style = MaterialTheme.typography.headlineMedium
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = receta.descripcion,
                            fontSize = 18.sp,
                            color = Color(0xFF333333),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }

            // Add space after each day section
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
