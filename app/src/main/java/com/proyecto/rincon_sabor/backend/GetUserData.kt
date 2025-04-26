package com.proyecto.rincon_sabor.backend

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

fun obtenerDatosUsuario(
    onSuccess: (Map<String, Any>) -> Unit,
    onError: (String) -> Unit
) {
    val user = FirebaseAuth.getInstance().currentUser
    val db = Firebase.firestore

    user?.let {
        db.collection("usuarios").document(user.uid).get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    onSuccess(document.data ?: emptyMap())
                } else {
                    onError("El documento no existe.")
                }
            }
            .addOnFailureListener { e ->
                onError("Error al obtener datos: ${e.localizedMessage}")
            }
    } ?: onError("Usuario no autenticado")
}