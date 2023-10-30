package com.hackathon.onecard.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.hackathon.onecard.R

private val AirBnbCereal = FontFamily(
    Font(R.font.airbnb_cereal_lt, FontWeight.Normal),
    Font(R.font.airbnb_cereal_md, FontWeight.Medium),
    Font(R.font.airbnb_cereal_xbd, FontWeight.Bold)
)

// Set of Material typography styles to start with
val Typography = Typography(
    h3 = TextStyle(
        fontFamily = AirBnbCereal,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),
    h4 = TextStyle(
        fontFamily = AirBnbCereal,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    body1 = TextStyle(
        fontFamily = AirBnbCereal,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    button = TextStyle(
        fontFamily = AirBnbCereal,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ),
    body2 = TextStyle(
        fontFamily = AirBnbCereal,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = AirBnbCereal,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    )
)