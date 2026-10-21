package com.example.lab3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab3.ui.theme.Lab3Theme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab3Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    JokeScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun JokeScreen(modifier: Modifier = Modifier) {
    val jokes = stringArrayResource(R.array.jokes)
    var currentJokeIndex by rememberSaveable { mutableIntStateOf(0) }
    var jokeNumber by rememberSaveable { mutableIntStateOf(1) }

    val currentJokeText = if (jokes.isNotEmpty()) {
        jokes[currentJokeIndex % jokes.size]
    } else {
        ""
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 16.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFFF3F0F8))
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = currentJokeText,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                lineHeight = 26.sp
            )
        }

        Button(
            onClick = {
                if (jokes.isNotEmpty()) {
                    currentJokeIndex = Random.nextInt(jokes.size)
                    jokeNumber++
                }
            },
            shape = CircleShape,
            modifier = Modifier.size(140.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF00796B)
            )
        ) {
            Text(
                text = stringResource(R.string.button_show_joke),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
        }

        Text(
            text = stringResource(
                R.string.greeting,
                stringResource(R.string.guest),
                jokeNumber
            ),
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 16.dp)
        )
    }
}