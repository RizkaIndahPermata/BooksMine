package com.rizkaindah0043.booksmine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.rizkaindah0043.booksmine.ui.screen.MainScreen
import com.rizkaindah0043.booksmine.ui.theme.BooksMineTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BooksMineTheme {
                MainScreen()
            }
        }
    }
}

