package com.example.nutriapp.ui.screen

import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.nutriapp.ui.Elements.CustomAlertDialog

@Composable
fun RecoveryPassScreen(navController: NavController){
    var showDialog by remember {
        mutableStateOf(false)
    }

    var correo by remember {
        mutableStateOf("")
    }
    var correoError by remember { mutableStateOf<String?>(null) }
    val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()

    fun validateInputs(): Boolean {
        var isValid = true

        if (correo.isBlank()) {
            correoError = "Correo electrónico no puede estar vacío"
            isValid = false
        } else if (!correo.matches(emailRegex)) {
            correoError = "Ingrese un correo electrónico válido"
            isValid = false
        } else {
            correoError = null
        }
        return isValid
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 100.dp)
            .padding(16.dp),
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
            onValueChange = {
                correo = it
                if (it.isNotBlank()) {
                    correoError = null
                }
            },
            label = { Text(text = "Correo electronico", fontSize = 22.sp) },
            modifier = Modifier.focusable()
                .semantics { contentDescription = "Ingrese correo electronico" },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions.Default,
            isError = correoError != null
        )
        correoError?.let {
            Text(text = it, color = androidx.compose.ui.graphics.Color.Red)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (validateInputs()) {
                correo = ""
                showDialog = true
            }
        },
            modifier = Modifier.semantics { contentDescription = "Recuperación de contraseña" }
        ) {
            Text(text = "Recuperar contraseña", fontSize = 22.sp)
        }

        TextButton(
            onClick = { navController.navigate("login") }
        ) {
            Text(text = "Volver", fontSize = 25.sp)
        }
    }



    if (showDialog) {
        CustomAlertDialog(
            onDismiss = { showDialog = false },
            message = "Registro de usuario exitoso",
            onConfirm = { navController.navigate("login") }
        )
    }
}