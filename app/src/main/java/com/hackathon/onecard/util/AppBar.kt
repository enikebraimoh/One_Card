package com.hackathon.onecard.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hackathon.onecard.R
import com.hackathon.onecard.ui.theme.OneCardTheme

@Composable
fun DefaultAppBar(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit,
    icon: @Composable() (() -> Unit?)? = null,
    navigation: Pair<ImageVector, () -> Unit>? = null,
) {
    TopAppBar(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.background,
        content = {
            navigation?.let {
                IconButton(
                    onClick = it.second,
                    content = {
                        Icon(it.first, contentDescription = null)
                    }
                )
            }
            Box(
                modifier = Modifier.weight(1f),
                content = {
                    title()
                }
            )
            if (icon != null) {
                icon()
            }
        },
        elevation = 1.dp,
    )
}

/*@Composable
fun DefaultAppBar(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit,
    icon: ImageVector? = null,
    navigation: Pair<ImageVector, () -> Unit>? = null,
) {
    TopAppBar(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.background,
        content = {
            navigation?.let {
                IconButton(
                    onClick = it.second,
                    content = {
                        Icon(it.first, contentDescription = null)
                    }
                )
            }
            Box(
                modifier = Modifier.weight(1f),
                content = {
                    title()
                }
            )
            IconButton(
                onClick = { },
                content = {
                    icon?.let {
                        Icon(
                            imageVector = it,
                            contentDescription = "Notifications"
                        )
                    }
                }
            )
        },
        elevation = 1.dp,
    )
}*/

@Composable
fun DashboardAppBar(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit,
    icon: @Composable () -> Unit,
    notificationsClicked: () -> Unit,
) {
    TopAppBar(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.background,
        contentColor = Color.Transparent,
        content = {
            IconButton(
                onClick = { },
                content = icon
            )
            Spacer(modifier = Modifier.width(10.dp))
            Box(
                modifier = Modifier.weight(1f),
                content = {
                    title()
                }
            )
            IconButton(
                onClick = notificationsClicked,
                content = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.notif_icon),
                        contentDescription = "Notifications"
                    )
                }
            )
        },
        elevation = 2.dp,
    )
}


@Composable
fun NavigationAppBar(
    modifier: Modifier = Modifier,
    navigation: Pair<ImageVector, () -> Unit>,
) {
    TopAppBar(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.background,
        content = {
            Column(modifier) {
                IconButton(
                    onClick = navigation.second,
                    content = {
                        Icon(navigation.first, contentDescription = "back")
                    }
                )
            }
        },
        elevation = 2.dp,
    )
}

@Preview
@Composable
fun AppBarPreview() = OneCardTheme {
    val icon = ImageVector.vectorResource(id = R.drawable.notif_icon)
    DefaultAppBar(
        title = { Text("Hello Enike") },

        icon = {
            IconButton(
                onClick = { },
                content = {
                    Row {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.notif_icon),
                            contentDescription = "Notifications"
                        )
                    }
                }
            )
        }
    )
}


@Preview
@Composable
fun DashboardAppBarPreview() = OneCardTheme {
    val icon = ImageVector.vectorResource(id = R.drawable.notif_icon)
    DashboardAppBar(
        title = { Text("Hello Enike") },
        icon = {
            Box(
                modifier = Modifier
                    .background(Color.Gray, CircleShape)
                    .size(40.dp)
            )
        },
        notificationsClicked = { }
    )
}

@Preview
@Composable
fun NavigationAppBarPreview() = OneCardTheme {
    val icon = ImageVector.vectorResource(id = R.drawable.notif_icon)
    NavigationAppBar(
        navigation = Icons.Default.ArrowBack to {}
    )
}