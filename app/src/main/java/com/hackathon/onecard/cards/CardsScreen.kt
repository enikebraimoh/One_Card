package com.hackathon.onecard.cards

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hackathon.onecard.ui.theme.OneCardTheme
import com.hackathon.onecard.util.AppButton
import com.hackathon.onecard.util.BottomBarScreen
import com.hackathon.onecard.util.Destinations

fun NavGraphBuilder.cardsScreen(
    navigateToMaps: () -> Unit,
) {
    composable(route = Destinations.PickCard.route) {
        CardsScreen(
            navigateToMaps = navigateToMaps,
        )
    }
}

@Composable()
fun CardsScreen(
    navigateToMaps: () -> Unit = {},
) {

    Column(
        Modifier
            .padding(horizontal = 20.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        Box(
            contentAlignment = Alignment.TopStart,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Pickup for a Card from a nearby merchant",
                style = MaterialTheme.typography.h3,
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier.fillMaxSize(2f)
        ) {
            AppButton(
                buttonText = "Pickup Card",
            ) {
                navigateToMaps()
                //   login(navigateToDashBoard, navigateToEmailVerification)
            }
        }
        Spacer(modifier = Modifier.height(40.dp))

    }
}


@Composable
@Preview(showBackground = true)
fun CardsScreenPreview() {
   OneCardTheme {
       CardsScreen()
   }
}
