package com.hackathon.onecard.util

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hackathon.onecard.R

@Composable
fun Card(
    modifier: Modifier = Modifier,
    cardBalance: String,
    cardNumber: String,
    cvv: String,
    expiry: String,
    cardHolderName: String,
    color: Color = Color(0xFF1E1E1E),
) {

    var rotated by remember { mutableStateOf(false) }

    val rotation by animateFloatAsState(
        targetValue = if (rotated) 180f else 0f,
        animationSpec = tween(500)
    )

    val animateFront by animateFloatAsState(
        targetValue = if (!rotated) 1f else 0f,
        animationSpec = tween(500)
    )

    val animateBack by animateFloatAsState(
        targetValue = if (rotated) 1f else 0f,
        animationSpec = tween(500)
    )

    androidx.compose.material.Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .padding(10.dp)
            .graphicsLayer {
                rotationY = rotation
                cameraDistance = 8 * density
            }
            .clickable {
                rotated = !rotated
            },
        backgroundColor = color,
        shape = RoundedCornerShape(14.dp),
        elevation = 4.dp,
    ) {
        if (!rotated) {
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(20.dp),
            ) {

                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                    Icon(
                        painter = painterResource(R.drawable.convert_icon),
                        contentDescription = "test",
                        modifier = Modifier
                            .padding(top = 6.dp, bottom = 6.dp, end = 20.dp)
                            .graphicsLayer {
                                alpha = animateFront
                            },
                    )
                    Spacer(modifier = Modifier.weight(1f))
                }
                Text(
                    text = cardNumber,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .graphicsLayer {
                            alpha = animateFront
                        },
                    fontSize = 25.sp
                )

                Spacer(modifier = Modifier.height(20.dp))

                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                    Column(horizontalAlignment = Alignment.Start) {
                        Text(
                            text = "Card Holder",
                            style = MaterialTheme.typography.body1.copy(
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold,
                            ),
                            modifier = Modifier
                                .graphicsLayer {
                                    alpha = animateFront
                                }
                        )
                        Text(
                            text = cardHolderName,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .graphicsLayer {
                                    alpha = animateFront
                                }
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Column(horizontalAlignment = Alignment.Start) {
                        Text(
                            text = "Balance",
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .graphicsLayer {
                                    alpha = animateFront
                                }
                        )
                        val balance = cardBalance

                    }

                }
            }
        } else {
            Column(
                modifier = Modifier
                    .background(color)
                    .padding(top = 20.dp),
            ) {

                Divider(
                    modifier = Modifier
                        .graphicsLayer {
                            alpha = animateBack
                        },
                    color = Color.Black, thickness = 50.dp
                )

                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                ) {
                    Text(
                        text = "VALID THRU",
                        color = Color.Gray,
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .graphicsLayer {
                                alpha = animateBack
                                rotationY = rotation
                            }
                    )
                    Text(
                        text = expiry,
                        color = Color.White,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .graphicsLayer {
                                alpha = animateBack
                                rotationY = rotation
                            }
                    )

                }

                Text(
                    text = "123",
                    color = Color.Black,
                    modifier = Modifier
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                        .background(Color.White)
                        .fillMaxWidth()
                        .graphicsLayer {
                            alpha = animateBack
                            rotationY = rotation
                        }
                        .padding(10.dp),

                    fontSize = 15.sp,
                    textAlign = TextAlign.End
                )

                Text(
                    text = "Biller Virtual Cards are not a bank account and are not insured by the FDIC.",
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .graphicsLayer {
                            alpha = animateBack
                            rotationY = rotation
                        }
                        .padding(5.dp),
                    fontWeight = FontWeight.Thin,
                    fontSize = 10.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}