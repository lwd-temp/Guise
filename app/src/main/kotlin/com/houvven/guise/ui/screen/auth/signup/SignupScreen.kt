package com.houvven.guise.ui.screen.auth.signup

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.houvven.guise.R
import com.houvven.guise.ui.LocalNavController
import com.houvven.guise.ui.screen.auth.PasswordTextField
import com.houvven.guise.ui.screen.auth.UsernameTextField
import com.houvven.guise.ui.screen.auth.WelcomeTitle

@Composable
fun SignupScreen() {
    val viewModel = hiltViewModel<SignupViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val navController = LocalNavController.current
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column {
            WelcomeTitle(
                title = stringResource(id = R.string.sign_up),
                subTitle = stringResource(id = R.string.sign_up_welcome)
            )
            Spacer(modifier = Modifier.height(50.dp))

            UsernameTextField(
                value = state.username,
                onValueChange = { viewModel.sendIntent(SignupIntent.UsernameChange(it)) },
                label = stringResource(id = R.string.username),
            )
            PasswordTextField(
                value = state.password,
                onValueChange = { viewModel.sendIntent(SignupIntent.PasswordChange(it)) },
                label = stringResource(id = R.string.password),
                visiblePassword = state.visiblePassword,
                onTogglePasswordVisibility = { viewModel.sendIntent(SignupIntent.TogglePasswordVisibility) }
            )
            PasswordTextField(
                value = state.confirmPassword,
                onValueChange = { viewModel.sendIntent(SignupIntent.ConfirmPasswordChange(it)) },
                label = stringResource(id = R.string.confirm_password),
                visiblePassword = state.visibleConfirmPassword,
                onTogglePasswordVisibility = { viewModel.sendIntent(SignupIntent.ToggleConfirmPasswordVisibility) }
            )
            Spacer(modifier = Modifier.height(25.dp))

            Button(
                onClick = { viewModel.sendIntent(SignupIntent.HandleSignup) },
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .align(alignment = Alignment.CenterHorizontally)
            ) {
                Text(text = stringResource(id = R.string.sign_up))
            }

            TextButton(
                onClick = { viewModel.sendIntent(SignupIntent.ToLogin(navController)) },
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(MaterialTheme.typography.bodySmall.toSpanStyle()) {
                            append(stringResource(id = R.string.already_have_an_account))
                            append(" ")
                        }
                        withStyle(
                            MaterialTheme.typography.bodySmall.toSpanStyle()
                                .copy(fontWeight = FontWeight.W500)
                        ) {
                            append(stringResource(id = R.string.login))
                        }
                    }
                )
            }
        }
    }
}