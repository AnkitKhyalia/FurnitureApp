package com.example.furnitureapp.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.furnitureapp.Screens.CheckOutScreen
import com.example.furnitureapp.Screens.HomeScren
import com.example.furnitureapp.Screens.ProductScreen

@Composable
fun FurnitureNavigation() {
    val navHostController= rememberNavController()

    NavHost(navController = navHostController, startDestination = Home ){
        composable(Home){
            HomeScren(navHostController)
        }
        composable(ProductDetail){
            ProductScreen(navHostController)
        }
        composable(Checkout){
             CheckOutScreen(navHostController)
        }
    }

}
const val Home= "home_screen"
const val ProductDetail= "product_detail_screen"
const val Checkout= "check_out_screen"
