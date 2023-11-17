package com.houvven.guise.ui.screen.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.houvven.guise.ui.style.TransparentTextFieldColor


@Composable
internal fun UsernameTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String
) {
    val colors = TransparentTextFieldColor()
    TextField(
        value = value,
        onValueChange = onValueChange,
        colors = colors,
        label = { Text(text = label) },
        singleLine = true,
        modifier = Modifier.width(280.dp)
    )
}

@Composable
internal fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    visiblePassword: Boolean,
    onTogglePasswordVisibility: () -> Unit
) {
    val colors = TransparentTextFieldColor()
    val visualTransformation =
        if (visiblePassword) VisualTransformation.None else PasswordVisualTransformation()
    val icon = if (visiblePassword) Icons.Filled.VisibilityOff else Icons.Filled.Visibility
    TextField(
        value = value,
        onValueChange = onValueChange,
        colors = colors,
        label = { Text(text = label) },
        singleLine = true,
        modifier = Modifier.width(280.dp),
        visualTransformation = visualTransformation,
        trailingIcon = {
            IconToggleButton(
                checked = visiblePassword,
                onCheckedChange = { onTogglePasswordVisibility() }
            ) {
                Icon(imageVector = icon, contentDescription = null)
            }
        }
    )
}

@Composable
internal fun WelcomeTitle(title: String, subTitle: String) {
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.displayMedium,
            letterSpacing = 2.sp
        )
        Text(
            text = subTitle,
            style = MaterialTheme.typography.displaySmall,
            fontSize = MaterialTheme.typography.displaySmall.fontSize * 0.75f,
            letterSpacing = (-0.5).sp,
        )
    }
}