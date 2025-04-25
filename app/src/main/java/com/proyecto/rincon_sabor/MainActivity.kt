package com.proyecto.rincon_sabor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.FirebaseApp
import com.proyecto.rincon_sabor.ui.login.form
import com.proyecto.rincon_sabor.ui.login.loginScreen
import com.proyecto.rincon_sabor.ui.login.registrationForm
import com.proyecto.rincon_sabor.ui.theme.Rincon_saborTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        enableEdgeToEdge()
        setContent {
            Rincon_saborTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "registro") {
                    composable("registro") {
                        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                            // Aquí puedes llamar a tu pantalla de registro
                            registrationForm(Modifier.padding(innerPadding), navController)
                        }
                    }
                        composable("home") {
                            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                                // Llamar a tu pantalla Home después del registro
                                home(Modifier.padding(innerPadding), navController)


                                //Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                                //registrationForm(Modifier.padding(innerPadding))
                                //myComplexLayou(Modifier.padding(innerPadding))
                                //loginScreen(Modifier.padding(innerPadding))
                                //form()
                            }
                        }
                    }
                }
            }
        }
    }





