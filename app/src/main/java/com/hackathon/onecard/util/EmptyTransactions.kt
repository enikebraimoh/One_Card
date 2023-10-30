package com.hackathon.onecard.util

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.hackathon.onecard.R
import com.hackathon.onecard.ui.theme.OneCardTheme

@Composable
fun EmptyTransactions(procees: () -> Unit) {

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.empty_transaction))
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 20.dp)
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        LottieAnimation(
            modifier = Modifier.size(200.dp),
            composition = composition,
            progress = progress
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "You have no transactions yet",
            style = MaterialTheme.typography.body1.copy(
                fontSize = 20.sp
            )
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "your transactions will appear here,\nperform a transaction to see what i am talking about",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body1.copy(
                fontSize = 14.sp, color = Color(0xFF8897A0)
            )
        )
        Spacer(modifier = Modifier.height(20.dp))
      //  AppButton(buttonText = "Perform transaction", onClick = { procees() })
    }
}

@Composable
@Preview
fun EmptyTransactionsPreview() {
    OneCardTheme {
        EmptyTransactions(procees = {})
    }
}