package com.hackathon.onecard.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
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
import com.hackathon.onecard.ui.theme.OneCardTheme
import com.hackathon.onecard.util.AppButton
import com.hackathon.onecard.util.InputField
import com.hackathon.onecard.util.InputState
import com.hackathon.onecard.util.PasswordInputField
import com.hackathon.onecard.util.Destinations

fun NavGraphBuilder.loginScreen(
    navigateToDashBoard: () -> Unit,
    navigateToSignUp: () -> Unit
) {
    composable(route = Destinations.Login.route) {
        LoginScreen(
            navigateToDashBoard = navigateToDashBoard,
            navigateToSignUp = navigateToSignUp
        )
    }
}

@Composable()
fun LoginScreen(
    navigateToDashBoard: () -> Unit = {},
    navigateToSignUp: () -> Unit = {},
) {
    val scaffoldState = rememberScaffoldState()
    var email by remember { mutableStateOf("ramez@gmail.com") }
    var password by remember { mutableStateOf("123456") }
    Column(
        Modifier
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        Box(
            contentAlignment = Alignment.TopStart,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Welcome to oneCard",
                style = MaterialTheme.typography.h3,
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        InputField(
            state = InputState(
                label = "Email",
                value = email,
                onValueChanged = { email = it },
                placeholder = "Enter your email",
            ),
        )

        Spacer(modifier = Modifier.height(20.dp))
        PasswordInputField(
            state = InputState(
                label = "Password",
                value = password,
                onValueChanged = { password = it },
                placeholder = "Enter your password",
            )
        )
        Spacer(modifier = Modifier.height(40.dp))

        AppButton(
            buttonText = "Login",
        ) {
            navigateToDashBoard()
            //   login(navigateToDashBoard, navigateToEmailVerification)
        }

        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Forgot Password",
            modifier = Modifier.clickable {
                //  navigateToForgotPassword()
            })

        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Don't have an Account? Register",
            modifier = Modifier.clickable {
                navigateToSignUp()
            })
    }
}


@Composable
@Preview(showBackground = true)
fun LoginScreenPreview() {
   OneCardTheme {
       LoginScreen()
   }
}
