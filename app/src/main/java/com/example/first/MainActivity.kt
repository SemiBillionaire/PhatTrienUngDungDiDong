package com.example.first

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.first.ui.AmphibiansApp
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
                    
                    // 1. Máy tính đơn giản
                    // CalculatorScreen()
                    
                    // 2. Todo List
                    // TodoApp()
                    
                    // 3. Business Card
                    // BusinessCardApp()
                    
                    // 4. Dice Roller
                    // DiceRollerApp()

                    // 5. Lemonade
                    // LemonadeApp()

                    // 6. Art Space
                    // ArtSpaceApp()

                    // 7. Heroes Screen
                    // SuperheroesApp()

                    // 8. LunchTray
                    // LunchTrayApp()

                    // 9. Amphibians
                    // AmphibiansApp()
                }
            }
        }
    }
}
