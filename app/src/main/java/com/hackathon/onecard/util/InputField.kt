package com.hackathon.onecard.util

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hackathon.onecard.ui.theme.OneCardTheme

@Composable
fun Input(
    state: InputState,
    modifier: Modifier = Modifier,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    textStyle: TextStyle = LocalTextStyle.current
) = with(state) {

    Column {
        Text(
            text = label,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium.copy(
                color = Color(0xFF7B818E)
            )
        )
        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(
            value = value,
            onValueChange = onValueChanged,
            modifier = modifier.fillMaxWidth(),
            maxLines = maxLines,
            enabled = enabled,
            readOnly = readOnly,
            isError = isError,
            singleLine = singleLine,
            visualTransformation = visualTransformation,
            textStyle = textStyle,
            interactionSource = interactionSource,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            placeholder = {
                if (placeholder != null) {
                    Text(text = placeholder)
                }
            },
            keyboardActions = keyboardActions,
            keyboardOptions = keyboardOptions,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colorScheme.secondary
            )
        )
    }
}

@Composable
fun InputField(
    modifier: Modifier = Modifier,
    state: InputState,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    val interactionSource = remember { MutableInteractionSource() }

    Input(
        state = state,
        modifier = modifier,
        leadingIcon = leadingIcon,
        interactionSource = interactionSource,
        trailingIcon = trailingIcon
    )

}

@Composable
fun PasswordInputField(
    modifier: Modifier = Modifier,
    state: InputState,
    leadingIcon: @Composable (() -> Unit)? = null
) = with(state) {

    var passwordVisible by remember {
        mutableStateOf(false)
    }

    Input(
        state = state.copy(
            visualTransformation = if (passwordVisible) {
                VisualTransformation.None
            } else PasswordVisualTransformation()
        ),
        modifier = modifier,
        leadingIcon = leadingIcon,
        trailingIcon = {
            if (passwordVisible) {
                Icon(
                    modifier = modifier.clickable {
                        passwordVisible = !passwordVisible
                    },
                    imageVector = Icons.Outlined.Visibility,
                    contentDescription = null
                )
            } else Icon(
                modifier = modifier.clickable {
                    passwordVisible = !passwordVisible
                },
                imageVector = Icons.Outlined.VisibilityOff,
                contentDescription = null
            )
        }
    )
}

enum class TextInputType {
    PASSWORD,
    EMAIL,
    PRICE,
    TEXT,
    NUMBER,
    PHONE,
    DROPDOWN,
    DOB,
    TransDate
}

data class InputState(
    val value: String,
    val onValueChanged: (String) -> Unit = {},
    val label: String = "Default Label",
    val color: Color? = null,
    val error: String? = null,
    val isError: Boolean = false,
    val keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    val keyboardActions: KeyboardActions = KeyboardActions(),
    val readOnly: Boolean = false,
    val enabled: Boolean = true,
    val singleLine: Boolean = true,
    val required: Boolean = false,
    val visualTransformation: VisualTransformation = VisualTransformation.None,
    val placeholder: String? = null,
    val maxLines: Int = Int.MAX_VALUE
)

@Preview
@Composable
private fun InputFieldPreview() = OneCardTheme(darkTheme = false) {
    Surface {
        Input(
            state = InputState(value = "Hello world")
        )
    }
}

@Preview
@Composable
private fun PasswordFieldPreview() = OneCardTheme(darkTheme = false) {
    Surface {
        PasswordInputField(
            state = InputState(value = "Hello world")
        )
    }
}
