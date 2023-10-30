package com.hackathon.onecard.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MarkerInfoWindowContent
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import com.hackathon.onecard.R
import com.hackathon.onecard.login.LoginScreen
import com.hackathon.onecard.ui.theme.OneCardTheme
import com.hackathon.onecard.util.AgentView
import com.hackathon.onecard.util.AppButton
import com.hackathon.onecard.util.BottomBarScreen
import com.hackathon.onecard.util.InputField
import com.hackathon.onecard.util.InputState
import com.hackathon.onecard.util.PasswordInputField
import com.hackathon.onecard.util.Destinations
import com.hackathon.onecard.util.NavigationAppBar
import kotlinx.coroutines.launch

fun NavGraphBuilder.mapScreen(
    navigateBack: () -> Unit,
) {
    composable(route = Destinations.Map.route) {
        MapScreen(
            navigateBack = navigateBack,
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable()
fun MapScreen(
    navigateBack: () -> Unit,
) {
    val umar = LatLng(9.15, 7.35)
    val farhan = LatLng(8.15, 6.35)
//    val enike = LatLng(8.15, 2.35)
    val tolani = LatLng(9.15, 6.35)
    val enike = LatLng(10.15, 6.35)
    val umarMarker = rememberMarkerState(position = umar)
    val farhanMarker = rememberMarkerState(position = farhan)
    val enikeMarker = rememberMarkerState(position = enike)
    val tolaniMarker = rememberMarkerState(position = tolani)

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )
    val coroutineScope = rememberCoroutineScope()


    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(umar, 7f)
    }

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            AgentView(
                agentName = "Umar Zakari",
                agentNumber = "+2348177665567",
                agentDistance = "5km away",
            )
            AgentView(
                agentName = "Enike Braimoh",
                agentNumber = "+2348177665567",
                agentDistance = "10km away",
            )
            AgentView(
                agentName = "Farhan Nasrudeen",
                agentNumber = "+2348177665567",
                agentDistance = "3km away",
            )
            AgentView(
                agentName = "Daniel Tolani",
                agentNumber = "+2348177665567",
                agentDistance = "5km away",
            )
            AgentView(
                agentName = "John doe",
                agentNumber = "+2348177665567",
                agentDistance = "2km away",
            )
        }, sheetPeekHeight = 200.dp
    ) {
        Column(
            Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NavigationAppBar(navigation = Icons.Default.ArrowBack to navigateBack)
            Spacer(modifier = Modifier.height(10.dp))
            Box(
                contentAlignment = Alignment.TopStart,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            ) {
                Text(
                    text = "Nearby Agents",
                    style = MaterialTheme.typography.h3,
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                GoogleMap(
                    modifier = Modifier.fillMaxSize(),
                    cameraPositionState = cameraPositionState
                ) {
                    MarkerInfoWindowContent(
                        state = umarMarker,
                        title = "directions to Agent(Umar)",
                        icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE),
                    ) {
                        Text(it.title ?: "Title", color = MaterialTheme.colors.primary)
                    }

                    MarkerInfoWindowContent(
                        state = farhanMarker,
                        title = "directions to Agent(farhan)",
                        icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE),
                    ) {
                        Text(it.title ?: "Title", color = MaterialTheme.colors.primary)
                    }

                    MarkerInfoWindowContent(
                        state = enikeMarker,
                        title = "directions to Agent(Enike)",
                        icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE),
                    ) {
                        Text(it.title ?: "Title", color = MaterialTheme.colors.primary)
                    }
                    MarkerInfoWindowContent(
                        state = tolaniMarker,
                        title = "directions to Agent(tolani)",
                        icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE),
                    ) {
                        Text(it.title ?: "Title", color = MaterialTheme.colors.primary)
                    }
                }
            }
        }

    }
}


@Composable
@Preview(showBackground = true)
fun MapScreenPreview() {
    OneCardTheme {
        MapScreen(
            navigateBack = {}
        )
    }
}
