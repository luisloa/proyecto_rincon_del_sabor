package com.proyecto.rincon_sabor

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


@Composable
fun home(modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
                .height(250.dp)
                .background(color = Color.LightGray)
        ) { productosRow() }

        Box(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
                .height(250.dp)
                .background(color = Color.LightGray)
        ) { productosRow() }

        Box(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
                .height(250.dp)
                .background(color = Color.LightGray)
        ) { productosRow() }

        Box(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
                .height(250.dp)
                .background(color = Color.LightGray)
        ) { productosRow() }
    }
}


@Composable
fun productosRow(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
    ) {
        repeat(10){
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
            .width(200.dp)
            .padding(5.dp)
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
                    .weight(4f)
            ) {
                Image(painter = painterResource(id = R.drawable.helado_inicio),
                    contentDescription = "Imagen del producto",
                    modifier = Modifier
                        .fillMaxWidth()
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