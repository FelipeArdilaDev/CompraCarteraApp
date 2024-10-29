package com.example.pruebatecnicaandroid.presentacion.views.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.example.pruebatecnicaandroid.R
import com.example.pruebatecnicaandroid.presentacion.viewmodel.CardViewModel


@Composable
fun CardNumberScreen(cardViewModel: CardViewModel, navigationController: NavHostController) {

    val showDialog: Boolean by cardViewModel.showDialog.observeAsState(false)
    var cardNumber by rememberSaveable { mutableStateOf("") }
    var errorText by remember { mutableStateOf("") }
    var isButtonEnable by remember { mutableStateOf(false) }

    Scaffold(
        containerColor = Color.White,
        topBar = {
            MyTopAppbar(onclickIcon = {}, onclickDrawer = {})
        },
        floatingActionButton = {
            MyFAB {
                val bin = cardNumber.take(6)
                cardViewModel.validateBinAndProceed(bin, navigationController)
            }
        },
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

                CardNumberTextField(cardNumber, errorText) {
                    cardNumber = it
                    if (cardNumber.length in 15..16) {
                        errorText = ""
                        isButtonEnable = true
                    } else {
                        errorText = "El número de tarjeta debe tener 16 digitos"
                        isButtonEnable = false
                    }

                }

                ErrorDialog(showDialog) {
                    cardViewModel.onDialogClose()
                }
            }
        }

    )
}

@Composable
fun CardNumberTextField(cardNumber: String, errorText: String, onValuechaged: (String) -> Unit) {

    TextField(
        value = cardNumber,
        onValueChange = {
            onValuechaged(it)
        },
        isError = errorText.isNotEmpty(),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp),
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = colorResource(R.color.black),
            cursorColor = colorResource(R.color.purple),
            focusedBorderColor = colorResource(R.color.purple),
        ),

        )
    if (errorText.isNotEmpty()) {
        Text(
            text = errorText,
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(start = 30.dp, top = 4.dp)
        )

    }

}

@Composable
fun MyFAB(
    color: Color = colorResource(R.color.background),
    coloText: Color = Color.Black,
    text: String = stringResource(R.string.btn_continue),
    onclickDrawer: () -> Unit
) {
    ExtendedFloatingActionButton(
        onClick = {
            onclickDrawer()
        },
        containerColor = color,
        contentColor = Color.White,
    ) {
        Text(text = text, color = coloText)
        Icon(
            imageVector = Icons.Filled.ChevronRight,
            contentDescription = "Add",
            tint = coloText
        )
    }
}


@Composable
fun ErrorDialog(showDialog: Boolean, ondismiss: () -> Unit) {
    if (showDialog) {
        Dialog(onDismissRequest = { ondismiss() }) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                Text(
                    "Upss, el numero de tarjeta no parecer ser de colombia",
                    fontSize = 16.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.size(16.dp))
                Button(onClick = {
                    ondismiss()
                }, modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Aceptar", color = Color.Black)
                }
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppbar(
    containerColor: Color = Color.White,
    contentColor: Color = Color.Black,
    onclickIcon: (String) -> Unit,
    onclickDrawer: () -> Unit
) {
    TopAppBar(
        title = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.lbl_app_bar_title),
                    fontSize = 20.sp,
                    color = contentColor,
                    textAlign = TextAlign.Center
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = containerColor,
            titleContentColor = contentColor,
            actionIconContentColor = contentColor,
            navigationIconContentColor = contentColor
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