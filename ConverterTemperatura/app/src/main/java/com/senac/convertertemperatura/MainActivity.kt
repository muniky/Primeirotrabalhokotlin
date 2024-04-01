package com.senac.convertertemperatura

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.senac.convertertemperatura.ui.theme.ConverterTemperaturaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConverterTemperaturaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ConverterTemperatura()
                }
            }
        }
    }
}

@Composable
fun ConverterTemperatura() {
    var temperatureInCelsius = remember {
        mutableStateOf(value = "")
    }
    var temperatureInFahrenheit = remember {
        mutableStateOf(value = "")
    }
    var showResult = remember {
        mutableStateOf(value = false)
    }

    Surface(modifier = Modifier.fillMaxSize(), color = Color.Gray) {
        Column(modifier = Modifier.padding(16.dp)
            .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Celsius",
                color = Color.White,
                textAlign = TextAlign.Center
            )
            OutlinedTextField(
                value = temperatureInCelsius.value,
                onValueChange = { value ->
                    temperatureInCelsius.value = value
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                textStyle = TextStyle(color = Color.White),
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = "Fahrenheit",
                color = Color.White,
                textAlign = TextAlign.Center
            )
            OutlinedTextField(
                value = temperatureInFahrenheit.value,
                onValueChange = { value ->
                    temperatureInFahrenheit.value = value
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                textStyle = TextStyle(color = Color.White),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (temperatureInCelsius.value.isNotEmpty()) {
                        temperatureInFahrenheit.value = ((temperatureInCelsius.value.toDoubleOrNull() ?: 0.0) * 1.8 + 32).toString()
                    } else if (temperatureInFahrenheit.value.isNotEmpty()) {
                        temperatureInCelsius.value = (((temperatureInFahrenheit.value.toDoubleOrNull() ?: 0.0) - 32) / 1.8).toString()
                    }
                    showResult.value = true
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Converter")
            }

            if (showResult.value) {
                Spacer(modifier = Modifier.height(16.dp))
                Text("Celsius: ${temperatureInCelsius.value}", color = Color.White)
                Text("Fahrenheit: ${temperatureInFahrenheit.value}", color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ConverterTemperaturaTheme {
        ConverterTemperatura()
    }
}