package com.example.nutriapp.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.nutriapp.R
import com.example.nutriapp.auth.FirebaseAuthService
import com.example.nutriapp.data.repository.UserRepository
import com.example.nutriapp.ui.Elements.CustomAlertDialog

@Composable
fun LoginScreen(navController: NavController) {

    val authService = FirebaseAuthService()
    var correo by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var mostrarDialogo by remember { mutableStateOf(false) }  // Estado para controlar el popup
    var mensajeError by remember { mutableStateOf("") }       // Estado para el mensaje de error

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(bottom = 100.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Contenido de la pantalla de login
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo de la aplicación",
            modifier = Modifier
                .size(300.dp)
                .semantics { contentDescription = "Logo de la aplicación" }
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Bienvenido",
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.semantics { contentDescription = "Texto de bienvenida" }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = correo,
            onValueChange = { correo = it },
            label = { Text(text = "Correo electrónico", fontSize = 28.sp) },
            modifier = Modifier
                .focusable()
                .semantics { contentDescription = "Correo electronico" },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions.Default
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = contrasena,
            onValueChange = { contrasena = it },
            label = { Text(text = "Contraseña", fontSize = 28.sp) },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .focusable()
                .semantics { contentDescription = "Contraseña" },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions.Default
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                authService.login(correo, contrasena) { isSuccess, errorMessage ->
                    if (isSuccess) {
                        // Login exitoso, navegar a la siguiente pantalla
                        navController.navigate("home")
                    } else {
                        // Mostrar el popup con el mensaje de error
                        mensajeError = errorMessage ?: "Error desconocido"
                        mostrarDialogo = true
                    }
                }
            },
            modifier = Modifier
        ) {
            Text(text = "Ingresar", fontSize = 30.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = {
            navController.navigate("registerUser")
        }) {
            Text(text = "Registrarse", fontSize = 25.sp)
        }

        Spacer(modifier = Modifier.height(2.dp))

        TextButton(onClick = {
            navController.navigate("recoveryPass")
        }) {
            Text(text = "Recuperar Contraseña", fontSize = 25.sp)
        }

        // Mostrar el CustomAlertDialog cuando `mostrarDialogo` es true
        if (mostrarDialogo) {
            CustomAlertDialog(
                onDismiss = { mostrarDialogo = false },
                onConfirm = { mostrarDialogo = false },
                message = mensajeError
            )
        }
    }
}
