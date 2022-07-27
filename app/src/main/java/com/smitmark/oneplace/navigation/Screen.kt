package com.smitmark.oneplace.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.smitmark.oneplace.Home
import com.smitmark.oneplace.Website
import com.smitmark.oneplace.viewmodel.WebSiteViewModel

sealed interface Screen{
    val route:String
}

object Home:Screen{
    override val route: String
        get() = "home"
}

object Website:Screen{
    override val route: String
        get() = "website/{index}"
}


@Composable
fun NavigationGraph(navController: NavHostController){
    val webSiteViewModel:WebSiteViewModel = viewModel()
    NavHost(navController=navController, startDestination = Home.route){
        composable(route = Home.route){
                Home(navController = navController,webSiteViewModel)
        }
        composable(route = Website.route, arguments = listOf(navArgument(name = "index"){
            type = NavType.IntType
        })){
            Website(navController,it.arguments?.getInt("index") ?: 0,webSiteViewModel)
        }

    }
}
