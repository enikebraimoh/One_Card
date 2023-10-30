package com.hackathon.onecard.util

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hackathon.onecard.ui.theme.OneCardTheme

@Composable
fun AppButton(
    buttonText: String,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    enabled: Boolean = true,
    color: Color? = null,
    onClick: () -> Unit,
) = Button(
    onClick = { onClick() },
    modifier = modifier.fillMaxWidth(),
    enabled = enabled && !isLoading,
    shape = MaterialTheme.shapes.medium,
    elevation = ButtonDefaults.elevation(defaultElevation = 0.dp),
    colors = ButtonDefaults.buttonColors(backgroundColor = color ?: MaterialTheme.colors.primary),
    content = {
        if (isLoading) Text("...") else Text(text = buttonText, modifier.padding(vertical = 10.dp))
    }
)

@Preview
@Composable
private fun InputFieldPreview() = OneCardTheme(darkTheme = false) {
    Surface {
        AppButton(buttonText = "Login", onClick = {})
    }
}