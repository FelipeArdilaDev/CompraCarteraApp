package com.example.pruebatecnicaandroid.presentacion.views.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.pruebatecnicaandroid.R

@Composable
fun SuccessScreen(name:String, txcNumber:String, ammountValue:String, date:String) {
    Scaffold(
        contentColor = Color.White,
        topBar = {
            MyTopAppbar(
                containerColor = Color.Red,
                contentColor = Color.White,
                onclickIcon = {},
                onclickDrawer = {})
        },
        floatingActionButton = {
            MyFAB(
                color = colorResource(R.color.purple),
                coloText = Color.White,
                text = "Finalizar"
            ) {

            }
        },
    ) { padding ->

        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .safeContentPadding()
        ) {

            val (card) = createRefs()
            Image(
                painter = painterResource(id = R.drawable.indonesia_53960_7_600),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Card(
                modifier = Modifier
                    .padding(horizontal = 30.dp)
                    .constrainAs(card) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom, margin = 16.dp)
                        width = Dimension.fillToConstraints
                    },
                shape = MaterialTheme.shapes.medium,
                elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_check_circle_outline),
                        contentDescription = "",
                        modifier = Modifier.size(60.dp),
                        alignment = Alignment.TopCenter
                    )

                    Text(
                        text = stringResource(R.string.lbl_compra_de_deuda_exitosa),
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(top = 5.dp),
                        color = colorResource(R.color.green)
                    )

                    Column(
                        modifier = Modifier.fillMaxWidth().padding(16.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.origen),
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(top = 8.dp),
                            color = colorResource(R.color.secondaryText)
                        )

                        Text(
                            text = name,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(top = 5.dp),
                            color = colorResource(R.color.black)
                        )

                        Text(
                            text = stringResource(R.string.lbl_txc_number),
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(top = 15.dp),
                            color = colorResource(R.color.secondaryText)
                        )

                        Text(
                            text = txcNumber,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(top = 5.dp),
                            color = colorResource(R.color.black)
                        )

                        Text(
                            text = stringResource(R.string.lbl_value),
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(top = 15.dp),
                            color = colorResource(R.color.secondaryText)
                        )

                        Text(
                            text = ammountValue,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(top = 5.dp),
                            color = colorResource(R.color.black)
                        )

                        Text(
                            text = stringResource(R.string.lbl_date),
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(top = 15.dp),
                            color = colorResource(R.color.secondaryText)
                        )

                        Text(
                            text = date,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(top = 5.dp),
                            color = colorResource(R.color.black)
                        )
                    }

                    Text(
                        text = stringResource(R.string.lbl_description),
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(15.dp),
                        color = colorResource(R.color.secondaryText),
                        textAlign = TextAlign.Center
                    )
                }
            }

        }

    }

}