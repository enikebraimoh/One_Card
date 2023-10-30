package com.hackathon.onecard.util

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hackathon.onecard.R
import com.hackathon.onecard.ui.theme.OneCardTheme

@Composable
fun QuickAction(
    color: Color = MaterialTheme.colors.secondary,
    icon: ImageVector = Icons.Default.Visibility
) {
    Box(
        modifier = Modifier
            .clip(MaterialTheme.shapes.small)
            .padding(20.dp)
            .background(color.copy(alpha = 0.2f), CircleShape)
    ) {
        Icon(
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.Center),
            imageVector = icon, contentDescription = null,
            tint = color
        )
    }
}

@Composable
fun DashboardItem(
    icon: ImageVector =  ImageVector.vectorResource(id = R.drawable.convert_icon),
    title: String = "Free Wifi",
    description: String = "",
    color: Color = MaterialTheme.colors.primary,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Column(
        modifier.padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .clip(MaterialTheme.shapes.small)
                .background(color.copy(alpha = 0.2f), CircleShape)
        ) {
            Icon(
                modifier = Modifier
                    .padding(25.dp)
                    .size(20.dp)
                    .align(Alignment.Center),
                imageVector = icon, contentDescription = null,
                tint = color
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.button .copy(
                fontSize = 12.sp,
                color = color,
                fontWeight = FontWeight.Normal
            ),
        )
        /*Text(
            text = description,
            style = MaterialTheme.typography.body2.copy(
                color = Color(0xFF8A8A8A),
                fontSize = 11.sp
            )
        )*/
    }

}

@Preview(showBackground = true)
@Composable
fun QuickActionPreview() {
    OneCardTheme {
        QuickAction()
    }
}

@Preview(showBackground = true)
@Composable
fun QuickAction2Preview() {
    OneCardTheme {
        DashboardItem()
    }
}
