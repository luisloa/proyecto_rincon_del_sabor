package com.proyecto.rincon_sabor

@Composable
fun perfilScreen(modifier: Modifier, navController: NavHostController) {
    var nombre by remember { mutableStateOf("Juan") }
    var apellido by remember { mutableStateOf("Pérez") }
    var correo by remember { mutableStateOf("juan.perez@correo.com") }
    var contrasena by remember { mutableStateOf("123456") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Mi cuenta", style = MaterialTheme.typography.titleLarge)

        OutlinedTextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre") })
        OutlinedTextField(value = apellido, onValueChange = { apellido = it }, label = { Text("Apellido") })
        OutlinedTextField(value = correo, onValueChange = { correo = it }, label = { Text("Correo") }, enabled = false)
        OutlinedTextField(value = contrasena, onValueChange = { contrasena = it }, label = { Text("Contraseña") }, visualTransformation = PasswordVisualTransformation())

        Button(onClick = { /* Aquí guardarías los cambios */ }) {
            Text("Guardar Cambios")
        }
    }
}