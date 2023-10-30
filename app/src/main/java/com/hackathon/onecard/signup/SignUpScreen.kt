package com.hackathon.onecard.signup

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hackathon.onecard.ui.theme.OneCardTheme
import com.hackathon.onecard.util.AppButton
import com.hackathon.onecard.util.Destinations
import com.hackathon.onecard.util.InputField
import com.hackathon.onecard.util.InputState
import com.hackathon.onecard.util.NavigationAppBar
import com.hackathon.onecard.util.PasswordInputField
import kotlinx.coroutines.launch

fun NavGraphBuilder.signUpRoute(
    navigateToLogin: () -> Unit,
    navigateBack: () -> Unit,
    navigateToEmailVerification: (email: String) -> Unit
) {
    composable(Destinations.SignUp.route) {

        SignUpScreen(
            navigateToLogin = navigateToLogin,
            navigateBack = navigateBack,
            success = navigateToEmailVerification
        )
    }
}

@Composable
internal fun SignUpScreen(
    navigateBack: () -> Unit,
    navigateToLogin: () -> Unit,
    success: (email: String) -> Unit
) {

    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    var firstName by remember { mutableStateOf("Farhan") }
    var lastName by remember { mutableStateOf("nasirudeen") }
    var email by remember { mutableStateOf("farhan@gmail.com") }
    var phoneNumber by remember { mutableStateOf("+2348140252210") }
    var password by remember { mutableStateOf("123456") }
    var isLoading by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            Column {
                NavigationAppBar(
                    navigation = Icons.Default.KeyboardArrowLeft to { navigateBack() }
                )
                AnimatedVisibility(visible = false) {
                    LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                }
            }
        },
        scaffoldState = scaffoldState,
        content = { scaffoldPadding ->
            Column(
                Modifier
                    .padding(scaffoldPadding)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                Box(
                    contentAlignment = Alignment.TopStart,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Register",
                        style = MaterialTheme.typography.h3
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(1f),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        InputField(
                            state = InputState(
                                firstName,
                                enabled = !isLoading,
                                label = "First Name",
                                placeholder = "Enter First Name",
                                onValueChanged = { firstName = it },
                            ),
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Box(modifier = Modifier.weight(1f)) {
                        InputField(
                            state = InputState(
                                value = lastName,
                                enabled = !isLoading,
                                label = "Last Name",
                                placeholder = "Enter Last Name",
                                onValueChanged = { lastName = it },
                            ),
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                InputField(
                    state = InputState(
                        value = email.trim(),
                        enabled = !isLoading,
                        label = "Email",
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email
                        ),
                        placeholder = "Enter Email",
                        onValueChanged = { email = it },
                    ),
                )

                Spacer(modifier = Modifier.height(20.dp))

                InputField(
                    state = InputState(
                        value = phoneNumber,
                        enabled = !isLoading,
                        label = "Phone Number",
                        placeholder = "Enter Phone Number",
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Phone
                        ),
                        onValueChanged = {
                            if (it.length <= 11) {
                                phoneNumber = it
                            }
                        },
                    ),
                )

                Spacer(modifier = Modifier.height(20.dp))
                PasswordInputField(
                    state = InputState(
                        value = password,
                        enabled = !isLoading,
                        label = "Password",
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password
                        ),
                        onValueChanged = { password = it },
                        placeholder = "Enter your password"
                    )
                )
                Spacer(modifier = Modifier.height(40.dp))

                AppButton(
                    buttonText = "Continue",
                    enabled = !isLoading
                ) {
                   // signUp(success)
                }

                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Already have an Account? Login",
                    modifier = Modifier.clickable {
                        navigateToLogin()
                    }
                )
            }
        }
    )
}

@Preview
@Composable
internal fun SignUpScreenPreview() = OneCardTheme {
    SignUpScreen(
        navigateBack = {

        },
        navigateToLogin = {

        },
        success = {}
    )
}

