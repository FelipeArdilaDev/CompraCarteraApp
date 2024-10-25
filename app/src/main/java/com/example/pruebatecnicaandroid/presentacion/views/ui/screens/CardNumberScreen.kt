package com.example.pruebatecnicaandroid.presentacion.views.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.pruebatecnicaandroid.R

@Composable
fun CardNumberScreen(navigationController: NavHostController) {

    Scaffold(
        containerColor = Color.White,
        topBar = {
            MyTopAppbar(onclickIcon = {}, onclickDrawer = {})
        },
        floatingActionButton = { MyFAB() },
        content = { padding ->
            Column(modifier = Modifier.padding(padding)) {
                LinearProgressIndicator(
                    modifier = Modifier.fillMaxWidth(),
                    trackColor = colorResource(R.color.background),
                    color = colorResource(R.color.purple),
                    progress = { 0.5f }
                )

                Text(
                    text = stringResource(R.string.lbl_step_1),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(15.dp),
                    color = colorResource(R.color.black)
                )

                Text(
                    text = stringResource(R.string.title_card),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(start = 15.dp, top = 10.dp),
                    color = colorResource(R.color.black)
                )

                Text(
                    text = stringResource(R.string.lbl_card),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(start = 30.dp, top = 30.dp),
                    color = colorResource(R.color.black)
                )

                CardNumberTextField("") {}

            }
        }

    )
}

@Composable
fun CardNumberTextField(numberCard: String, onTextChanged: (String) -> Unit) {
    var myTask: String by remember { mutableStateOf("") }
    OutlinedTextField(
        value = myTask,
        onValueChange = { myTask = it },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp),
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = colorResource(R.color.black),
            cursorColor = colorResource(R.color.purple),
            focusedBorderColor = colorResource(R.color.purple),
        ),

    )
}

@Composable
fun MyFAB() {
    ExtendedFloatingActionButton(
        onClick = {},
        containerColor = colorResource(R.color.background),
        contentColor = Color.White
    ) {
        Text(text = stringResource(R.string.btn_continue), color = Color.Black)
        Icon(
            imageVector = Icons.Filled.ChevronRight,
            contentDescription = "Add",
            tint = Color.Black
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppbar(onclickIcon: (String) -> Unit, onclickDrawer: () -> Unit) {
    TopAppBar(
        title = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.lbl_app_bar_title),
                    fontSize = 20.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White,
            titleContentColor = Color.Black,
            actionIconContentColor = Color.Black,
            navigationIconContentColor = Color.Black
        ),
        navigationIcon = {
            IconButton(onClick = { onclickDrawer() }) {
                Icon(imageVector = Icons.Filled.ArrowBackIosNew, contentDescription = "")
            }
        },
        actions = {
            IconButton(onClick = { onclickIcon("Salir") }) {
                Icon(imageVector = Icons.Filled.ExitToApp, contentDescription = "")
            }
        }
    )
}