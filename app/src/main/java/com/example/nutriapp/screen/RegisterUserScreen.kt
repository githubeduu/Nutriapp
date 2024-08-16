package com.example.nutriapp.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nutriapp.Elements.AlertDialog

data class Usuario(val nombre: String, val Telefono: String, val correo: String, val contrasena: String)

@Composable
fun RegisterUserScreen(){

    var showDialog by remember {
        mutableStateOf(false)
    }

    if(showDialog){
        AlertDialog (
            onDismiss = { showDialog = false}
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

        Text(text = "Registro de usuario", fontSize = 28.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = nombre, onValueChange = {
            nombre = it
        }, label = {
            Text(text = "Nombre")
        })

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = telefono, onValueChange = {
            telefono = it
        }, label = {
            Text(text = "Teléfono")
        })

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = correo, onValueChange = {
            correo = it
        }, label = {
            Text(text = "Correo electronico")
        })

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = contrasena, onValueChange = {
            contrasena = it
        }, label = {
            Text(text = "Contraseña")
        }, visualTransformation = PasswordVisualTransformation())

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (nombre.isNotBlank() && telefono.isNotBlank() && correo.isNotBlank() && contrasena.isNotBlank()) {
                listaUsuario.add(Usuario(nombre, telefono, correo, contrasena))
                Log.i("RegisterUser", "Usuarios registrados: $listaUsuario")
                registrationMessage = "Usuario registrado correctamente"

                nombre = ""
                telefono = ""
                correo = ""
                contrasena = ""

                showDialog = true
            } else {
                registrationMessage = "Por favor complete todos los campos"
            }
        }) {
            Text(text = "Registrarse")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = registrationMessage,
            color = if(registrationMessage.contains("correctamente")) androidx.compose.ui.graphics.Color.Green
                    else androidx.compose.ui.graphics.Color.Red)

    }

}