package com.example.nutriapp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.example.nutriapp.repository.UserRepository

@Composable
fun LoginScreen(navController: NavController){

    var correo by remember {
        mutableStateOf("")
    }

    var contrasena by remember {
        mutableStateOf("")
    }

    var mensajeLogin by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo de la aplicación",
            modifier = Modifier.size(300.dp).semantics { contentDescription = "Logo de la aplicación" })

        Spacer(modifier = Modifier.height(4.dp))

        Text(text = "Bienvenido",
             fontSize = 35.sp,
             fontWeight = FontWeight.Bold,
             color = MaterialTheme.colorScheme.primary,
             modifier = Modifier.semantics { contentDescription = "Texto de bienvenida" }
            )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = correo,
            onValueChange = { correo = it},
            label = { Text(text = "Correo electrónico",fontSize = 22.sp) },
            modifier = Modifier.focusable().semantics { contentDescription = "Correo electronico" },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions.Default)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = contrasena,
            onValueChange = { contrasena = it },
            label = { Text(text = "Contraseña",fontSize = 22.sp)},
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.focusable().semantics { contentDescription = "Contraseña" },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions.Default
            )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val user = UserRepository.usuarios.find { it.correo == correo && it.contrasena == contrasena }
            if (user != null) {
                mensajeLogin = "Inicio de sesión exitoso"
                navController.navigate("prescription")
            } else {
                mensajeLogin = "Correo o contraseña incorrectos"
            }
        },
            modifier = Modifier.semantics { contentDescription = "Botón inicio de sesión" }
        ){
            Text(text = "Ingresar", fontSize = 25.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = mensajeLogin,
            color = if (mensajeLogin.contains("exitoso")) androidx.compose.ui.graphics.Color.Green
            else androidx.compose.ui.graphics.Color.Red
        )

        Spacer(modifier = Modifier.height(32.dp))

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


    }

}