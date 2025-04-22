package com.proyecto.rincon_sabor.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun ejercicioUno(modifier: Modifier) {
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
        ) { productosRow()}

        Box(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
                .height(250.dp)
                .background(color = Color.LightGray)
        ) { productosRow()}
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
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .width(200.dp)
            .padding(5.dp)
            .background(color = Color.White),
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
            ) {}
            Text(text = "Titulo Producto", modifier = Modifier.weight(1f))
            Text(text = "Precio", modifier = Modifier.weight(1f))
        }
    }
}