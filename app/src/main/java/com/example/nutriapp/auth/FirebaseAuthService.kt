package com.example.nutriapp.auth
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.firestore.FirebaseFirestore


open class FirebaseAuthService {
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    fun register(correo: String, contrasena: String, nombre: String, telefono: String, onComplete: (Boolean, String?) -> Unit) {
        firebaseAuth.createUserWithEmailAndPassword(correo, contrasena)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userId = task.result?.user?.uid
                    if (userId != null) {
                        // Guardar en Firestore
                        val user = hashMapOf(
                            "nombre" to nombre,
                            "telefono" to telefono,
                            "correo" to correo
                        )

                        firestore.collection("usuarios")
                            .document(userId)  // Usamos el UID del usuario autenticado como documento
                            .set(user)
                            .addOnSuccessListener {
                                onComplete(true, null)
                            }
                            .addOnFailureListener { e ->
                                onComplete(false, e.message)
                            }
                    } else {
                        onComplete(false, "Error al obtener UID del usuario")
                    }
                } else {
                    onComplete(false, task.exception?.message)
                }
            }
    }

    fun login(email: String, password: String, onComplete: (Boolean, String?) -> Unit) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onComplete(true, null)
                } else {
                    val exception = task.exception
                    val errorMessage = when (exception) {
                        is FirebaseAuthInvalidCredentialsException -> "La contraseña o el correo es incorrecto."
                        is FirebaseAuthInvalidUserException -> "El correo electrónico no está registrado."
                        else -> "Error desconocido: ${exception?.message}"
                    }
                    onComplete(false, errorMessage)
                }
            }
    }


    fun logout(onResult: (Boolean) -> Unit) {
        try {
            firebaseAuth.signOut()
            onResult(true)
        } catch (e: Exception) {
            onResult(false)
            Log.e("Logout", "Error al cerrar sesión: ${e.message}")
        }
    }
}