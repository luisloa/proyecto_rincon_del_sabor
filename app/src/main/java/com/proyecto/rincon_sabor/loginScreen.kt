package com.proyecto.rincon_sabor

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import com.proyecto.rincon_sabor.R
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun loginScreen(){
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
                    .padding()
                    .fillMaxWidth()
                    .height(617.dp)
                    .graphicsLayer{
                        shadowElevation = 10.dp.toPx()
                        shape = RoundedCornerShape(1)
                        clip = true
                    }
                    .background(Color.Black.copy(alpha = 0.3f))
            )
            Text(text= "Esta a un toque de ingresar al sabor", color = Color.Black,
                style = TextStyle(fontSize = 15.sp, fontStyle = FontStyle.Italic, fontFamily = FontFamily.Serif, fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .padding(vertical = 15.dp)
            )
            ElevatedButton (onClick = {}) {

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
