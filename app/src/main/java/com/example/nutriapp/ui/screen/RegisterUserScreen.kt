package com.example.nutriapp.ui.screen

import android.util.Log
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import com.example.nutriapp.auth.FirebaseAuthService
import com.example.nutriapp.ui.Elements.CustomAlertDialog
import com.example.nutriapp.data.repository.UserRepository
import com.example.nutriapp.data.repository.Usuario

@Composable
fun RegisterUserScreen(navController: NavController){

    val authService = FirebaseAuthService()
    var showDialog by remember {
        mutableStateOf(false)
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

    var nombreError by remember { mutableStateOf<String?>(null) }
    var telefonoError by remember { mutableStateOf<String?>(null) }
    var correoError by remember { mutableStateOf<String?>(null) }
    var contrasenaError by remember { mutableStateOf<String?>(null) }
    val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()

    //Funcion de orden superior para manejar cambio de valor
    fun handleTextChange(onValueChange: (String) -> Unit, errorSetter: (String?) -> Unit): (String) -> Unit {
        return { newValue ->
            onValueChange(newValue)
            if (newValue.isNotBlank()) {
                errorSetter(null)
            }
        }
    }

    fun validateInputs(): Boolean {
        var isValid = true

        if (nombre.isBlank()) {
            nombreError = "Nombre no puede estar vacío"
            isValid = false
        } else {
            nombreError = null
        }

        if (telefono.isBlank()) {
            telefonoError = "Teléfono no puede estar vacío"
            isValid = false
        } else {
            telefonoError = null
        }

        if (correo.isBlank()) {
            correoError = "Correo electrónico no puede estar vacío"
            isValid = false
        } else if (!correo.matches(emailRegex)) {
            correoError = "Ingrese un correo electrónico válido"
            isValid = false
        } else {
            correoError = null
        }

        if (contrasena.isBlank()) {
            contrasenaError = "Contraseña no puede estar vacía"
            isValid = false
        } else {
            contrasenaError = null
        }

        return isValid
    }
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
            onValueChange = handleTextChange({ nombre = it }, { nombreError = it }),
            label = { Text(text = "Nombre", fontSize = 22.sp) },
            modifier = Modifier.focusable().semantics { contentDescription = "Ingrese nombre" },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions.Default,
            isError = nombreError != null
        )
        nombreError?.let {
            Text(text = it, color = androidx.compose.ui.graphics.Color.Red)
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = telefono,
            onValueChange = handleTextChange({ telefono = it }, { telefonoError = it }),
            label = { Text(text = "Teléfono", fontSize = 22.sp) },
            modifier = Modifier.focusable().semantics { contentDescription = "Ingrese telefono" },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions.Default,
            isError = telefonoError != null
        )
        telefonoError?.let {
            Text(text = it, color = androidx.compose.ui.graphics.Color.Red)
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = correo,
            onValueChange = handleTextChange({ correo = it }, { correoError = it }),
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

        OutlinedTextField(
            value = contrasena,
            onValueChange = handleTextChange({ contrasena = it }, { contrasenaError = it }),
            label = { Text(text = "Contraseña", fontSize = 22.sp) },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.focusable().semantics { contentDescription = "Ingrese contraseña" },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions.Default,
            isError = contrasenaError != null
        )
        contrasenaError?.let {
            Text(text = it, color = androidx.compose.ui.graphics.Color.Red)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (validateInputs()) {
                // Llamada al método de registro de FirebaseAuthService
                authService.register(correo, contrasena) { isSuccess, errorMessage ->
                    if (isSuccess) {
                        UserRepository.usuarios.add(Usuario(nombre, telefono, correo, contrasena))
                        Log.i("RegisterUser", "Usuario registrado: $correo")
                        nombre = ""
                        telefono = ""
                        correo = ""
                        contrasena = ""
                        showDialog = true
                    } else {
                        // Manejo de error
                        contrasenaError = errorMessage // Mostrar el error en el campo de contraseña
                    }
                }
            }
        },
            modifier = Modifier.semantics { contentDescription = "Botón de registro de usuario" }
        ) {
            Text(text = "Registrarse", fontSize = 22.sp)
        }
    }

    if(showDialog){
        CustomAlertDialog (
            onDismiss = { showDialog = false},
            message = "Registro de usuario exitosa",
            navigateToLogin = {  navController.navigate("login") }
        )
    }
}