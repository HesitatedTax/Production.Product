package com.example.adventureworks
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @GET("{id}")
    fun getProduct(@Path("id") id: String): Call<Product>

    @POST("")
    fun createProduct(@Body product: Product): Call<Product>

    @PUT("{id}")
    fun updateProduct(@Path("id") id: String, @Body product: Product): Call<Product>
}
