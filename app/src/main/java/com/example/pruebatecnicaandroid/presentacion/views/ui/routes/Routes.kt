package com.example.pruebatecnicaandroid.presentacion.views.ui.routes

sealed class Routes(val route: String) {
    object DashboardScreen : Routes("dashboardScreen")

    object CardNumberScreen : Routes("cardNumberScreen/{name}") {
        fun createRoute(name: String) = "cardNumberScreen/$name"
    }

    object PurchaseValueScreen : Routes("purchaseValueScreen/{name}") {
        fun createRoute(name: String) = "purchaseValueScreen/$name"
    }

    object SuccessScreen : Routes("successScreen/{name}/{txcNumber}/{ammountValue}/{date}") {
        fun createRoute(name: String, txcNumber: String, ammountValue: String, date: String) =
            "successScreen/$name/$txcNumber/$ammountValue/$date"
    }
}