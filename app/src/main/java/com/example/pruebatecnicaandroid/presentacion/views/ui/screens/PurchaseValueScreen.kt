package com.example.pruebatecnicaandroid.presentacion.views.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pruebatecnicaandroid.R

@Preview
@Composable
fun PurchaseValueScreen() {

    var cardNumber by rememberSaveable { mutableStateOf("") }
    var errorText by remember { mutableStateOf("") }

    Scaffold(
        containerColor = Color.White,
        topBar = {
            MyTopAppbar(onclickIcon = {}, onclickDrawer = {})
        },
        floatingActionButton = {
            MyFAB {  }
        },
    ) { padding ->
        Column(Modifier.padding(padding)) {
            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth(),
                trackColor = colorResource(R.color.background),
                color = colorResource(R.color.purple),
                progress = { 1f }
            )

            Text(
                text = stringResource(R.string.lbl_step_2),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(15.dp),
                color = colorResource(R.color.black)
            )

            Text(
                text = stringResource(R.string.lbl_title_purchase),
                style = MaterialTheme.typography.titleLarge,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 15.dp),
                color = colorResource(R.color.black)
            )

            Text(
                text = stringResource(R.string.lbl_origen),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(start = 30.dp, top = 30.dp),
                color = colorResource(R.color.black)
            )

            Text(
                text = stringResource(R.string.lbl_testbank),
                style = MaterialTheme.typography.titleLarge,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 30.dp, top = 5.dp),
                color = colorResource(R.color.black)
            )

            Text(
                text = stringResource(R.string.lbl_ammount),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(start = 30.dp, top = 30.dp),
                color = colorResource(R.color.black)
            )

            AmmountTextField(cardNumber, errorText) {
                cardNumber = it
                if (cardNumber.length in 15..16) {
                    errorText = ""
                } else {
                    errorText = "El número de tarjeta debe tener 16 digitos"
                }

            }
        }
    }

}

@Composable
fun AmmountTextField(cardNumber: String, errorText: String, onValuechaged: (String) -> Unit) {

    Column {
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

        Text(
            text = "Ingrese un monto inferior a 400.000(cupo disponible)",
            color = if (false) MaterialTheme.colorScheme.error else colorResource(R.color.blue),
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(start = 30.dp, top = 4.dp)
        )
    }
    if (errorText.isNotEmpty()) {
        Text(
            text = errorText,
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(start = 30.dp, top = 4.dp)
        )

    }
}
