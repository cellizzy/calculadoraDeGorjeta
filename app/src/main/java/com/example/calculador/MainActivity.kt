package com.example.calculador

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculador.ui.theme.CalculadorTheme
import java.nio.file.WatchEvent
import java.text.NumberFormat

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
    var  gorjeta by remember { mutableStateOf(0.0) }
    var percentagemGorjeta by remember { mutableStateOf("") }
    var arredondar by remember {
        mutableStateOf(false) }

    val focusManager= LocalFocusManager.current

    gorjeta= CalcularGorjeta(valorEntrada,percentagemGorjeta, arredondar)

Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.LightGray
    ) {



        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally

        ){
            Text(
                text = "Calcular Gorjeta" ,
                modifier = Modifier.padding( top =25.dp),
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                color = Color.Black,
                fontWeight = FontWeight.Bold

         )

            CampoTextoEditavel(
                value = valorEntrada,
                label ="valor de entrada" ,
                onValueChange = {valorEntrada=it},
                imeAction = ImeAction.Next,
                keyboardActions = KeyboardActions(
                    onNext = {focusManager.moveFocus(FocusDirection.Down)}
                )
            )
            CampoTextoEditavel(
                value = percentagemGorjeta,
                label ="% Gorjeta" ,
                onValueChange = {percentagemGorjeta=it},
                imeAction = ImeAction.Done,
                keyboardActions = KeyboardActions(
                    onNext = {focusManager.clearFocus()}
                )
            )
            Row(
               modifier = Modifier
                   .fillMaxWidth()
                   .size(48.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text ="Arredondar",
                    fontSize = 20.sp
                )
                Switch(checked =arredondar,
                    onCheckedChange ={arredondar=it},
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.End)
                )



            }
                


            Text(
                text ="valor da Gorjeta : ${NumberFormat.getCurrencyInstance().format(gorjeta)}",
                modifier = Modifier.padding( top =25.dp),
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }

    }
}
fun CalcularGorjeta(
    valorEntrada:String,
    percentagemGorjeta:String,
    arredondar:Boolean
):Double{

    var gorjeta=(valorEntrada.toDoubleOrNull()?:0.0)*(percentagemGorjeta.toDoubleOrNull()?:0.0)/100

    if (arredondar==true)
    gorjeta= kotlin.math.ceil(gorjeta)

        return gorjeta
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CampoTextoEditavel(
    value: String,
    label:String,
    onValueChange:(String)->Unit,
    imeAction: ImeAction,
    keyboardActions: KeyboardActions
){
    TextField(
        value = value,
        label={ Text(text = label)},
        onValueChange = onValueChange,
        modifier = Modifier.padding(top = 25.dp),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = imeAction
        ),
        keyboardActions = keyboardActions
    )
}
