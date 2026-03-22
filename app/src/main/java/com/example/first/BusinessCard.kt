package com.example.first

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.first.ui.theme.FirstTheme

@Composable
fun BusinessCardApp() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFD2E8D4)), // Màu nền xanh nhạt giống mẫu Android
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))
        
        // Phần trên: Ảnh, Tên, Chức danh
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .background(Color(0xFF073042))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.duck), // Cần file duck.jpg trong drawable
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Text(
                text = "Hoàng Minh Quân",
                fontSize = 40.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = "Sinh viên",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF006D3B)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // Phần dưới: Thông tin liên hệ
        Column(
            modifier = Modifier.padding(bottom = 48.dp)
        ) {
            ContactRow(
                icon = Icons.Default.Phone,
                text = "0968803328"
            )
            ContactRow(
                icon = Icons.Default.Share, // Icon dùng cho mạng xã hội
                text = "@semibillionaire98"
            )
            ContactRow(
                icon = Icons.Default.Email,
                text = "22028019@vnu.edu.vn"
            )
        }
    }
}

@Composable
fun ContactRow(icon: ImageVector, text: String) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .width(250.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color(0xFF006D3B),
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            fontSize = 16.sp,
            color = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BusinessCardPreview() {
    FirstTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            BusinessCardApp()
        }
    }
}
