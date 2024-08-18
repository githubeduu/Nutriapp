package com.example.nutriapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nutriapp.screen.LoginScreen
import com.example.nutriapp.screen.PrescriptionScreen
import com.example.nutriapp.screen.RecoveryPassScreen
import com.example.nutriapp.screen.RegisterUserScreen

@Composable
fun MyAppNavigation() {
    NutriAppTheme {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = Routes.LoginScreen, builder ={
            composable(Routes.LoginScreen){
                LoginScreen(navController)
            }
            composable(Routes.RecoveryPassScreen){
                RecoveryPassScreen()
            }
            composable(Routes.RegisterUserScreen){
                RegisterUserScreen(navController)
            }
            composable(Routes.PrescriptionScreen){
                PrescriptionScreen()
            }
        } )

    }

}