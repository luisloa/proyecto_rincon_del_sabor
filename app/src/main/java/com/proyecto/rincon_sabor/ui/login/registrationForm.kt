package com.proyecto.rincon_sabor.ui.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.proyecto.rincon_sabor.R
import com.proyecto.rincon_sabor.createUser


@Composable
fun registrationForm(modifier: Modifier, navController: NavHostController) {
    val cardShape = RoundedCornerShape(24.dp)
    val density = LocalDensity.current
    val elevationPx = with(density) { 15.dp.toPx() }

    var nombre by remember { mutableStateOf("") }  // Variable que delega
    var apellido by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var confirmarContrasena by remember { mutableStateOf("") }
    var mensajeError by remember { mutableStateOf("") }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White),
        contentAlignment = Alignment.TopCenter
    ) {
        Image(
            painter = painterResource(id = R.drawable.helado_inicio),
            contentDescription = "Compose head",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .height(500.dp)
                .graphicsLayer {
                    shadowElevation = elevationPx
                    shape = RoundedCornerShape(1)
                    clip = true
                }
                .background(Color.Black.copy(alpha = 0.1f))
        )

        Text(
            text = "Rincón del Sabor", color = Color.White,
            style = TextStyle(
                fontSize = 40.sp,
                fontStyle = FontStyle.Italic,
                fontFamily = FontFamily.Cursive,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(vertical = 40.dp)
        )
    }

    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Box(
            modifier = Modifier
                .padding(bottom = 30.dp)
                .shadow(
                    elevation = (12.dp),
                    shape = cardShape,
                    clip = false
                )
                .clip(cardShape)
                .background(color = Color.White)
                .width(350.dp)
                .height(450.dp)
                .verticalScroll(rememberScrollState())
        ) {form(navController)}
    }
}


@Composable
fun form(navController: NavHostController) {
    val context = LocalContext.current
    var usuarioCreadoExitosamente by remember { mutableStateOf(false) }


    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var confirmarContrasena by remember { mutableStateOf("") }
    var mensajeError by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(450.dp)
            .padding(15.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val cardShape = RoundedCornerShape(24.dp)
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier
                .padding(bottom = 3.dp)
                .shadow(
                    elevation = (4.dp),
                    shape = cardShape,
                    clip = false
                )
                .clip(cardShape)
                .fillMaxWidth()
                .height(60.dp)
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
                .padding(bottom = 3.dp)
                .shadow(
                    elevation = (4.dp),
                    shape = cardShape,
                    clip = false
                )
                .clip(cardShape)
                .fillMaxWidth()
                .height(60.dp)
                .background(color = Color.White),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = Color.Transparent,
                disabledBorderColor = Color.Transparent
            )
        )

        OutlinedTextField(
            value = correo,
            onValueChange = { correo = it },
            label = { Text("Correo electrónico") },
            modifier = Modifier
                .padding(bottom = 3.dp)
                .shadow(
                    elevation = (4.dp),
                    shape = cardShape,
                    clip = false
                )
                .clip(cardShape)
                .fillMaxWidth()
                .height(60.dp)
                .background(color = Color.White),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = Color.Transparent,
                disabledBorderColor = Color.Transparent),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        OutlinedTextField(
            value = contrasena,
            onValueChange = { contrasena = it },
            label = { Text("Contraseña") },
            modifier = Modifier
                .padding(bottom = 3.dp)
                .shadow(
                    elevation = (4.dp),
                    shape = cardShape,
                    clip = false
                )
                .clip(cardShape)
                .fillMaxWidth()
                .height(60.dp)
                .background(color = Color.White),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = Color.Transparent,
                disabledBorderColor = Color.Transparent),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        OutlinedTextField(
            value = confirmarContrasena,
            onValueChange = { confirmarContrasena = it },
            label = { Text("Confirmar Contraseña") },
            modifier = Modifier
                .padding(bottom = 3.dp)
                .shadow(
                    elevation = (4.dp),
                    shape = cardShape,
                    clip = false
                )
                .clip(cardShape)
                .fillMaxWidth()
                .height(60.dp)
                .background(color = Color.White),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = Color.Transparent,
                disabledBorderColor = Color.Transparent),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        if (mensajeError.isNotEmpty()) {
            Text(
                text = mensajeError,
                color = Color.Red,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Button(
            onClick = {
                if (contrasena != confirmarContrasena) {
                    mensajeError = "Las contraseñas no coinciden"
                } else if (nombre.isBlank() || apellido.isBlank() || correo.isBlank() || contrasena.isBlank()) {
                    mensajeError = "Todos los campos son obligatorios"
                } else {
                    mensajeError = ""
                    // Aquí podrías manejar el registro (guardar en base de datos, llamar API, etc.)
                    createUser(
                        email = correo,
                        password = contrasena,
                        nombre = nombre,
                        apellido = apellido,
                        navController = navController,
                        onSuccess = {
                            usuarioCreadoExitosamente = true
                            navController.navigate("home")
                        },
                        onError = { errorMsg ->
                            mensajeError = errorMsg
                        }
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text("Registrar")
        }
        if (usuarioCreadoExitosamente) {
            LaunchedEffect(usuarioCreadoExitosamente) {
                Toast.makeText(context, "Usuario creado con éxito", Toast.LENGTH_LONG).show()
                usuarioCreadoExitosamente = false
            }
        }
    }
}





