package mx.edu.utez.angel.ui.screens

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import mx.edu.utez.angel.R
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
                brand = "ARMAF",
                name = "Armaf Odyssey Mandarine Sky Eau de Parfum 100 ml",
                price = 599.00,
                rating = 4.8f,
                reviewCount = 4,
                isFreeShipping = true,
                description = "Una fragancia gourmand cítrica y oriental vibrante. Destaca por sus notas de mandarina, naranja, caramelo, haba tonka y un toque dulce amaderado perfecto para hacerse notar.",
                image = R.drawable.mandarin,
                reviews = listOf(
                    Review("Carlos M.", "Aroma increíble y dulce", "\"El olor a mandarina y caramelo es riquísimo, dura bastante tiempo en piel y ropa.\"", 5.0f),
                    Review("Ana P.", "Súper recomendado", "\"Llegó muy bien empaquetado y el envase en forma de termo/odyssey está genial.\"", 5.0f),
                    Review("Luis G.", "Excelente proyección", "\"Por el precio de $599 ofrece una calidad y duración sorprendentes.\"", 4.5f),
                    Review("Sofia T.", "Huele delicioso", "\"Una de mis fragancias favoritas, totalmente recomendado para regalar.\"", 4.5f)
                )
            ),
            ProductDetail(
                id = "2",
                brand = "LG",
                name = "Smart TV LG 55\" 4K UHD ThinQ AI",
                price = 9999.00,
                rating = 4.7f,
                reviewCount = 4,
                isFreeShipping = true,
                description = "Pantalla Smart TV 4K de alta calidad con procesador de alta velocidad e inteligencia artificial ThinQ AI integrada.",
                image = R.drawable.television,
                reviews = listOf(
                    Review("Fernando R.", "Excelente imagen", "\"La calidad 4K se ve increíble, los colores son muy vivos.\"", 5.0f),
                    Review("Maria S.", "Muy rápido el sistema", "\"El sistema operativo webOS responde de inmediato.\"", 5.0f),
                    Review("Jorge K.", "Buen sonido", "\"El sonido integrado es fuerte, aunque mejor con barra de sonido.\"", 4.0f),
                    Review("Diana V.", "Gran relación calidad-precio", "\"Llegó al día siguiente de la compra en excelente estado.\"", 5.0f)
                )
            ),
            ProductDetail(
                id = "3",
                brand = "NIKE",
                name = "Tenis Nike Air Max Para Hombre",
                price = 2899.00,
                rating = 4.5f,
                reviewCount = 4,
                isFreeShipping = true,
                description = "Comodidad suprema y amortiguación clásica Air Max para el uso diario con un diseño deportivo distintivo.",
                image = R.drawable.tenis,
                reviews = listOf(
                    Review("Roberto L.", "Muy cómodos", "\"Excelentes para caminar largas distancias sin cansarte.\"", 5.0f),
                    Review("Andrés H.", "Diseño genial", "\"Combinan con todo y la suela Air Max amortigua muy bien.\"", 4.5f),
                    Review("Valeria M.", "Talla perfecta", "\"Pídelos en tu talla exacta, ajustan super bien al pie.\"", 4.5f),
                    Review("Gabriel C.", "Satisfecho", "\"La calidad del material se siente premium como todo lo de Nike.\"", 4.0f)
                )
            ),
            ProductDetail(
                id = "4",
                brand = "KELLOGG'S",
                name = "Cereal Zucaritas Kellogg's 710 g",
                price = 85.00,
                rating = 4.9f,
                reviewCount = 4,
                isFreeShipping = true,
                description = "Hojuelas de maíz escarchadas con azúcar, adicionadas con vitaminas y hierro. ¡Sacan el tigre que llevas dentro!",
                image = R.drawable.zucaritas,
                reviews = listOf(
                    Review("Mateo R.", "¡Súper crujientes!", "\"Las mejores hojuelas con leche fría, el empaque rinde bastante.\"", 5.0f),
                    Review("Laura V.", "Un clásico del desayuno", "\"Llegó bien empaquetado y la caja no venía aplastada.\"", 5.0f),
                    Review("Daniela S.", "Excelente sabor", "\"A mis hijos les encantan para antes de ir a la escuela.\"", 5.0f),
                    Review("Oscar M.", "Buen precio", "\"Súper práctico pedirlo por aquí con el envío rápido.\"", 4.5f)
                )
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