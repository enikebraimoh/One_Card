package com.hackathon.onecard.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.ArrowCircleDown
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hackathon.onecard.R
import com.hackathon.onecard.signup.SignUpScreen
import com.hackathon.onecard.transaction.TransactionItem
import com.hackathon.onecard.transaction.fakeData
import com.hackathon.onecard.ui.theme.OneCardTheme
import com.hackathon.onecard.util.AppButton
import com.hackathon.onecard.util.BottomBarScreen
import com.hackathon.onecard.util.DashboardBalance
import com.hackathon.onecard.util.DashboardItem
import com.hackathon.onecard.util.Destinations
import com.hackathon.onecard.util.EmptyTransactions
import com.hackathon.onecard.util.InputField
import com.hackathon.onecard.util.InputState
import com.hackathon.onecard.util.NavigationAppBar
import com.hackathon.onecard.util.PasswordInputField
import kotlinx.coroutines.launch

fun NavGraphBuilder.homeRoute() {
    composable(BottomBarScreen.Home.route) {
        HomeScreen()
    }
}

@Composable
internal fun HomeScreen() {
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        content = { scaffoldPadding ->
            Column(
                Modifier
                    .padding(scaffoldPadding)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Spacer(modifier = Modifier.height(10.dp))

                Box(
                    modifier = Modifier
                        .background(
                            color = Color.Gray,
                            shape = CircleShape
                        )
                        .size(50.dp)
                ){
                    Text(
                        text = "U",
                        modifier = Modifier.align(Alignment.Center),
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                DashboardBalance(
                    balanceTitle = "Balance",
                    balance = "₦ 15,000.00",
                    icon = { Icon(Icons.Outlined.Visibility, "visibility") },
                    deposit = {}
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Quick Actions",
                    style = MaterialTheme.typography.button
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    DashboardItem(
                        icon = ImageVector.vectorResource(id = R.drawable.send_icon),
                        title = "Send Money"
                    )
                    DashboardItem(
                        icon = Icons.Outlined.Add,
                        title = "Add Money"
                    )
                    DashboardItem(
                        icon = Icons.Outlined.ArrowCircleDown,
                        title = "Withdraw"
                    )
                    DashboardItem(
                        icon = ImageVector.vectorResource(id = R.drawable.convert_icon),
                        title = "Exchange"
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Recent Transactions",
                    style = MaterialTheme.typography.button
                )
//                Box(
//                    contentAlignment = Alignment.Center,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                ) {
//                    EmptyTransactions(procees = {})
//                }

//                LazyColumn(
//                    content = {
//                        item(fakeData) {
//                            TransactionItem(
//                                transaction = it,
//                            )
//                        }
//                    }
//                )
                Column {
                    for (i in fakeData) {
                        Spacer(modifier = Modifier.height(20.dp))
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            TransactionItem(
                                transaction = i,
                           )
                        }
                    }
                }


            }
        }
    )
}

@Preview
@Composable
internal fun HomeScreenPreview() = OneCardTheme {
    HomeScreen()
}

