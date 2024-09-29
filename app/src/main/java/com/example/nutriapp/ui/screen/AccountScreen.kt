package com.example.nutriapp.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.nutriapp.data.repository.UserRepository
import com.example.nutriapp.ui.Elements.CustomAlertDialog
import kotlinx.coroutines.launch

@Composable
fun AccountScreen(navController: NavController) {
    var nombre by remember { mutableStateOf(TextFieldValue("")) }
    var telefono by remember { mutableStateOf(TextFieldValue("")) }
    var correo by remember { mutableStateOf("") }

    var errorMessage by remember { mutableStateOf<String?>(null) }
    var successMessage by remember { mutableStateOf<String?>(null) }

    var showUpdateDialog by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var deleteSuccess by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()

    // Cargar datos del usuario al iniciar la pantalla
    LaunchedEffect(Unit) {
        UserRepository.fetchUserData { fetchedNombre, fetchedTelefono, fetchedCorreo, error ->
            if (error == null) {
                nombre = TextFieldValue(fetchedNombre ?: "")
                telefono = TextFieldValue(fetchedTelefono ?: "")
                correo = fetchedCorreo ?: ""
            } else {
                errorMessage = error
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Mi Cuenta",
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.semantics { contentDescription = "Texto de bienvenida" }
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(text = "Correo: $correo", fontSize = 18.sp)

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier
                .fillMaxWidth() // Expandir todo el ancho
                .height(70.dp), // Aumentar la altura
            textStyle = TextStyle(fontSize = 28.sp) // Aumentar el tamaño del texto
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = telefono,
            onValueChange = { telefono = it },
            label = { Text("Teléfono") },
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp), // Aumentar la altura
            textStyle = TextStyle(fontSize = 28.sp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                coroutineScope.launch {
                    UserRepository.updateUserDetails(nombre.text, telefono.text) { success, error ->
                        if (success) {
                            successMessage = "Datos actualizados correctamente"
                            showUpdateDialog = true
                        } else {
                            errorMessage = error
                        }
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp) // Aumentar la altura del botón
        ) {
            Text(text = "Actualizar", fontSize = 30.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                coroutineScope.launch {
                    UserRepository.deleteUser { success, error ->
                        if (success) {
                            deleteSuccess = true
                            showDeleteDialog = true
                        } else {
                            errorMessage = error
                        }
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red), // Cambiar el color de fondo del botón
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {
            Text(text = "Eliminar cuenta", fontSize = 30.sp, color = Color.White)
        }

        TextButton(
            onClick = { navController.navigate("home") }
        ) {
            Text(text = "Volver", fontSize = 25.sp)
        }

        // Show update dialog
        if (showUpdateDialog) {
            CustomAlertDialog(
                onDismiss = { showUpdateDialog = false },
                message = successMessage ?: "Datos actualizados exitosamente",
                onConfirm = { showUpdateDialog = false }
            )
        }

        // Show delete dialog
        if (showDeleteDialog) {
            CustomAlertDialog(
                onDismiss = { showDeleteDialog = false },
                message = if (deleteSuccess) "Usuario eliminado exitosamente" else "Error al eliminar el usuario",
                onConfirm = {
                    navController.navigate("login")
                    showDeleteDialog = false
                }
            )
        }
    }
}

