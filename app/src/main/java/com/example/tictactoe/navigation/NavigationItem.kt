package com.example.tictactoe.navigation

sealed class NavigationItem(val route: String){
    object PlayerPage: NavigationItem(route = "player_page")
    object GamePage: NavigationItem("game/{player1}/{player2}")
}