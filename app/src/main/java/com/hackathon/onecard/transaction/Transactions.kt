package com.hackathon.onecard.transaction

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hackathon.onecard.R
import com.hackathon.onecard.ui.theme.OneCardTheme
import com.hackathon.onecard.util.AppButton
import com.hackathon.onecard.util.BottomBarScreen
import com.hackathon.onecard.util.formatDateTime

fun NavGraphBuilder.transactionsScreen() {
    composable(route = BottomBarScreen.Transaction.route) {
        TransactionScreen()
    }
}

@Composable()
fun TransactionScreen() {
    Scaffold(
        content = { padding ->
            Column(
                Modifier
                    .padding(horizontal = 20.dp)
                    .padding(padding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                Box(
                    contentAlignment = Alignment.TopStart,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Transactions",
                        style = MaterialTheme.typography.h3,
                    )
                }

                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    )
}


@Composable
@Preview(showBackground = true)
fun TransactionScreenPreview() {
    OneCardTheme {
        TransactionScreen()
    }
}

@Composable
fun TransactionItem(
    transaction: Data,
   // navigateToDetails: (transactionId: String, amount: String, date: String, purpose: String, status: String) -> Unit,
) {

    //var amount = Money((transaction.amount / 100), Util.NAIRA)
    val amount = (transaction.amount / 100)

    val icon = when (transaction.txn_type) {
        "credit" -> {
            R.drawable.credit_icon
        }

        "debit" -> {
            R.drawable.debit_icon
        }

        else -> {
            R.drawable.debit_icon
        }
    }

    Row(
        modifier = Modifier
            .clip(MaterialTheme.shapes.large)
            .clickable {
//                navigateToDetails(
//                    transaction._id,
//                    amount,
//                    transaction.createdAt,
//                    transaction.purpose,
//                    transaction.status
//                )
            }
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Spacer(modifier = Modifier.width(10.dp))
        Image(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier.size(50.dp)
        )
        Column(
            modifier = Modifier
                .padding(10.dp)
                .weight(1f)
        ) {
            Text(
                text = transaction.purpose.capitalize(),
                style = MaterialTheme.typography.h4.copy(fontSize = 14.sp)
            )
            Text(
                text = formatDateTime(transaction.createdAt),
                style = MaterialTheme.typography.body1.copy(
                    color = Color.Gray,
                    fontSize = 12.sp
                ),
                maxLines = 1
            )
        }
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .weight(1f),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "₦$amount",
                style = MaterialTheme.typography.h4.copy(fontSize = 14.sp)
            )
            Text(
                text = "-",
                style = MaterialTheme.typography.body1.copy(
                    color = Color.Gray,
                    fontSize = 12.sp
                )
            )
        }
    }
}
