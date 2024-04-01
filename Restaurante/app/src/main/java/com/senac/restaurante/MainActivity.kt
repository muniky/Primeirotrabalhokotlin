package com.senac.restaurante

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.senac.restaurante.ui.theme.RestauranteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RestauranteTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Restaurante()
                }
            }
        }
    }
}

@Composable
fun Restaurante() {
    var Valortotal = remember { mutableStateOf("") }
    var Personalizado = remember { mutableStateOf(18f) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Cálculo de Gorjetas", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = Valortotal.value,
                onValueChange = {
                    Valortotal.value = it
                },
                label = { Text("Valor Ganho") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text("Personalizado %: ${Personalizado.value.toInt()}%")
            Slider(
                value = Personalizado.value,
                onValueChange = { Personalizado.value = it },
                valueRange = 0f..30f
            )
            Spacer(modifier = Modifier.height(16.dp))

            val Gorjeta = Valortotal.value.toDoubleOrNull() ?: 0.0
            val Gorjeta15 = Gorjeta * 0.15
            val gorjetaPersonalizada = Gorjeta * (Personalizado.value / 100)

            Text("Gorjeta Personalizada: $${"%.2f".format(gorjetaPersonalizada)}")
            Text("Total da Gorjeta Personalizada: $${"%.2f".format(Gorjeta + gorjetaPersonalizada)}")
            Text("Gorjeta 15%: $${"%.2f".format(Gorjeta15)}")
            Text("Total 15%: $${"%.2f".format(Gorjeta + Gorjeta15)}")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RestauranteTheme {
        Restaurante()
    }
}