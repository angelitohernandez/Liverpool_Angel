package mx.edu.utez.angel.ui.screens

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import mx.edu.utez.angel.models.ProductDetail
import mx.edu.utez.angel.models.Review

class ProductoViewModel : ViewModel() {

    private val _productos = MutableStateFlow<List<ProductDetail>>(emptyList())
    val productos: StateFlow<List<ProductDetail>> = _productos.asStateFlow()

    private val _productoSeleccionado = MutableStateFlow<ProductDetail?>(null)
    val productoSeleccionado: StateFlow<ProductDetail?> = _productoSeleccionado.asStateFlow()

    init {
        cargarProductosIniciales()
    }

    private fun cargarProductosIniciales() {
        _productos.value = listOf(
            ProductDetail(
                id = "1",
                brand = "DIOR",
                name = "Perfume Dior Sauvage Eau de Parfum 100 ml",
                price = 3450.00,
                rating = 4.8f,
                reviewCount = 128,
                isFreeShipping = true,
                description = "Una composición rotundamente fresca, dictada por un nombre que suena como un manifiesto. Una obra creada entre la brutalidad y la elegancia, con notas de Bergamota de Reggio di Calabria y Ámbar.",
                reviews = listOf(
                    Review("Cliente", "Excelente aroma y duración", "\"El perfume es 100% original, llegó rápido y bien empaquetado.\"", 5.0f)
                )
            ),
            ProductDetail(
                id = "2",
                brand = "LG",
                name = "Smart TV LG 55\" 4K UHD ThinQ AI",
                price = 9999.00,
                rating = 4.7f,
                reviewCount = 85,
                isFreeShipping = true,
                description = "Pantalla Smart TV 4K de alta calidad con procesador de alta velocidad e inteligencia artificial ThinQ AI integrada."
            ),
            ProductDetail(
                id = "3",
                brand = "NIKE",
                name = "Tenis Nike Air Max Para Hombre",
                price = 2899.00,
                rating = 4.5f,
                reviewCount = 42,
                isFreeShipping = true,
                description = "Comodidad suprema y amortiguación clásica Air Max para el uso diario con un diseño deportivo distintivo."
            ),
            ProductDetail(
                id = "4",
                brand = "MICHAEL KORS",
                name = "Bolsa MK tote Piel Saffiano Rosa",
                price = 4200.00,
                rating = 4.9f,
                reviewCount = 19,
                isFreeShipping = true,
                description = "Bolsa de mano fabricada en piel Saffiano de alta durabilidad con detalles dorados elegantes."
            )
        )
    }

    fun seleccionarProducto(id: String) {
        _productoSeleccionado.value = _productos.value.find { it.id == id }
    }

    fun toggleFavorite(id: String) {
        _productos.update { lista ->
            lista.map { if (it.id == id) it.copy(isFavorite = !it.isFavorite) else it }
        }
        _productoSeleccionado.value?.let { actual ->
            if (actual.id == id) {
                _productoSeleccionado.value = actual.copy(isFavorite = !actual.isFavorite)
            }
        }
    }
}