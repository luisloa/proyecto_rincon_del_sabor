package com.proyecto.rincon_sabor.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.proyecto.rincon_sabor.R
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun loginScreen(modifier: Modifier){
    Box(modifier = Modifier
        .padding()
        .fillMaxSize()
        .background(color = Color.White),
        contentAlignment = Alignment.TopStart
    ){
        Column(modifier = Modifier
            .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Image(painter = painterResource(id = R.drawable.helado_inicio),
                contentDescription = "Compose head",
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1.24f)
                    .graphicsLayer{
                        shadowElevation = 15.dp.toPx()
                        shape = RoundedCornerShape(1)
                        clip = true
                    }
                    .background(Color.Black.copy(alpha = 0.1f))
            )
            Text(text= "Esta a un toque de ingresar al sabor", color = Color.Black,
                style = TextStyle(fontSize = 15.sp, fontStyle = FontStyle.Italic, fontFamily = FontFamily.Serif, fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .padding(vertical = 15.dp)
                    .weight(.5f)
            )
            ElevatedButton (onClick = {},
                modifier = Modifier
                ) {

                Text(text = "Ingresar",
                    style = TextStyle(color = Color.Black)
                )
            }
        }
        Text(text= "Rincón del Sabor", color = Color.White,
            style = TextStyle(fontSize = 40.sp, fontStyle = FontStyle.Italic, fontFamily = FontFamily.Cursive, fontWeight = FontWeight.Bold),
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(vertical = 70.dp)
        )
    }
}
