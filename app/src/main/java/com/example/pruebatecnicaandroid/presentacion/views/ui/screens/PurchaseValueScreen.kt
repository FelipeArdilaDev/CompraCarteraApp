package com.example.pruebatecnicaandroid.presentacion.views.ui.screens

import android.widget.Toast
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.pruebatecnicaandroid.R
import com.example.pruebatecnicaandroid.data.local.PreferencesHelper
import com.example.pruebatecnicaandroid.domain.repository.PurchaseResult
import com.example.pruebatecnicaandroid.presentacion.viewmodel.TransactionViewModel
import com.example.pruebatecnicaandroid.presentacion.views.ui.routes.Routes
import com.example.pruebatecnicaandroid.utils.DPUtils
import java.text.NumberFormat
import java.util.Locale

@Composable
fun PurchaseValueScreen(
    txcViewModel: TransactionViewModel,
    navigationController: NavHostController,
    name: String
) {

    var cardNumber by rememberSaveable { mutableStateOf("") }
    var errorText by remember { mutableStateOf("") }
    var ammountValue by rememberSaveable { mutableStateOf("") }
    var ammount by rememberSaveable { mutableStateOf(0L) }
    val context = LocalContext.current
    val preferencesHelper by remember { mutableStateOf(PreferencesHelper(context)) }
    val txcNumber by txcViewModel.transactionNumber.observeAsState("")
    val purchaseResult by txcViewModel.PurchaseResult.observeAsState(PurchaseResult(false, ""))


    Scaffold(
        containerColor = Color.White,
        topBar = {
            MyTopAppbar(onclickIcon = {}, onclickDrawer = {})
        },
        floatingActionButton = {
            MyFAB {
                txcViewModel.fetchTransactionNumber(navigationController, name, ammountValue)
            }
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
                text = name,
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

                val inputText = cardNumber
                val cleanString = inputText.replace("[^\\d]".toRegex(), "")
                val parsed = cleanString.toDoubleOrNull() ?: 0.0
                val formattedInput = DPUtils.formatCurrency(parsed)
                ammountValue = formattedInput
                ammount = preferencesHelper.getBalance()

                errorText = if (parsed > ammount) {
                    "El monto no debe superar el cupo disponible de ${
                        DPUtils.formatCurrency(
                            ammount.toDouble()
                        )
                    }"
                } else {
                    ""
                }


            }
        }
    }

}

@Composable
fun AmmountTextField(cardNumber: String, errorText: String, onValueChanged: (String) -> Unit) {
    val context = LocalContext.current
    val preferencesHelper by remember { mutableStateOf(PreferencesHelper(context)) }
    var formattedText by remember { mutableStateOf(cardNumber) }

    Column {
        TextField(
            value = formattedText,
            onValueChange = { newValue ->
                // Remueve cualquier carácter no numérico como el símbolo $ y comas
                val cleanValue = newValue.replace("[^\\d]".toRegex(), "")

                // Si el campo está vacío, mantén el inputText vacío, si no, aplica el formato
                formattedText = if (cleanValue.isEmpty()) "" else DPUtils.formatCurrency(
                    cleanValue.toDoubleOrNull() ?: 0.0
                )

                onValueChanged(formattedText)
            },
            visualTransformation = PesosVisualTransformation(),
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
            text = if (errorText.isNotEmpty()) "El monto no debe superar el cupo disponible de ${
                DPUtils.formatCurrency(
                    preferencesHelper.getBalance().toDouble()
                )
            }" else "Ingrese un monto inferior a ${
                DPUtils.formatCurrency(
                    preferencesHelper.getBalance().toDouble()
                )
            }(cupo disponible)",
            color = if (errorText.isNotEmpty()) MaterialTheme.colorScheme.error
            else colorResource(R.color.blue),
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(start = 30.dp, top = 4.dp)
        )
    }
}

class PesosVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        // Limpia cualquier carácter que no sea un dígito
        val cleanText = text.text.replace("[^\\d]".toRegex(), "")

        // Convierte a Double y formatea en pesos colombianos
        val formattedValue = if (cleanText.isNotEmpty()) {
            "$ ${NumberFormat.getNumberInstance(Locale("es", "CO")).format(cleanText.toDouble())}"
        } else {
            "$ "
        }

        val transformedText = AnnotatedString(formattedValue)

        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                return transformedText.text.length // Coloca el cursor al final del texto
            }

            override fun transformedToOriginal(offset: Int): Int {
                return cleanText.length // Devuelve la posición al final del valor limpio
            }
        }

        return TransformedText(transformedText, offsetMapping)
    }
}
