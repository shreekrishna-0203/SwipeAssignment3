package com.example.swipeassignment

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter

@Composable
fun ProductListScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    products: List<data1Item>,
    isLoading: Boolean
) {
    var query by remember { mutableStateOf("") }
    val filteredProducts = products.filter {
        it.product_name.contains(query, ignoreCase = true) ||
                it.product_type.contains(query, ignoreCase = true)
    }

    Column(modifier = modifier.padding(16.dp)) {
        // Search Bar
        SearchBar(query = query, onQueryChange = { query = it })

        Spacer(modifier = Modifier.height(16.dp))

        // Display list of filtered products
        LazyColumn {
            items(filteredProducts) { product ->
                ProductCard(product = product)
            }
        }
    }
}

@Composable
fun SearchBar(query: String, onQueryChange: (String) -> Unit) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "Search") },
        placeholder = { Text("Search products...") },
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
    )
}


@Composable
fun ProductCard(product: data1Item) {
    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 4.dp)
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            val imagePainter = if (product.image.isNotEmpty()) {
                rememberImagePainter(product.image)
            } else {
                painterResource(id = R.drawable.ic_launcher_foreground) // Use a default image
            }

            Image(
                painter = imagePainter,
                contentDescription = product.product_type,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = product.product_name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = product.product_type,
                fontSize = 14.sp,
                color = Color.Gray
            )
            Text(
                text = "Price: ₹${product.price}",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "Tax: ₹${product.tax}",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}
