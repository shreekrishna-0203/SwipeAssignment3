import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swipeassignment.Repository1
import com.example.swipeassignment.data1Item
import kotlinx.coroutines.launch

class ViewModel: ViewModel() {

    private val repository1 = Repository1()

    var products by mutableStateOf<List<data1Item>>(emptyList())
        private set

    var isLoading by mutableStateOf(false) // Add loading state
        private set

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        isLoading = true // Set loading state to true
        viewModelScope.launch {
            try {
                val result = repository1.getData()
                Log.d("VIEWMODEL_DATA", "Updating products with ${result.size} items")
                products = result
            } catch (e: Exception) {
                Log.e("VIEWMODEL_ERROR", "Failed to fetch products: ${e.message}")
                products = emptyList()
            } finally {
                isLoading = false // Reset loading state
            }
        }
    }

    // push
    fun addProduct(product: data1Item) {
        viewModelScope.launch {
            try {
                val response = repository1.addProduct(product)
                Log.d("VIEWMODEL_DATA", "Product added successfully: $response")
                fetchProducts() // Refresh the product list
            } catch (e: Exception) {
                Log.e("VIEWMODEL_ERROR", "Failed to add product: ${e.message}")
            }
        }
    }
}

