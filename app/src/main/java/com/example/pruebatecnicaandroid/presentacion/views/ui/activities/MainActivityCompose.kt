package com.example.pruebatecnicaandroid.presentacion.views.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.viewModels
import com.example.pruebatecnicaandroid.domain.usecase.GetUserInfoUseCase
import com.example.pruebatecnicaandroid.presentacion.viewmodel.DashboardViewModel
import com.example.pruebatecnicaandroid.presentacion.viewmodel.DashboardViewModelFactory
import com.example.pruebatecnicaandroid.presentacion.views.ui.activities.ui.theme.PruebaTecnicaAndroidTheme
import com.example.pruebatecnicaandroid.presentacion.views.ui.screens.DashboardScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivityCompose : ComponentActivity() {

    @Inject
    lateinit var getUserInfoUseCase: GetUserInfoUseCase

    private val dashboardViewModel: DashboardViewModel by viewModels {
        DashboardViewModelFactory(getUserInfoUseCase)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PruebaTecnicaAndroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DashboardScreen()
                }
            }
        }
    }
}
