package com.example.littlelemon

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavigationComposable(
    navController: NavHostController,
    sharedPreferences: SharedPreferences,
    applicationContext: Context
){
    Log.e("MainActivity","Inside Navigation Composable")
    val startDestination:String = if(sharedPreferences.contains("firstName")&&sharedPreferences.contains("lastName")&&sharedPreferences.contains("email")){
        Home.route
    }else
        OnBoarding.route


    Log.e("MainActivity","Start Destination $startDestination ${sharedPreferences.contains("firstName")&&sharedPreferences.contains("lastName")&&sharedPreferences.contains("email")}")
    NavHost(navController = navController, startDestination = startDestination ){
        composable(OnBoarding.route){
            OnBoarding(sharedPreferences.edit(),navController,applicationContext)
        }
        composable(Home.route){
            Home(navController,applicationContext)
        }
        composable(Profile.route){
            Profile(sharedPreferences,navController)
        }
    }
}