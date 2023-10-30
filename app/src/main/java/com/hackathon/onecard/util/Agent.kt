package com.hackathon.onecard.util

import android.graphics.BitmapFactory
import android.graphics.drawable.VectorDrawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForwardIos
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hackathon.onecard.R
import com.hackathon.onecard.ui.theme.OneCardTheme

@Composable
fun AgentView(
    agentName : String = "Umar Zakari",
    agentNumber : String = "+2348177665567",
    agentDistance : String = "12km away",
) {
    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
       Row {
           Box(
               modifier = Modifier
                   .background(
                       color = Color.Gray,
                       shape = CircleShape
                   )
                   .size(60.dp)
           )
           Spacer(modifier = Modifier.width(10.dp))
           Column {
               Text(
                   text = agentName,
                   style = MaterialTheme.typography.h6
               )

               Text(
                   text = agentNumber,
                   style = MaterialTheme.typography.body1
               )

               Text(
                   text = agentDistance,
                   style = MaterialTheme.typography.body1
               )
           }
       }
        Row {
            Image(
                modifier = Modifier.padding(end = 15.dp),
                imageVector = ImageVector.vectorResource(id = R.drawable.call),
                contentDescription = "ic_document_filter"
            )
            Image(
                modifier = Modifier.padding(end = 15.dp),
                imageVector = ImageVector.vectorResource(id = R.drawable.direction),
                contentDescription = "ic_document_filter"
            )
        }
    }

}

@Composable
@Preview
fun AgentViewPreview() {
   OneCardTheme {
      Surface {
          AgentView()
      }
   }
}