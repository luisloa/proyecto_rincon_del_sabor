package com.proyecto.rincon_sabor.backend

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.firestore

fun actualizarPerfilUsuario(
    nuevoNombre: String,
    nuevoApellido: String,
    nuevaContrasena: String?,
    onSuccess: () -> Unit,
    onError: (String) -> Unit
) {
    val auth = FirebaseAuth.getInstance()
    val user = auth.currentUser
    val db = Firebase.firestore

    user?.let {
        val actualizaciones = hashMapOf<String, Any>(
            "nombre" to nuevoNombre,
            "apellido" to nuevoApellido
        )

        db.collection("usuarios").document(user.uid)
            .update(actualizaciones)
            .addOnSuccessListener {
                val profileUpdates = UserProfileChangeRequest.Builder()
                    .setDisplayName("$nuevoNombre $nuevoApellido")
                    .build()

                user.updateProfile(profileUpdates).addOnCompleteListener {
                    if (nuevaContrasena?.isNotBlank() == true) {
                        user.updatePassword(nuevaContrasena)
                            .addOnSuccessListener { onSuccess() }
                            .addOnFailureListener { e -> onError(e.localizedMessage ?: "Error al cambiar contraseña") }
                    } else {
                        onSuccess()
                    }
                }
            }
            .addOnFailureListener { e -> onError("Error Firestore: ${e.localizedMessage}") }
    } ?: onError("Usuario no autenticado")
}