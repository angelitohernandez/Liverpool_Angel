package mx.edu.utez.angel.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import mx.edu.utez.angel.ui.screens.Detalles
import mx.edu.utez.angel.ui.screens.ProductoViewModel
import mx.edu.utez.angel.ui.screens.Productos
import mx.edu.utez.angel.ui.screens.TituloLiverpool

sealed class Screen(val route: String) {
    object TituloLiverpool : Screen("titulo_liverpool")
    object Productos : Screen("productos")
    object Detalles : Screen("detalles")
}

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    viewModel: ProductoViewModel = viewModel()
) {
    val productos by viewModel.productos.collectAsStateWithLifecycle()
    val productoSeleccionado by viewModel.productoSeleccionado.collectAsStateWithLifecycle()

    NavHost(
        navController = navController,
        startDestination = Screen.TituloLiverpool.route
    ) {
        composable(Screen.TituloLiverpool.route) {
            TituloLiverpool(
                onNavigateToProductos = {
                    navController.navigate(Screen.Productos.route)
                }
            )
        }

        composable(Screen.Productos.route) {
            Productos(
                productos = productos,
                onProductoClick = { id ->
                    viewModel.seleccionarProducto(id)
                    navController.navigate(Screen.Detalles.route)
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.Detalles.route) {
            productoSeleccionado?.let { producto ->
                Detalles(
                    producto = producto,
                    onFavoriteToggle = {
                        viewModel.toggleFavorite(producto.id)
                    },
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}