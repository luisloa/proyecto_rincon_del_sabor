package com.proyecto.rincon_sabor.backend

import androidx.navigation.NavController
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.firestore

fun createUser(
    email: String,
    password: String,
    nombre: String,
    apellido: String,
    navController: NavController,
    onSuccess: () -> Unit,
    onError: (String) -> Unit
) {
    val auth = FirebaseAuth.getInstance()
    val db = Firebase.firestore

    //Crear usuario en Firebase Authentication
    auth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = auth.currentUser
                val profileUpdates = UserProfileChangeRequest.Builder()
                    .setDisplayName("$nombre $apellido")
                    .build()

                //Actualizar el perfil del usuario
                user?.updateProfile(profileUpdates)
                    ?.addOnCompleteListener { updateTask ->
                        if (updateTask.isSuccessful) {
                            val uid = user.uid
                            val userData = hashMapOf(
                                "nombre" to nombre,
                                "apellido" to apellido,
                                "email" to email,
                                "fechaCreacion" to FieldValue.serverTimestamp()
                            )

                            //Guardar datos del usuario en Firestore
                            db.collection("usuarios").document(uid)
                                .set(userData)
                                .addOnSuccessListener {

                                    //Navegar a la pantalla de inicio
                                    onSuccess()
                                    navController.navigate("home")
                                }
                                .addOnFailureListener { e ->
                                    onError("Error al guardar en Firestore: ${e.localizedMessage}")
                                }

                        } else {
                            onError("Error al actualizar el perfil")
                        }
                    }
            } else {
                onError(task.exception?.localizedMessage ?: "Error desconocido")
            }
        }
}