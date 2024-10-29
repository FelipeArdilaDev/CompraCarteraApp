package com.example.pruebatecnicaandroid.presentacion.views.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pruebatecnicaandroid.data.local.PreferencesHelper
import com.example.pruebatecnicaandroid.domain.usecase.GetUserInfoUseCase
import com.example.pruebatecnicaandroid.presentacion.viewmodel.CardViewModel
import com.example.pruebatecnicaandroid.presentacion.viewmodel.DashboardViewModel
import com.example.pruebatecnicaandroid.presentacion.viewmodel.DashboardViewModelFactory
import com.example.pruebatecnicaandroid.presentacion.views.ui.activities.ui.theme.PruebaTecnicaAndroidTheme
import com.example.pruebatecnicaandroid.presentacion.views.ui.routes.Routes
import com.example.pruebatecnicaandroid.presentacion.views.ui.screens.CardNumberScreen
import com.example.pruebatecnicaandroid.presentacion.views.ui.screens.DashboardScreen
import com.example.pruebatecnicaandroid.presentacion.views.ui.screens.PurchaseValueScreen
import com.example.pruebatecnicaandroid.presentacion.views.ui.screens.SuccessScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivityCompose : ComponentActivity() {

    @Inject
    lateinit var getUserInfoUseCase: GetUserInfoUseCase

    private lateinit var preferencesHelper: PreferencesHelper

    private val cardViewModel: CardViewModel by viewModels()

    private val dashboardViewModel: DashboardViewModel by viewModels {
        DashboardViewModelFactory(getUserInfoUseCase)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        preferencesHelper = PreferencesHelper(this)
        dashboardViewModel.loadUserInfo()

        setContent {
            PruebaTecnicaAndroidTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navigationController = rememberNavController()

                    NavHost(
                        navController = navigationController,
                        startDestination = Routes.DashboardScreen.route
                    ) {
                        composable(Routes.DashboardScreen.route) {
                            DashboardScreen(
                                dashboardViewModel,
                                preferencesHelper,
                                navigationController
                            )
                        }
                        composable(Routes.CardNumberScreen.route) {
                            CardNumberScreen(cardViewModel,navigationController)
                        }

                        composable(Routes.PurchaseValueScreen.route) {
                            PurchaseValueScreen()
                        }

                        composable(Routes.SuccessScreen.route) {
                            SuccessScreen()
                        }
                    }
                }
            }
        }
    }
}
