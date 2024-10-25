package com.example.pruebatecnicaandroid.presentacion.views.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import com.example.pruebatecnicaandroid.R
import com.example.pruebatecnicaandroid.data.local.PreferencesHelper
import com.example.pruebatecnicaandroid.presentacion.viewmodel.DashboardViewModel
import com.example.pruebatecnicaandroid.presentacion.views.ui.routes.Routes
import com.example.pruebatecnicaandroid.utils.DPUtils

@Composable
fun DashboardScreen(
    dashboardViewModel: DashboardViewModel,
    preferencesHelper: PreferencesHelper,
    navigationController: NavHostController
) {
    val userInfo by dashboardViewModel.userInfo.observeAsState()

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .safeContentPadding()
    ) {

        Image(
            painter = painterResource(id = R.drawable.indonesia_53960_7_600),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        val (profileImage, bankImage, greetingText, usernameText, productText, card, fab) = createRefs()

        Image(
            painter = painterResource(id = R.drawable.ic_user),
            contentDescription = "",
            modifier = Modifier
                .size(50.dp)
                .constrainAs(profileImage) {
                    top.linkTo(parent.top, margin = 30.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                })

        Image(painter = painterResource(R.drawable.ic_waller_logo),
            contentDescription = "",
            modifier = Modifier
                .size(50.dp)
                .constrainAs(bankImage) {
                    top.linkTo(parent.top, margin = 30.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                })

        Text(
            text = stringResource(id = R.string.lbl_hola),
            color = Color.White,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.constrainAs(greetingText) {
                top.linkTo(bankImage.bottom, margin = 16.dp)
                start.linkTo(parent.start, margin = 16.dp)
            }

        )

        Text(
            text = userInfo?.name ?: "",
            color = Color.White,
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier.constrainAs(usernameText) {
                top.linkTo(greetingText.bottom, margin = 16.dp)
                start.linkTo(parent.start, margin = 16.dp)
            }

        )

        Text(
            text = stringResource(id = R.string.lbl_products),
            color = Color.White,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .constrainAs(productText) {
                    top.linkTo(usernameText.bottom, margin = 8.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                }
        )

        Card(
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .fillMaxHeight(0.4f)
                .constrainAs(card) {
                    top.linkTo(productText.bottom)
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
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_circle),
                    contentDescription = "",
                    modifier = Modifier.size(24.dp)
                )

                Text(
                    text = stringResource(R.string.visa_oro),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 5.dp),
                    color = colorResource(R.color.black)
                )

                Text(
                    text = userInfo?.cardNumber ?: "",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = 8.dp),
                    color = colorResource(R.color.black)
                )

                Text(
                    text = stringResource(R.string.lbl_aviable),
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(top = 30.dp),
                    color = colorResource(R.color.black)
                )

                Text(
                    text = DPUtils.formatCurrency(userInfo?.balance?.toDouble() ?: 0.0),
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(top = 16.dp),
                    color = colorResource(R.color.black)
                )

                Text(
                    text = stringResource(R.string.lbl_pago_minimo),
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(top = 30.dp),
                    color = colorResource(R.color.black)
                )

                Text(
                    text = stringResource(R.string.lbl_pay_test),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = 5.dp),
                    color = colorResource(R.color.black)
                )
            }
        }

        ExtendedFloatingActionButton(
            onClick = {
                userInfo?.let { preferencesHelper.saveBalance(it.balance) }
                navigationController.navigate(Routes.CardNumberScreen.route)
            },

            modifier = Modifier
                .padding(bottom = 50.dp)
                .constrainAs(fab) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            containerColor = colorResource(R.color.purple),
            contentColor = Color.White

        ) {
            Text(text = stringResource(R.string.lbl_compra_de_deuda))
            Icon(painter = painterResource(R.drawable.ic_wallet), contentDescription = "")

        }

    }

}