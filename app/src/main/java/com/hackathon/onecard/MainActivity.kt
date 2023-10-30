package com.hackathon.onecard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hackathon.onecard.cards.cardsScreen
import com.hackathon.onecard.cards.mapScreen
import com.hackathon.onecard.dashboard.DashboardScreen
import com.hackathon.onecard.login.loginScreen
import com.hackathon.onecard.signup.signUpRoute
import com.hackathon.onecard.ui.theme.OneCardTheme
import com.hackathon.onecard.util.Destinations

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navHostController = rememberNavController()
            OneCardTheme {
                RootNavigationGraph(
                    startDestination = Destinations.Login.route,
                    navController = navHostController
                )

            }
        }
    }
}

@Composable
fun RootNavigationGraph(
    startDestination: String,
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = startDestination) {
        loginScreen(
            navigateToDashBoard = {
                navController.navigate(Destinations.Dashboard.route)
            },
            navigateToSignUp = {
                navController.navigate(Destinations.SignUp.route)
            }
        )
        signUpRoute(
            navigateToLogin = {
                navController.navigate(Destinations.Login.route)
            },
            navigateBack = {
                navController.popBackStack()
            },
            navigateToEmailVerification = {
                navController.navigate(Destinations.EmailVerification.route)
            }
        )
        mapScreen(
            navigateBack = {
                navController.popBackStack()
            }
        )
        cardsScreen(
            navigateToMaps = {
                navController.navigate(Destinations.Map.route)
            },
        )
        composable(
            route = Destinations.Dashboard.route
        ){
            DashboardScreen(
                navigateToMaps = {
                    navController.navigate(Destinations.PickCard.route)
                }
            )
        }
    }
}
