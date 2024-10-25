package com.example.pruebatecnicaandroid.presentacion.views.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun SuccessScreen() {
    Scaffold(contentColor = Color.White) { padding ->
        Column(Modifier.padding(padding)) {
            Text("success ")
        }
    }

}