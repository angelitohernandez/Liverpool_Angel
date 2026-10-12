package mx.edu.utez.angel.models

import java.util.UUID

data class Review(
    val author: String,
    val title: String,
    val comment: String,
    val rating: Float
)

data class ProductDetail(
    val id: String = UUID.randomUUID().toString(),
    val brand: String,
    val name: String,
    val price: Double,
    val rating: Float,
    val reviewCount: Int,
    val isFreeShipping: Boolean = true,
    val description: String,
    val image: Int? = null,
    val reviews: List<Review> = emptyList(),
    val isFavorite: Boolean = false
)