package com.proyecto.rincon_sabor.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.proyecto.rincon_sabor.backend.actualizarPerfilUsuario
import com.proyecto.rincon_sabor.backend.eliminarCuentaUsuario
import com.proyecto.rincon_sabor.backend.obtenerDatosUsuario

@Composable
fun perfilScreen(modifier: Modifier, navController: NavHostController) {
    val cardShape = RoundedCornerShape(24.dp)

    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var nuevaContrasena by remember { mutableStateOf("") }
    var contrasenaActual by remember { mutableStateOf("") }
    var contrasenaConfirmacion by remember { mutableStateOf("") }
    var mostrarDialogoEliminar by remember { mutableStateOf(false) }
    var mostrarDialogoActualizar by remember { mutableStateOf(false) }
    var editando by remember { mutableStateOf(false) }

    var mensajeError by remember { mutableStateOf<String?>(null) }
    var mensajeExito by remember { mutableStateOf<String?>(null) }
    var mostrarDialogoErrorAutenticacion by remember { mutableStateOf(false) }

    // Carga datos del usuario
    LaunchedEffect(Unit) {
        obtenerDatosUsuario(
            onSuccess = { data ->
                nombre = data["nombre"] as? String ?: ""
                apellido = data["apellido"] as? String ?: ""
                email = data["email"] as? String ?: ""
            },
            onError = {
                mensajeError = it
            }
        )
    }

    // Formulario
    Column(modifier = modifier.padding(16.dp)) {
        mensajeError?.let { Text(it, color = Color.Red) }
        mensajeExito?.let { Text(it, color = Color.Blue) }


        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            enabled = editando,
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
            enabled = editando,
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

        OutlinedTextField(
            value = nuevaContrasena,
            onValueChange = { nuevaContrasena = it },
            label = { Text("Nueva contraseña") },
            enabled = editando,
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
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (!editando) {
            Button(onClick = { editando = true }, modifier = Modifier.fillMaxWidth()) {
                Icon(Icons.Default.Edit, contentDescription = "Editar")
                Spacer(Modifier.width(8.dp))
                Text("Editar perfil")
            }
        } else {
            Button(
                onClick = { mostrarDialogoActualizar = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.Edit, contentDescription = "Guardar")
                Spacer(Modifier.width(8.dp))
                Text("Guardar cambios")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { mostrarDialogoEliminar = true },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(Icons.Default.Delete, contentDescription = "Eliminar")
            Spacer(Modifier.width(8.dp))
            Text("Eliminar cuenta")
        }
    }

    if (mostrarDialogoActualizar) {
        AlertDialog(
            onDismissRequest = { mostrarDialogoActualizar = false },
            title = { Text("Confirmar cambios") },
            text = {
                Column {
                    Text("Ingresa tu contraseña actual para confirmar la actualización.")
                    OutlinedTextField(
                        value = contrasenaActual,
                        onValueChange = { contrasenaActual = it },
                        label = { Text("Contraseña actual") },
                        visualTransformation = PasswordVisualTransformation()
                    )
                }

            },

            confirmButton = {
                TextButton(onClick = {
                    val user = FirebaseAuth.getInstance().currentUser
                    val credential =
                        EmailAuthProvider.getCredential(email, contrasenaActual)
                    user?.reauthenticate(credential)
                        ?.addOnSuccessListener {
                            actualizarPerfilUsuario(
                                nuevoNombre = nombre,
                                nuevoApellido = apellido,
                                nuevaContrasena = nuevaContrasena.takeIf { it.isNotBlank() },
                                onSuccess = {
                                    mensajeExito = "Perfil actualizado"
                                    editando = false
                                },
                                onError = { mensajeError = it }
                            )
                        }
                        ?.addOnFailureListener {
                            mensajeError = "Contraseña incorrecta. Intenta de nuevo."
                            mostrarDialogoErrorAutenticacion = true
                        }
                    mostrarDialogoActualizar = false
                }) {
                    Text("Actualizar")
                }
            },
            dismissButton = {
                TextButton(onClick = { mostrarDialogoActualizar = false }) {
                    Text("Cancelar")
                }
            }
        )
    }
    if (mostrarDialogoEliminar) {
        AlertDialog(
            onDismissRequest = { mostrarDialogoEliminar = false },
            title = { Text("¿Estás seguro?") },
            text = {
                Column {
                    Text("Esta acción eliminará tu cuenta permanentemente.")
                    OutlinedTextField(
                        value = contrasenaConfirmacion,
                        onValueChange = { contrasenaConfirmacion = it },
                        label = { Text("Confirma tu contraseña") },
                        visualTransformation = PasswordVisualTransformation()
                    )
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    eliminarCuentaUsuario(
                        password = contrasenaConfirmacion,
                        onSuccess = {
                            navController.navigate("login") {
                                popUpTo("profile") { inclusive = true }
                            }
                        },
                        onError = { mensajeError = it }
                    )
                    mostrarDialogoEliminar = false
                }) {
                    Text("Eliminar", color = Color.Red)
                }
            },
            dismissButton = {
                TextButton(onClick = { mostrarDialogoEliminar = false }) {
                    Text("Cancelar")
                }
            }
        )
    }
}



