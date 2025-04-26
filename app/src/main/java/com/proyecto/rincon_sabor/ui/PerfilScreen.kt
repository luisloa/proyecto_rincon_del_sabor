package com.proyecto.rincon_sabor.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.proyecto.rincon_sabor.obtenerDatosUsuario

@Composable
fun perfilScreen(modifier: Modifier, navController: NavHostController) {
    val cardShape = RoundedCornerShape(24.dp)

    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }

    // Cargar datos del usuario al abrir la pantalla
    LaunchedEffect(Unit) {
        obtenerDatosUsuario(
            onSuccess = { data ->
                nombre = data["nombre"] as? String ?: ""
                apellido = data["apellido"] as? String ?: ""
                email = data["email"] as? String ?: ""
            },
            onError = {
                error = it
            }
        )
    }

    // Aquí puedes construir el formulario
    Column(modifier = modifier.padding(16.dp)) {
        if (error != null) {
            Text("Error: $error", color = Color.Red)
        }

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier
                .padding(bottom = 5.dp)
                .shadow(
                    elevation = (3.dp),
                    shape = cardShape,
                    clip = false
                )
                .clip(cardShape)
                .width(370.dp)
                .height(70.dp)
                .background(color = Color.White),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = Color.Transparent,
                disabledBorderColor = Color.Transparent
            )
        )

        OutlinedTextField(
            value = apellido,
            onValueChange = { apellido = it },
            label = { Text("Apellido") },
            modifier = Modifier
                .padding(bottom = 5.dp)
                .shadow(
                    elevation = (3.dp),
                    shape = cardShape,
                    clip = false
                )
                .clip(cardShape)
                .width(370.dp)
                .height(70.dp)
                .background(color = Color.White),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = Color.Transparent,
                disabledBorderColor = Color.Transparent
            )
        )

        OutlinedTextField(
            value = email,
            onValueChange = {},
            enabled = false,
            label = { Text("Email") },
            modifier = Modifier
                .padding(bottom = 5.dp)
                .shadow(
                    elevation = (3.dp),
                    shape = cardShape,
                    clip = false
                )
                .clip(cardShape)
                .width(370.dp)
                .height(70.dp)
                .background(color = Color.White),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = Color.Transparent,
                disabledBorderColor = Color.Transparent
            )
        )

        // Aquí puedes poner el botón para actualizar y eliminar cuenta
    }
}