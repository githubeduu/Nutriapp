package com.example.nutriapp.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.nutriapp.auth.FirebaseAuthService

@Composable
fun HomeScreen(navController: NavController) {
    val authService = FirebaseAuthService()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                navController.navigate("account")
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
        ) {
            Text(text = "Mi cuenta", fontSize = 30.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                navController.navigate("nutritionalRecipe")
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
        ) {
            Text(text = "Minuta semanal", fontSize = 30.sp)
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(
            onClick = {
                authService.logout() { success ->
                    if (success) {
                        navController.navigate("login")
                    } else {
                        Log.e("Logout", "Error al cerrar sesión")
                    }
                }
            }
        ) {
            Text(text = "Cierre Sesión", fontSize = 25.sp)
        }
    }
}
