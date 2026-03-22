package com.example.first

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.first.ui.theme.FirstTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FirstTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // --- CHỈ CẦN THAY ĐỔI DÒNG DƯỚI ĐÂY ---
                    // CalculatorScreen() // Để chạy Máy tính
                    // TodoApp()          // Để chạy Todo List
                    BusinessCardApp()
                }
            }
        }
    }
}
