package com.hackathon.onecard.util

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hackathon.onecard.R
import com.hackathon.onecard.ui.theme.OneCardTheme

@Composable
fun DashboardBalance(
    balanceTitle: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.primary,
    currency : String = "₦ Nigerian Naira",
    icon: @Composable RowScope.() -> Unit? = { null },
    balance: String,
    deposit: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.small,
        backgroundColor = color,
        elevation = 2.dp
    ) {
        Box(modifier = modifier) {
            Image(
                contentScale = ContentScale.Crop,
                modifier = modifier.matchParentSize(),
                painter = painterResource(id = R.drawable.balance_bg),
                contentDescription = null,
                alpha = 0.1f
            )
            Column {
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column() {
                        Box(
                            modifier = modifier
                                .background(
                                    color = Color.Gray,
                                    shape = RoundedCornerShape(20.dp)
                                )
                                .padding(vertical = 5.dp, horizontal = 10.dp)
                        ) {
                            Text(
                                text = currency,
                                style = MaterialTheme.typography.body1.copy(
                                    fontSize = 15.sp,
                                    color = MaterialTheme.colors.onPrimary
                                )
                            )
                        }
                        Spacer(modifier = modifier.height(10.dp))
                        Text(
                            text = balance,
                            style = MaterialTheme.typography.body1.copy(
                                fontSize = 30.sp,
                                color = MaterialTheme.colors.onPrimary
                            )
                        )
                    }
                    Row() {
                        icon()
                    }
                }
            }
        }
    }
}

@Composable
internal fun Wallet(modifier: Modifier = Modifier, balance: String) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.small,
        backgroundColor = Color(0xFF141D2A),
        elevation = 2.dp
    ) {
        Box(modifier = modifier) {
            Image(
                contentScale = ContentScale.Crop,
                modifier = modifier.matchParentSize(),
                painter = painterResource(id = R.drawable.balance_bg),
                contentDescription = null,
                alpha = 0.1f
            )
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(30.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column() {
                    Text(
                        text = "Main Balance",
                        style = MaterialTheme.typography.body1.copy(
                            fontSize = 12.sp,
                            color = MaterialTheme.colors.onPrimary
                        )
                    )
                    Spacer(modifier = modifier.height(5.dp))
                    Text(
                        text = balance,
                        style = MaterialTheme.typography.body1.copy(
                            fontSize = 22.sp,
                            color = MaterialTheme.colors.onPrimary
                        )
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Add Money",
                        style = MaterialTheme.typography.body1.copy(
                            fontSize = 15.sp,
                            color = MaterialTheme.colors.onPrimary
                        )
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    FloatingActionButton(
                        onClick = { },
                        modifier = Modifier.size(40.dp),
                        backgroundColor = MaterialTheme.colors.secondary,
                    ) {
                        Icon(Icons.Filled.Add, "deposit")
                    }
                }
            }
        }
    }

}

@Preview
@Composable
internal fun WalletPreview() = OneCardTheme {
    Wallet(balance = "0.0")
}


@Preview
@Composable
fun NewDashboardBalancePreview() = OneCardTheme {
    DashboardBalance(
        balanceTitle = "Account Balance",
        icon = { Icon(Icons.Outlined.Visibility, "visibility") },
        balance = "4,340,570.00",
        deposit = {}
    )
}

@Preview
@Composable
fun DashboardBalancePreview() = OneCardTheme {
    DashboardBalance(balanceTitle = "Cashback Balance", balance = "4,340,570.00", deposit = {})
}