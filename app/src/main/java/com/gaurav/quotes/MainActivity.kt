package com.gaurav.quotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gaurav.quotes.ui.theme.TestTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestTheme {
                QuoteScreen()
            }
        }
    }
}

@Composable
fun QuoteScreen() {
    val quotes = listOf(
        "Success is not final. Failure is not fatal.",
        "Believe in yourself and all that you are.",
        "Hard work beats talent.",
        "Your only limit is your mind.",
        "Don’t wait. The time will never be just right."
    )

    var currentQuote by remember { mutableStateOf(quotes.random()) }
    var backgroundColor by remember { mutableStateOf(randomColor()) }

    val textColor = getContrastColor(backgroundColor)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = currentQuote,
            color = textColor,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            currentQuote = quotes.random()
            backgroundColor = randomColor()
        }) {
            Text("Show Another Quote")
        }
    }
}


fun randomColor(): Color {
    val red = Random.nextInt(256)
    val green = Random.nextInt(256)
    val blue = Random.nextInt(256)
    return Color(red, green, blue)
}

fun getContrastColor(bgColor: Color): Color {
    val r = bgColor.red * 255
    val g = bgColor.green * 255
    val b = bgColor.blue * 255
    val brightness = (r * 0.299 + g * 0.587 + b * 0.114)
    return if (brightness < 186) Color.White else Color.Black
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TestTheme {
        QuoteScreen()
    }
}