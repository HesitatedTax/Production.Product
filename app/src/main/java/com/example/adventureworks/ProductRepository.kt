package com.example.adventureworks

import android.content.Context
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

class ProductRepository(private val context: Context) {
    private val apiService = RetrofitClient.apiService

    suspend fun fetchProduct(productID: String): Product? = withContext(Dispatchers.IO) {
        try {
            val response = apiService.getProduct(productID).awaitResponse()
            if (response.isSuccessful) {
                response.body()
            } else {
                withContext(Dispatchers.Main) {
                    showToast("Failed to fetch product: ${response.message()}")
                }
                null
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                showToast("Network Error: ${e.message}")
            }
            null
        }
    }

    suspend fun updateProduct(id: String, product: Product): Boolean = withContext(Dispatchers.IO) {
        try {
            val response = apiService.updateProduct(id, product).awaitResponse()
            if (response.isSuccessful) {
                true
            } else {
                withContext(Dispatchers.Main) {
                    showToast("Failed to update product: ${response.message()}")
                }
                false
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                showToast("Network Error: ${e.message}")
            }
            false
        }
    }

    suspend fun createProduct(product: Product): Product? = withContext(Dispatchers.IO) {
        try {
            val response = apiService.createProduct(product).awaitResponse()
            if (response.isSuccessful) {
                response.body()
            } else {
                withContext(Dispatchers.Main) {
                    showToast("Failed to create product: ${response.message()}")
                }
                null
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                showToast("Network Error: ${e.message}")
            }
            null
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}
