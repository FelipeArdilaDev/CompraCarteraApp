package com.example.pruebatecnicaandroid.presentacion.views.ui.routes

sealed class Routes(val route:String) {
    object DashboardScreen : Routes("dashboardScreen")
    object CardNumberScreen : Routes("cardNumberScreen")
}