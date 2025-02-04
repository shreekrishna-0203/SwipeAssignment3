package com.example.swipeassignment

import ViewModel
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.swipeassignment.ui.theme.SwipeAssignmentTheme

class AddProductActivity : ComponentActivity() {

    private val viewModel = ViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SwipeAssignmentTheme {
                AddProductScreen(
                    onSubmit = { product ->
                        viewModel.addProduct(product)
                        Toast.makeText(this, "Product Added!", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                )
            }
        }
    }
}
