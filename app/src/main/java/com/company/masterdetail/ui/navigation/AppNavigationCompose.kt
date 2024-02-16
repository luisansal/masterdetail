package com.company.masterdetail.ui.navigation

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.company.masterdetail.domain.model.PokeModel
import com.company.masterdetail.ui.chapter1.Chapter1Screen
import com.company.masterdetail.ui.chapter2.Chapter2Screen
import com.company.masterdetail.ui.chapter3.Chapter3Screen
import com.company.masterdetail.ui.main.MainScreen
import com.company.mylocations.ui.main.MainActivity
import com.google.gson.Gson

enum class AppScreens {
    MainScreen,
    Chapter1Screen,
    Chapter2Screen,
    Chapter3Screen,
    Chapter4Screen
}

@Composable
fun AppNavigationCompose() {
    val navController = rememberNavController()
    val context = LocalContext.current.applicationContext
    NavHost(navController = navController, startDestination = AppScreens.MainScreen.name) {
        composable(AppScreens.MainScreen.name) {
            MainScreen(
                gotoChapter1 = {
                    navController.navigate(AppScreens.Chapter1Screen.name)
                },
                gotoChapter2and3 = {
                    navController.navigate(AppScreens.Chapter2Screen.name)
                },
                gotoChapter4 = {
                    context.startActivity(Intent(context, MainActivity::class.java).apply {
                        flags = FLAG_ACTIVITY_NEW_TASK
                    })
                }
            )
        }
        composable(AppScreens.Chapter1Screen.name) {
            Chapter1Screen {
                navController.navigate(AppScreens.MainScreen.name)
            }
        }
        composable(AppScreens.Chapter2Screen.name) {
            Chapter2Screen(
                goTodetail = {
                    val pokeModelJson = Gson().toJson(it)
                    navController.navigate("${AppScreens.Chapter3Screen.name}/$pokeModelJson")
                }
            ) {
                navController.navigate(AppScreens.MainScreen.name)
            }
        }
        composable(
            "${AppScreens.Chapter3Screen.name}/{pokemon}",
            arguments = listOf(navArgument("pokemon") { type = NavType.StringType })
        ) {
            val pokemonJson = it.arguments?.getString("pokemon")!!
            val pokemonModel = Gson().fromJson(pokemonJson, PokeModel::class.java)
            Chapter3Screen(pokemonModel) {
                navController.navigate(AppScreens.Chapter2Screen.name)
            }
        }
    }
}