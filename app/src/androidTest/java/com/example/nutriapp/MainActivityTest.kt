package com.example.nutriapp

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.nutriapp.ui.screen.LoginScreen
import com.google.firebase.FirebaseApp
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        FirebaseApp.initializeApp(context)

        composeTestRule.setContent {
            NutriAppTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Routes.LoginScreen) {
                    composable(Routes.LoginScreen) {
                        LoginScreen(navController)
                    }
                }
            }
        }
    }

    @Test
    fun testButtonClick() {
        composeTestRule.onNodeWithText("Login").performClick()
    }
}