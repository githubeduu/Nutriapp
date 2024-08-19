package com.example.nutriapp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.nutriapp.Elements.AlertDialog
import com.example.nutriapp.R

@Composable
fun RecoveryPassScreen(navController: NavController){
    var showDialog by remember {
        mutableStateOf(false)
    }

    if(showDialog){
        AlertDialog (
            onDismiss = { showDialog = false},
            message = "Se envió correo electrónico",
            navigateToLogin = { navController.navigate("login") }
        )
    }
    var correo by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Recuperar contraseña",
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.semantics { contentDescription = "Recuperar contraseña" }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = correo,
            onValueChange = { correo = it},
            label = { Text(text = "Correo electrónico", fontSize = 22.sp)},
            modifier = Modifier.focusable().semantics { contentDescription = "Correo electronico" }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { showDialog = true } ) {
            Text(text = "Ingresar",fontSize = 22.sp)
        }


    }

}