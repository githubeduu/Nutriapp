package com.example.nutriapp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.nutriapp.R

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

    val listaUsuario = remember { mutableStateListOf<Usuario>() }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.logo), contentDescription = "Login imagen",
        modifier = Modifier.size(200.dp))

        Spacer(modifier = Modifier.height(4.dp))

        Text(text = "Bienvenido", fontSize = 28.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = correo, onValueChange = {
            correo = it
        }, label = {
            Text(text = "Correo electrónico")
        })

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = contrasena, onValueChange = {
            contrasena = it
        }, label = {
            Text(text = "Contraseña")
        }, visualTransformation = PasswordVisualTransformation())

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val user = listaUsuario.find { it.correo == correo && it.contrasena == contrasena }
            if (user != null) {
                mensajeLogin = "Inicio de sesión exitoso"
                navController.navigate("prescription")
            } else {
                mensajeLogin = "Correo o contraseña incorrectos"
            }
        }) {
            Text(text = "Ingresar")
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
            Text(text = "Registrarse")
        }

        Spacer(modifier = Modifier.height(4.dp))

        TextButton(onClick = {
            navController.navigate("recoveryPass")
        }) {
            Text(text = "Recuperar Contraseña")
        }


    }

}