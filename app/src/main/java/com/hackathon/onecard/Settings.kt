package com.hackathon.onecard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hackathon.onecard.cards.CardsScreen
import com.hackathon.onecard.login.LoginScreen
import com.hackathon.onecard.ui.theme.OneCardTheme
import com.hackathon.onecard.util.AppButton
import com.hackathon.onecard.util.BottomBarScreen
import com.hackathon.onecard.util.DefaultAppBar
import com.hackathon.onecard.util.InputField
import com.hackathon.onecard.util.InputState
import com.hackathon.onecard.util.PasswordInputField
import com.hackathon.onecard.util.Destinations

fun NavGraphBuilder.settingsScreen() {
    composable(route = BottomBarScreen.Settings.route) {
        SettingsScreen()
    }
}

@Composable()
fun SettingsScreen() {
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
                        text = "Settings",
                        style = MaterialTheme.typography.h3,
                    )
                }

                Spacer(modifier = Modifier.height(40.dp))

                Box(
                    contentAlignment = Alignment.BottomCenter,
                    modifier = Modifier.fillMaxSize()
                ) {
                    AppButton(
                        buttonText = "Logout",
                        color = MaterialTheme.colors.error,
                        onClick = { /*TODO*/ }
                    )
                }
            }
        }
    )
}


@Composable
@Preview(showBackground = true)
fun SettingsScreenPreview() {
    OneCardTheme {
        SettingsScreen()
    }
}