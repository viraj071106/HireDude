package com.example.hiredude.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hiredude.theme.*

@Preview
@Composable
fun AboutScreen() {
    var showContactToast by remember { mutableStateOf(false) }
    var isCopied by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Surface)
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // Alumni Badge & Header
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(SecondaryContainer.copy(alpha = 0.3f))
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    IconVerified(tint = PrimaryContainer, modifier = Modifier.size(14.dp))
                    Text(
                        text = "AN IIT DELHI ALUMNI INITIATIVE",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryContainer,
                        letterSpacing = 0.8.sp
                    )
                }
            }

            Text(
                text = "Your gateway to opportunities",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = OnSurface,
                lineHeight = 30.sp
            )

            Text(
                text = "HireDude bridges ambitious Indian engineering students directly with verified fast-growing tech teams, eliminating ghosting and referral black holes through IIT & NIT alumni networks.",
                fontSize = 14.sp,
                color = OnSurfaceVariant,
                lineHeight = 22.sp
            )
        }

        // Card 1: For Students
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            color = SurfaceContainerLowest,
            shadowElevation = 1.dp
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .clip(CircleShape)
                                .background(Primary)
                        )
                        Text("For students", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = OnSurface)
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(SurfaceContainerLow)
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text("Free Forever", fontSize = 11.sp, fontWeight = FontWeight.Medium, color = OnSurfaceVariant)
                    }
                }

                val studentBenefits = listOf(
                    "Verified campus domain accreditation (IIT, NIT, IIIT, BITS)",
                    "Real stipend transparency and verified recruiter routing",
                    "Guaranteed application review timeline under 72 hours"
                )

                studentBenefits.forEach { benefit ->
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        IconCheck(tint = Primary, modifier = Modifier.size(16.dp))
                        Text(benefit, fontSize = 13.sp, color = OnSurface, lineHeight = 18.sp)
                    }
                }
            }
        }

        // Card 2: For Recruiters (Gradient)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(BrandPurpleDark, BrandPurpleLight)
                    )
                )
                .padding(16.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .clip(CircleShape)
                                .background(TertiaryFixed)
                        )
                        Text("For recruiters", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    }

                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(Color.White.copy(alpha = 0.2f))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text("Priority Access", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    }
                }

                Text("Curated talent from ₹499 per post", fontSize = 12.sp, color = Color.White.copy(alpha = 0.8f))

                val recruiterBenefits = listOf(
                    "Target batch-specific talent (2025/2026 B.Tech/M.Tech)",
                    "Pre-screened coding & design portfolios with verified CGPA",
                    "Direct WhatsApp candidate outreach with 84% response rate"
                )

                recruiterBenefits.forEach { benefit ->
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        IconCheck(tint = TertiaryFixed, modifier = Modifier.size(16.dp))
                        Text(benefit, fontSize = 13.sp, color = Color.White.copy(alpha = 0.95f), lineHeight = 18.sp)
                    }
                }
            }
        }

        // Key Ecosystem Metrics Grid
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            color = SurfaceContainerLowest,
            shadowElevation = 1.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val metrics = listOf(
                    "18,400+" to "Verified students",
                    "420+" to "Hiring startups",
                    "4.2 days" to "Avg. hiring time"
                )

                metrics.forEachIndexed { index, (value, label) ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        Text(value, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = OnSurface)
                        Text(label, fontSize = 11.sp, color = OnSurfaceVariant, textAlign = TextAlign.Center)
                    }

                    if (index < metrics.size - 1) {
                        HorizontalDivider(
                            modifier = Modifier
                                .height(32.dp)
                                .width(1.dp),
                            color = SurfaceContainer
                        )
                    }
                }
            }
        }

        // Contact Button & Toast
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            OutlinedButton(
                onClick = { showContactToast = !showContactToast },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text("✉ Contact us", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = OnSurface)
            }

            if (showContactToast) {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    color = SurfaceContainerHigh
                ) {
                    Row(
                        modifier = Modifier
                            .padding(12.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Write to team@hiredude.in", fontSize = 13.sp, fontWeight = FontWeight.Medium, color = OnSurface)
                        Button(
                            onClick = { isCopied = true },
                            shape = RoundedCornerShape(6.dp),
                            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = SurfaceContainerLowest, contentColor = Primary)
                        ) {
                            Text(if (isCopied) "Copied!" else "Copy", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }

        // Footer Links
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Terms of Service", fontSize = 11.sp, color = OnSurfaceVariant)
                Text("•", fontSize = 10.sp, color = Outline)
                Text("Privacy Policy", fontSize = 11.sp, color = OnSurfaceVariant)
                Text("•", fontSize = 10.sp, color = Outline)
                Text("Placement Cell Inquiries", fontSize = 11.sp, color = OnSurfaceVariant)
            }

            Text(
                text = "© 2025 HireDude Technologies Private Limited. Hauz Khas, New Delhi.",
                fontSize = 11.sp,
                color = Outline,
                textAlign = TextAlign.Center
            )
        }
    }
}
