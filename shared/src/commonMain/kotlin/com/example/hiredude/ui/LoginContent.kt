package com.example.hiredude.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hiredude.model.AuthLoginState
import com.example.hiredude.theme.*

@Preview
@Composable
fun previewFun() {
    LoginContent(
        onLoginSuccess = {}
    )
}
@Composable
fun LoginContent(
    onLoginSuccess: () -> Unit
) {
    var loginState by remember { mutableStateOf(AuthLoginState.DEFAULT) }
    var email by remember { mutableStateOf("ananya@nitt.edu") }
    var password by remember { mutableStateOf("••••••••••••") }
    var isPasswordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Surface)
            .padding(horizontal = 20.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        // Interactive State Pill Bar
        /*Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "INTERACTIVE STATES",
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold,
                color = Outline,
                letterSpacing = 0.8.sp
            )
            Text(
                text = "Screen 02 / Auth",
                fontSize = 12.sp,
                color = Secondary,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // State Tab Buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(SurfaceContainer)
                .padding(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            val tabs = listOf(
                AuthLoginState.DEFAULT to "Default",
                AuthLoginState.ERROR to "Error State",
                AuthLoginState.LOADING to "Loading State"
            )

            tabs.forEach { (state, label) ->
                val isSelected = loginState == state
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(36.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(if (isSelected) SurfaceContainerLowest else Color.Transparent)
                        .clickable {
                            loginState = state
                            if (state == AuthLoginState.ERROR) {
                                password = "invalidPass123"
                            } else {
                                password = "••••••••••••"
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = label,
                        fontSize = 12.sp,
                        fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Medium,
                        color = if (isSelected) Primary else OnSurfaceVariant
                    )
                }
            }
        }*/

        Spacer(modifier = Modifier.height(24.dp))

        // Brand Header
        Column {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(PrimaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Text("HD", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Log in to HireDude",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = OnSurface,
                letterSpacing = (-0.5).sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Enter your college or personal email to access curated student roles",
                fontSize = 14.sp,
                color = OnSurfaceVariant,
                lineHeight = 20.sp
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Auth Card
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            color = SurfaceContainerLowest,
            shadowElevation = 2.dp
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Email Field
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text(
                        text = "Email",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = if (loginState == AuthLoginState.ERROR) Error else OnSurface
                    )

                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        modifier = Modifier.fillMaxWidth(),
                        enabled = loginState != AuthLoginState.LOADING,
                        singleLine = true,
                        isError = loginState == AuthLoginState.ERROR,
                        shape = RoundedCornerShape(10.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Primary,
                            unfocusedBorderColor = SurfaceContainerHigh,
                            errorBorderColor = Error,
                            disabledContainerColor = SurfaceContainer
                        )
                    )
                }

                // Password Field
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Password",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = if (loginState == AuthLoginState.ERROR) Error else OnSurface
                        )
                        Text(
                            text = "Forgot password?",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium,
                            color = Primary,
                            modifier = Modifier.clickable { }
                        )
                    }

                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        modifier = Modifier.fillMaxWidth(),
                        enabled = loginState != AuthLoginState.LOADING,
                        singleLine = true,
                        isError = loginState == AuthLoginState.ERROR,
                        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        trailingIcon = {
                            Text(
                                text = if (isPasswordVisible) "Hide" else "Show",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium,
                                color = if (loginState == AuthLoginState.ERROR) Error else Outline,
                                modifier = Modifier.clickable { isPasswordVisible = !isPasswordVisible }
                            )
                        },
                        shape = RoundedCornerShape(10.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Primary,
                            unfocusedBorderColor = SurfaceContainerHigh,
                            errorBorderColor = Error,
                            disabledContainerColor = SurfaceContainer
                        )
                    )

                    if (loginState == AuthLoginState.ERROR) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconWarning(
                                tint = Error,
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = "Wrong email or password. Check credentials or reset.",
                                color = Error,
                                fontSize = 12.sp
                            )
                        }
                    }
                }

                // Submit Button
                Button(
                    onClick = {
                        if (loginState != AuthLoginState.LOADING) {
                            onLoginSuccess()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(44.dp),
                    enabled = loginState != AuthLoginState.LOADING,
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrimaryContainer,
                        contentColor = OnPrimary,
                        disabledContainerColor = PrimaryContainer.copy(alpha = 0.6f)
                    )
                ) {
                    if (loginState == AuthLoginState.LOADING) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(18.dp),
                            color = OnPrimary,
                            strokeWidth = 2.dp
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Logging in...", fontSize = 14.sp, fontWeight = FontWeight.Medium)
                    } else {
                        Text("Log in", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                    }
                }

                // Divider
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    HorizontalDivider(
                        modifier = Modifier.weight(1f),
                        color = SurfaceContainerHigh
                    )
                    Text(
                        text = "OR",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Outline
                    )
                    HorizontalDivider(
                        modifier = Modifier.weight(1f),
                        color = SurfaceContainerHigh
                    )
                }

                // Google Login Button
                OutlinedButton(
                    onClick = { onLoginSuccess() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(44.dp),
                    enabled = loginState != AuthLoginState.LOADING,
                    shape = RoundedCornerShape(10.dp),
                    border = ButtonDefaults.outlinedButtonBorder.copy(
                        brush = androidx.compose.ui.graphics.SolidColor(SurfaceContainerHigh)
                    )
                ) {
                    Text(
                        text = "Continue with Google",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = OnSurface
                    )
                }

                // Footer Link
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Don't have an account? ",
                        fontSize = 13.sp,
                        color = OnSurfaceVariant
                    )
                    Text(
                        text = "Create an account",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Primary,
                        modifier = Modifier.clickable { onLoginSuccess() }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Campus Email Detection Badge
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            color = SurfaceContainerLowest,
            shadowElevation = 1.dp
        ) {
            Row(
                modifier = Modifier.padding(14.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(SurfaceContainer),
                    contentAlignment = Alignment.Center
                ) {
                    IconCheck(
                        tint = Primary,
                        modifier = Modifier.size(20.dp)
                    )
                }

                Column {
                    Text(
                        text = "Campus Email Detection Active",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = OnSurface
                    )
                    Text(
                        text = "Instant badge unlocks for accredited NIT, IIT, and IIIT domains.",
                        fontSize = 12.sp,
                        color = OnSurfaceVariant
                    )
                }
            }
        }
    }
}
