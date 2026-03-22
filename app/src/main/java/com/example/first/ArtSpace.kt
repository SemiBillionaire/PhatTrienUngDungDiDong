package com.example.first

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.first.ui.theme.FirstTheme

@Composable
fun ArtSpaceApp() {
    var currentArt by remember { mutableStateOf(1) }

    val imageResource = when (currentArt) {
        1 -> R.drawable.art1
        2 -> R.drawable.art2
        else -> R.drawable.art3
    }

    val title = "Ảnh $currentArt"
    val artist = "Hoàng Minh Quân"
    val year = "(2024)"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Phần hiển thị ảnh
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(bottom = 32.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = imageResource),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Fit
                )
            }
        }

        // Phần miêu tả
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFECEFF1))
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = title,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Light
                )
                Row {
                    Text(
                        text = artist,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = year,
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
            }
        }

        // Phần nút bấm điều hướng
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { 
                    if (currentArt > 1) currentArt-- else currentArt = 3 
                },
                modifier = Modifier.weight(1f).padding(end = 8.dp)
            ) {
                Text("Previous")
            }
            Button(
                onClick = { 
                    if (currentArt < 3) currentArt++ else currentArt = 1 
                },
                modifier = Modifier.weight(1f).padding(start = 8.dp)
            ) {
                Text("Next")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    FirstTheme {
        ArtSpaceApp()
    }
}
