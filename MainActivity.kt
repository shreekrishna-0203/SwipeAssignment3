package com.example.swipeassignment

import ViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.swipeassignment.ui.theme.SwipeAssignmentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SwipeAssignmentTheme {
                val navController = rememberNavController()
                val productViewModel: ViewModel = viewModel() // Use viewModel() to get the ViewModel

                Scaffold(
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = { navController.navigate("add_product") },
                            content = { Text("+") }
                        )
                    }
                ) { paddingValues ->
                    NavGraph(navController, modifier = Modifier.padding(paddingValues), productViewModel = productViewModel)
                }
            }
        }
    }
}

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    productViewModel: ViewModel // Pass ViewModel here
) {
    NavHost(navController, startDestination = "product_list", modifier = modifier) {
        composable("product_list") {
            // Pass the list of products and the loading state to the ProductListScreen
            ProductListScreen(
                navController = navController,
                products = productViewModel.products,
                isLoading = productViewModel.isLoading // Pass isLoading state
            )
        }
        composable("add_product") {
            AddProductScreen(
                onSubmit = { product ->
                    productViewModel.addProduct(product) // Simply call addProduct without a callback
                    // Navigate back to the product list screen after adding the product
                    navController.popBackStack()
                }
            )
        }
    }
}


