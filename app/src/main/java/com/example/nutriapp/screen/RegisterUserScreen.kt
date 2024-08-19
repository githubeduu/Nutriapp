package com.example.nutriapp.screen

import android.util.Log
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.input.*
import androidx.navigation.NavController
import com.example.nutriapp.Elements.AlertDialog
import com.example.nutriapp.repository.UserRepository


data class Usuario(val nombre: String, val Telefono: String, val correo: String, val contrasena: String)

@Composable
fun RegisterUserScreen(navController: NavController){

    var showDialog by remember {
        mutableStateOf(false)
    }

    if(showDialog){
        AlertDialog (
            onDismiss = { showDialog = false},
            message = "Registro de usuario exitosa",
            navigateToLogin = {  navController.navigate("login") }
        )
    }

    val listaUsuario = remember {
        mutableStateListOf<Usuario>()
    }
    var nombre by remember {
        mutableStateOf("")
    }

    var telefono by remember {
        mutableStateOf("")
    }
    var correo by remember {
        mutableStateOf("")
    }
    var contrasena by remember {
        mutableStateOf("")
    }
    var registrationMessage by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Registro de usuario",
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.semantics { contentDescription = "Registro de usuario" }
            )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it},
            label = { Text(text = "Nombre",fontSize = 22.sp)},
            modifier = Modifier.focusable().semantics { contentDescription = "Ingrese nombre" },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions.Default
            )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = telefono,
            onValueChange = { telefono = it},
            label = { Text(text = "Teléfono",fontSize = 22.sp)},
            modifier = Modifier.focusable().semantics { contentDescription = "Ingrese telefono" },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions.Default
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = correo,
            onValueChange = { correo = it },
            label = { Text(text = "Correo electronico",fontSize = 22.sp)},
            modifier = Modifier.focusable().semantics { contentDescription = "Ingrese correo electronico" },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions.Default
            )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = contrasena,
            onValueChange = { contrasena = it},
            label = { Text(text = "Contraseña",fontSize = 22.sp)},
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.focusable().semantics { contentDescription = "Ingrese contraseña" },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions.Default
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (nombre.isNotBlank() && telefono.isNotBlank() && correo.isNotBlank() && contrasena.isNotBlank()) {
                UserRepository.usuarios.add(Usuario(nombre, telefono, correo, contrasena))
                Log.i("RegisterUser", "Usuarios registrados: $listaUsuario")
                nombre = ""
                telefono = ""
                correo = ""
                contrasena = ""

                showDialog = true
            } else {
                registrationMessage = "Por favor complete todos los campos"
            }
         },
            modifier = Modifier.semantics { contentDescription = "Botón de registro de usuario" }
        ){
            Text(text = "Registrarse",fontSize = 22.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = registrationMessage,
            color = if(registrationMessage.contains("correctamente")) androidx.compose.ui.graphics.Color.Green
                    else androidx.compose.ui.graphics.Color.Red)

    }

}