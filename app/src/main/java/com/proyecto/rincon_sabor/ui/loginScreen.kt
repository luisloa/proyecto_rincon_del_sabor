package com.proyecto.rincon_sabor.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import com.proyecto.rincon_sabor.R
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth


@Composable
fun login(modifier: Modifier, navController: NavHostController) {
    val cardShape = RoundedCornerShape(24.dp)
    val density = LocalDensity.current
    val elevationPx = with(density) { 12.dp.toPx() }
    val context = LocalContext.current

    var correo by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }


    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            Modifier.fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.helado_inicio),
                contentDescription = "Compose head",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
                    .graphicsLayer {
                        shadowElevation = elevationPx
                        shape = RoundedCornerShape(1)
                        clip = true
                    }
                    .background(Color.Black.copy(alpha = 0.5f))
            )

            Text(
                text = "Esta a un toque de ingresar al sabor", color = Color.Black,
                style = TextStyle(
                    fontSize = 15.sp,
                    fontStyle = FontStyle.Italic,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .padding(vertical = 15.dp)
            )

            OutlinedTextField(
                value = correo,
                onValueChange = { correo = it },
                label = { Text("Correo electrónico") },
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
                ),
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
                    .width(370.dp)
                    .height(70.dp)
                    .background(color = Color.White),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent,
                    disabledBorderColor = Color.Transparent
                ),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )

            Button(
                onClick = {
                    FirebaseAuth.getInstance()
                        .signInWithEmailAndPassword(correo, contrasena)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                navController.navigate("home") {
                                    popUpTo("registro") { inclusive = true }
                                }
                            } else {
                                errorMessage = "Usuario no registrado"
                            }
                        }
                },
                modifier = Modifier
                    .padding(top = 10.dp)
                    .width(200.dp)
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Ingresar", fontSize = 18.sp)
            }

            // Mensaje de error
            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))




            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "¿Aun no esta registrado?", color = Color.Black,
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontStyle = FontStyle.Normal,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                )
                Text(
                    text = " Registrar", color = Color.Blue,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontStyle = FontStyle.Normal,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        textDecoration = TextDecoration.Underline
                    ),
                    modifier = Modifier
                        .clickable {
                            navController.navigate("registro")
                        }
                        .padding(start = 5.dp)
                )
            }
        }
        Text(
            text = "Rincón del Sabor", color = Color.White,
            style = TextStyle(
                fontSize = 40.sp,
                fontStyle = FontStyle.Italic,
                fontFamily = FontFamily.Cursive,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .padding(vertical = 25.dp)
        )
    }
}
