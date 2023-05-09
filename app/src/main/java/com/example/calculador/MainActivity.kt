package com.example.calculador

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculador.ui.theme.CalculadorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculadorTheme {

                  Appcalculadora()
                }
            }
        }
    }


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun Appcalculadora(){

    var valorEntrada by remember { mutableStateOf("") }


    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

    }


    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Calcular Gorjeta" ,
            modifier = Modifier.padding( top =25.dp),
            fontSize = 25.sp,
            textAlign = TextAlign.Center,
            color = Color.Black

     )
        TextField(
            value = valorEntrada ,
            label = {
                   Text(
                       text ="Valor de entrada" )
            },
            onValueChange = {valorEntrada= it },
            modifier = Modifier
                .padding( top =25.dp )

        )

        Text(
            text ="valor da Gorjeta:" ,
        modifier = Modifier.padding( top =25.dp),
        fontSize = 25.sp,
        textAlign = TextAlign.Center,
        color = Color.Black)
    }
}