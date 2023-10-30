package com.hackathon.onecard.dashboard

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.hackathon.onecard.cards.cardsScreen
import com.hackathon.onecard.cards.vitrualCardsRoute
import com.hackathon.onecard.home.homeRoute
import com.hackathon.onecard.settingsScreen
import com.hackathon.onecard.transaction.transactionsScreen
import com.hackathon.onecard.ui.theme.OneCardTheme
import com.hackathon.onecard.util.BottomBarScreen
import com.hackathon.onecard.util.Destinations

@Composable
fun DashboardScreen(
    navController: NavHostController = rememberNavController(),
    navigateToMaps: () -> Unit ,
) {
    val scope = rememberCoroutineScope()

    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) { paddingValues ->
        DashboardNavGraph(
            modifier = Modifier.padding(paddingValues)
                .padding(bottom = 20.dp),
            navController = navController,
            navigateToMaps = navigateToMaps
        )
    }
}


@Composable
fun DashboardNavGraph(
    modifier: Modifier,
    navController: NavHostController,
    navigateToMaps: () -> Unit = {},
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        route = Destinations.Dashboard.route,
        startDestination = BottomBarScreen.Home.route
    ) {
        homeRoute()
        vitrualCardsRoute(
            openPickupCard = navigateToMaps
        )
        transactionsScreen()
        settingsScreen()
    }
}


@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Cards,
        BottomBarScreen.Transaction,
        BottomBarScreen.Settings,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = screens.any { it.route == currentDestination?.route }
    if (bottomBarDestination) {
        BottomNavigation(
            elevation = 0.dp,
            backgroundColor = MaterialTheme.colors.background
        ) {
            screens.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        selectedContentColor = MaterialTheme.colors.primary,
        unselectedContentColor = MaterialTheme.colors.primary.copy(alpha = 0.3f),
        label = {
            Text(
                softWrap = false,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxSize(),
                text = screen.title,
                textAlign = TextAlign.Center,
            )
        },
        icon = {
            Icon(
                imageVector = ImageVector.vectorResource(screen.icon),
                contentDescription = screen.title
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        // unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}

@Preview
@Composable
fun PreviewDashboardScreen() {
  OneCardTheme {
      DashboardScreen(
          navigateToMaps = {}
      )
  }
}