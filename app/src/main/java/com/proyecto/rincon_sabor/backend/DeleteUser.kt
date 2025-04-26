package com.proyecto.rincon_sabor.backend

import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

fun eliminarCuentaUsuario(
    password: String,
    onSuccess: () -> Unit,
    onError: (String) -> Unit
) {
    val user = FirebaseAuth.getInstance().currentUser
    val email = user?.email

    if (user != null && email != null) {
        val credential = EmailAuthProvider.getCredential(email, password)

        user.reauthenticate(credential)
            .addOnSuccessListener {
                val uid = user.uid

                // Primero elimina el documento en Firestore
                FirebaseFirestore.getInstance()
                    .collection("usuarios") // Ajusta esto al nombre real de tu colección
                    .document(uid)
                    .delete()
                    .addOnSuccessListener {
                        // Después elimina el usuario autenticado
                        user.delete()
                            .addOnSuccessListener {
                                onSuccess()
                            }
                            .addOnFailureListener { e ->
                                onError("Error al eliminar la cuenta de autenticación: ${e.message}")
                            }
                    }
                    .addOnFailureListener { e ->
                        onError("Error al eliminar el documento en Firestore: ${e.message}")
                    }
            }
            .addOnFailureListener {
                onError("Error al reautenticar: ${it.message}")
            }
    } else {
        onError("Usuario no autenticado.")
    }
}