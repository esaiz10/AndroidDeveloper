package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import com.example.artspace.ui.theme.ArtSpaceTheme
import com.example.artspace.ArtSpaceApp as ArtSpaceApp1

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ArtSpaceApp1()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp() {
    val images = listOf(
        R.drawable.art_1,
        R.drawable.art_2,
    )

    var currentIndex by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Artwork Wall
        Image(
            painter = painterResource(id = images[currentIndex]),
            contentDescription = "Artwork",
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f)
                .border(2.dp, Color.Gray)
                .padding(8.dp)
        )

        // Artwork Descriptor
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text(
                text = "Artwork ${currentIndex + 1}",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Artist ${currentIndex + 1}",
                fontSize = 16.sp,
                color = Color.Gray
            )
        }

        // Navigation Buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = {
                if (currentIndex > 0) currentIndex--
            }) {
                Text("Previous")
            }
            Button(onClick = {
                if (currentIndex < images.size - 1) currentIndex++
            }) {
                Text("Next")
            }
        }
    }
}