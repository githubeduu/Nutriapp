package com.example.nutriapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nutriapp.ui.screen.AccountScreen
import com.example.nutriapp.ui.screen.HomeScreen
import com.example.nutriapp.ui.screen.LoginScreen
import com.example.nutriapp.ui.screen.NutritionalRecipeScreen
import com.example.nutriapp.ui.screen.RecoveryPassScreen
import com.example.nutriapp.ui.screen.RegisterUserScreen


@Composable
fun MyAppNavigation() {
    NutriAppTheme {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = Routes.LoginScreen, builder ={
            composable(Routes.LoginScreen){
                LoginScreen(navController)
            }
            composable(Routes.RecoveryPassScreen){
                RecoveryPassScreen(navController)
            }
            composable(Routes.RegisterUserScreen){
                RegisterUserScreen(navController)
            }
            composable(Routes.NutritionalRecipeScreen){
                NutritionalRecipeScreen()
            }
            composable(Routes.HomeScreen){
                HomeScreen(navController)
            }
            composable(Routes.AccountScreen){
                AccountScreen(navController)
            }
        } )

    }

}