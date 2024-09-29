package com.example.nutriapp

import com.example.nutriapp.auth.FirebaseAuthService
import com.example.nutriapp.data.repository.UserRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.whenever
import org.mockito.kotlin.mock

class UserRepositoryTest {
    private lateinit var authService: FirebaseAuthService // Asumiendo que tienes un FirebaseAuthService para simular.

    @Before
    fun setUp() {
        // Si necesitas inicializar FirebaseAuthService o algún otro servicio, hazlo aquí
        authService = mock()
    }

    @Test
    fun `test fetching user data`() = runBlocking {
        var nombre: String? = null
        var telefono: String? = null
        var correo: String? = null
        var error: String? = null

        UserRepository.fetchUserData { n, t, c, e ->
            nombre = n
            telefono = t
            correo = c
            error = e
        }

        assertEquals("John Doe", nombre)
        assertEquals("123456789", telefono)
        assertEquals("john@example.com", correo)
        assertNull(error)
    }

    @Test
    fun `test register user`() = runBlocking {
        val testEmail = "test@example.com"
        val testPassword = "password123"
        val testNombre = "Test User"
        val testTelefono = "1234567890"

        // Capturamos el argumento de onComplete
        val captor = argumentCaptor<(Boolean, String?) -> Unit>()

        // Simulando el resultado de registro exitoso
        whenever(authService.register(testEmail, testPassword, testNombre, testTelefono, captor.capture()))
            .thenAnswer { invocation ->
                // Simulamos que el registro es exitoso llamando al callback capturado
                captor.firstValue(true, null)
            }

        var success: Boolean? = null
        var errorMessage: String? = null

        // Ejecutar el registro
        authService.register(testEmail, testPassword, testNombre, testTelefono) { isSuccess, error ->
            success = isSuccess
            errorMessage = error
        }

        assertTrue(success == true)
        assertNull(errorMessage)
    }
}