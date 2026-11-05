package com.example.hiredude.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hiredude.theme.BrandPurpleDark
import com.example.hiredude.theme.BrandPurpleLight
import com.example.hiredude.theme.PrimaryContainer
import com.example.hiredude.theme.SecondaryContainer
import com.example.hiredude.theme.TertiaryFixed
import kotlinx.coroutines.delay

@Composable
fun SplashContent(
    onContinue: () -> Unit
) {
    var isConnected by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(1400)
        isConnected = true
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(BrandPurpleDark, BrandPurpleLight)
                )
            )
            .padding(24.dp)
    ) {
        // Top Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .statusBarsPadding()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.15f))
                    .padding(horizontal = 12.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .clip(CircleShape)
                        .background(TertiaryFixed)
                )
                Text(
                    text = "EST. 2024",
                    color = Color.White,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.5.sp
                )
            }

            Text(
                text = "VERIFIED RECRUITMENT",
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = 0.8.sp
            )
        }

        // Center Content
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Squircle Brand Mark
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier
                            .width(20.dp)
                            .height(10.dp)
                            .clip(RoundedCornerShape(topStart = 2.dp, topEnd = 2.dp))
                            .background(PrimaryContainer)
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Box(
                        modifier = Modifier
                            .width(16.dp)
                            .height(22.dp)
                            .clip(RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp))
                            .background(PrimaryContainer)
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Title
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "HireDude",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Box(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(SecondaryContainer)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Engineering, product, and data careers from alumni networks.",
                color = Color.White.copy(alpha = 0.85f),
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                lineHeight = 20.sp,
                modifier = Modifier.widthIn(max = 280.dp)
            )

            Spacer(modifier = Modifier.height(28.dp))

            // Status Pill
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.White)
                    .clickable { onContinue() }
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (!isConnected) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(16.dp),
                        color = PrimaryContainer,
                        strokeWidth = 2.dp
                    )
                    Text(
                        text = "Syncing Roles...",
                        color = PrimaryContainer,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                } else {
                    IconCheck(
                        tint = PrimaryContainer,
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = "Network Connected",
                        color = PrimaryContainer,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        // Bottom Footer
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Box(
                modifier = Modifier
                    .width(32.dp)
                    .height(2.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.2f))
            )

            Text(
                text = "Built for Indian Students & Engineers",
                color = Color(0xFFD7B0DB),
                fontSize = 12.sp,
                textAlign = TextAlign.Center
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Bangalore", color = Color.White.copy(alpha = 0.5f), fontSize = 11.sp)
                Text("•", color = Color.White.copy(alpha = 0.5f), fontSize = 11.sp)
                Text("Delhi NCR", color = Color.White.copy(alpha = 0.5f), fontSize = 11.sp)
                Text("•", color = Color.White.copy(alpha = 0.5f), fontSize = 11.sp)
                Text("Hyderabad", color = Color.White.copy(alpha = 0.5f), fontSize = 11.sp)
            }
        }
    }
}
