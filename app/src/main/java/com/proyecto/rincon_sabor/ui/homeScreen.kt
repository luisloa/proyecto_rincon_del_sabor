package com.proyecto.rincon_sabor.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.NavigationBar
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import com.proyecto.rincon_sabor.R


@Composable
fun home(modifier: Modifier, navController: NavHostController) {
    var num_filas_productos = 0
    val cardShape = RoundedCornerShape(18.dp)

    Scaffold(
        bottomBar = {
            NavigationBar(
                modifier = Modifier.navigationBarsPadding()
                    .height(50.dp)
                    .background(color = Color.White)
                    .shadow(
                        elevation = (5.dp),
                        shape = cardShape,
                        clip = false
                    )
                    .clip(cardShape),
            ) {
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Perfil") },
                    selected = false,
                    onClick = {
                        navController.navigate("perfil")
                    },
                    label = { Text("Perfil") }
                )
            }
        }
    ) { innerPadding ->

        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            while (num_filas_productos < 6) {
                num_filas_productos += 1
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .fillMaxWidth()
                        .height(230.dp)
                        .background(color = Color.LightGray)
                ) { productosRow() }
            }
        }
    }
}

@Composable
fun productosRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
    ) {
        repeat(10) {
            producto()
        }
    }
}


@Composable
fun producto() {
    val density = LocalDensity.current
    val elevationPx = with(density) { 10.dp.toPx() }

    Box(
        modifier = Modifier
            .fillMaxHeight()
            .width(225.dp)
            .padding(3.dp)
            .background(color = Color.White)
            .aspectRatio(1f),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White)
                    .height(190.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.helado_inicio),
                    contentDescription = "Imagen del producto",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(190.dp)
                        .graphicsLayer {
                            shadowElevation = elevationPx
                            shape = RoundedCornerShape(1)
                            clip = true
                        }
                )
            }
            Text(text = "Titulo Producto", modifier = Modifier.weight(1f))
            Text(text = "Precio", modifier = Modifier.weight(1f))
        }
    }
}