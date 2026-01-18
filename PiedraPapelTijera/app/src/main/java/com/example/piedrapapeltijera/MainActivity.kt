package com.example.piedrapapeltijera

import GameScreen
import ResultScreen
import WelcomeScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "welcome") {

                // Pantalla de Bienvenida
                composable("welcome") {
                    WelcomeScreen(onNavigateToGame = { name ->
                        navController.navigate("game/$name")
                    })
                }

                // Pantalla de Juego
                composable(
                    route = "game/{playerName}",
                    arguments = listOf(navArgument("playerName") { type = NavType.StringType })
                ) { backStackEntry ->
                    val name = backStackEntry.arguments?.getString("playerName") ?: "Jugador"
                    GameScreen(
                        playerName = name,
                        onNavigateToResult = { pName, pScore, aiScore ->
                            navController.navigate("result/$pName/$pScore/$aiScore") {
                                popUpTo("welcome") // Evita que al dar atrás vuelva al juego terminado
                            }
                        }
                    )
                }

                // Pantalla de Resultados
                composable(
                    route = "result/{name}/{pScore}/{aiScore}",
                    arguments = listOf(
                        navArgument("name") { type = NavType.StringType },
                        navArgument("pScore") { type = NavType.IntType },
                        navArgument("aiScore") { type = NavType.IntType }
                    )
                ) { backStackEntry ->
                    ResultScreen(
                        name = backStackEntry.arguments?.getString("name") ?: "",
                        pScore = backStackEntry.arguments?.getInt("pScore") ?: 0,
                        aiScore = backStackEntry.arguments?.getInt("aiScore") ?: 0,
                        onPlayAgain = { navController.navigate("welcome") }
                    )
                }
            }
        }
    }
}