package com.hackathon.onecard.util

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hackathon.onecard.R
import com.hackathon.onecard.ui.theme.OneCardTheme

@Composable
fun EmptyCardsScreen(
    modifier: Modifier = Modifier,
    isLoading: Boolean = true,
    onClick : () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .padding(vertical = 20.dp)
            .fillMaxSize(1f)
    ) {

        Box(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .height(200.dp)
                .background(
                    MaterialTheme.colors.secondary,
                    shape = MaterialTheme.shapes.small
                )
                .padding(20.dp),
        )

        Column(
            modifier = Modifier
                .fillMaxSize(2f)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {

            Column(
                Modifier.fillMaxWidth()
            )
            {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = "One Card",
                    style = MaterialTheme.typography.h4
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = "For payments and subscriptions to online merchants." +
                            "\nEnjoy transactions enrichment and budgeting",
                    style = MaterialTheme.typography.body1.copy(
                        fontSize = 14.sp,
                        color = Color(0xFF8897A0)
                    )
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            AppButton(
                buttonText = "Get One Card",
                enabled = !isLoading,
                onClick = onClick
            )
        }
    }

}

@Preview
@Composable
fun EmptyCardsScreenPreview() {
    OneCardTheme {
        Surface(color = MaterialTheme.colors.background) {
            EmptyCardsScreen(onClick = {})
        }
    }
}