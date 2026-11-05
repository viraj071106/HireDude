package com.example.hiredude.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hiredude.model.UserProfile
import com.example.hiredude.theme.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Preview
@Composable
fun previewFun() {
    ProfileScreen(
        userProfile = UserProfile(
            name = "John Doe",
            email = "john.doe@example.com",
            phone = "123-456-7890",
            portfolioUrl = "https://example.com/portfolio",
            collegeShort = "XYZ University",
            degreeBatch = "2023"
        ),
        onProfileUpdate = {},
        onUpgradeToPro = {},
        onLogout = {}
    )
}

@Composable
fun ProfileScreen(
    userProfile: UserProfile,
    onProfileUpdate: (UserProfile) -> Unit,
    onUpgradeToPro: () -> Unit,
    onLogout: () -> Unit
) {
    var name by remember { mutableStateOf(userProfile.name) }
    var email by remember { mutableStateOf(userProfile.email) }
    var phone by remember { mutableStateOf(userProfile.phone) }
    var portfolioUrl by remember { mutableStateOf(userProfile.portfolioUrl) }

    var isSaving by remember { mutableStateOf(false) }
    var showSavedNotification by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Surface)
            .statusBarsPadding(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Top Profile Card
        item {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                color = SurfaceContainerLowest,
                shadowElevation = 1.dp
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        Row(
                            modifier = Modifier.weight(1f),
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(56.dp)
                                    .clip(CircleShape)
                                    .background(PrimaryContainer),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = userProfile.name.take(1),
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }

                            Column {
                                Text(
                                    text = userProfile.name,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = OnSurface,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Text(
                                    text = "${userProfile.collegeShort} · ${userProfile.degreeBatch}",
                                    fontSize = 13.sp,
                                    color = OnSurfaceVariant,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }

                        // Clean Member Badge
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(if (userProfile.isProMember) PrimaryContainer else SurfaceContainer)
                                .padding(horizontal = 10.dp, vertical = 6.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(6.dp)
                                        .clip(CircleShape)
                                        .background(if (userProfile.isProMember) Color.White else Primary)
                                )
                                Text(
                                    text = if (userProfile.isProMember) "Pro Member" else "Free Plan",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (userProfile.isProMember) Color.White else OnSurface
                                )
                            }
                        }
                    }

                    // Verified College Talent Network Banner
                    /*Box(
                         modifier = Modifier
                             .fillMaxWidth()
                             .clip(RoundedCornerShape(10.dp))
                             .background(Primary)
                             .padding(12.dp)
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
                                 IconVerified(tint = Color.White, modifier = Modifier.size(18.dp))
                                 Column {
                                     Text("VERIFIED CAMPUS NETWORK", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color.White.copy(alpha = 0.9f))
                                     Text("Tiruchirappalli · Tier 1 Engineering", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
                                 }
                             }

                             Box(
                                 modifier = Modifier
                                     .clip(CircleShape)
                                     .background(Color.White.copy(alpha = 0.2f))
                                     .padding(horizontal = 8.dp, vertical = 2.dp)
                             ) {
                                 Text("Active", fontSize = 10.sp, color = Color.White, fontWeight = FontWeight.Bold)
                             }
                         }
                    }*/

                    // Profile Completion Bar
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(SurfaceContainerLow)
                            .padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text("${userProfile.completionPercentage}% ", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Primary)
                                Text("complete", fontSize = 13.sp, color = OnSurface)
                            }
                            Text("Add portfolio link to reach 100%", fontSize = 11.sp, color = PrimaryContainer, fontWeight = FontWeight.Medium)
                        }

                        LinearProgressIndicator(
                            progress = { userProfile.completionPercentage / 100f },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(6.dp)
                                .clip(CircleShape),
                            color = PrimaryContainer,
                            trackColor = SurfaceContainerHighest
                        )
                    }
                }
            }
        }

        // Settings Items List
        item {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                color = SurfaceContainerLowest,
                shadowElevation = 1.dp
            ) {
                Column {
                    ProfileSettingRow(
                        icon = { IconPerson(tint = OnSurfaceVariant, modifier = Modifier.size(20.dp)) },
                        title = "Personal details",
                        subtitle = "${userProfile.email} · ${userProfile.phone}"
                    )
                    HorizontalDivider(color = SurfaceVariant)

                    ProfileSettingRow(
                        icon = { IconEducation(tint = OnSurfaceVariant, modifier = Modifier.size(20.dp)) },
                        title = "Education",
                        subtitle = "${userProfile.college} (CGPA: 8.84)"
                    )
                    HorizontalDivider(color = SurfaceVariant)

                    // Skills
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                IconCode(tint = OnSurfaceVariant, modifier = Modifier.size(20.dp))
                                Text("Skills", fontSize = 15.sp, fontWeight = FontWeight.SemiBold, color = OnSurface)
                            }
                            Text("Manage", fontSize = 12.sp, color = Primary, fontWeight = FontWeight.Medium)
                        }

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            modifier = Modifier.padding(start = 32.dp)
                        ) {
                            userProfile.skills.forEach { skill ->
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(6.dp))
                                        .background(SurfaceContainer)
                                        .padding(horizontal = 10.dp, vertical = 4.dp)
                                ) {
                                    Text(skill, fontSize = 12.sp, color = OnSurface)
                                }
                            }
                        }
                    }

                    HorizontalDivider(color = SurfaceVariant)

                    ProfileSettingRow(
                        icon = { IconWork(tint = OnSurfaceVariant, modifier = Modifier.size(20.dp)) },
                        title = "Experience",
                        subtitle = userProfile.experienceSummary
                    )
                    HorizontalDivider(color = SurfaceVariant)

                    ProfileSettingRow(
                        icon = { IconDocument(tint = OnSurfaceVariant, modifier = Modifier.size(20.dp)) },
                        title = "Resume",
                        subtitle = "${userProfile.resumeName} (${userProfile.resumeSize})"
                    )
                    HorizontalDivider(color = SurfaceVariant)

                    ProfileSettingRow(
                        icon = { IconLink(tint = OnSurfaceVariant, modifier = Modifier.size(20.dp)) },
                        title = "Links",
                        subtitle = "GitHub, LinkedIn, Behance"
                    )
                    HorizontalDivider(color = SurfaceVariant)

                    // Subscription Row
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconStar(tint = PrimaryContainer, modifier = Modifier.size(20.dp))
                            Column {
                                Text("Subscription", fontSize = 15.sp, fontWeight = FontWeight.SemiBold, color = OnSurface)
                                Text(if (userProfile.isProMember) "HireDude Pro Member" else "Free Tier", fontSize = 13.sp, color = OnSurfaceVariant)
                            }
                        }

                        Button(
                            onClick = { onUpgradeToPro() },
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = SurfaceContainer, contentColor = PrimaryContainer)
                        ) {
                            Text(if (userProfile.isProMember) "Manage Pro" else "Upgrade ₹299/mo", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }
                    }

                    HorizontalDivider(color = SurfaceVariant)

                    ProfileSettingRow(
                        icon = { IconSettings(tint = OnSurfaceVariant, modifier = Modifier.size(20.dp)) },
                        title = "Settings & Preferences",
                        subtitle = "Notifications, visibility & security"
                    )
                    HorizontalDivider(color = SurfaceVariant)

                    // Logout Row
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onLogout() }
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconLogout(tint = Error, modifier = Modifier.size(20.dp))
                            Text("Log out", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Error)
                        }
                        IconChevronRight(tint = Error, modifier = Modifier.size(16.dp))
                    }
                }
            }
        }

        // Edit Profile Form Section
        item {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                color = SurfaceContainerLowest,
                shadowElevation = 1.dp
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    Text("Edit Profile Info", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = OnSurface)

                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text("Full Name", fontSize = 12.sp, color = OnSurfaceVariant)
                        OutlinedTextField(
                            value = name,
                            onValueChange = { name = it },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            shape = RoundedCornerShape(8.dp)
                        )
                    }

                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text("Primary College Email", fontSize = 12.sp, color = OnSurfaceVariant)
                        OutlinedTextField(
                            value = email,
                            onValueChange = { email = it },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            enabled = false,
                            shape = RoundedCornerShape(8.dp)
                        )
                    }

                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text("Phone Number", fontSize = 12.sp, color = OnSurfaceVariant)
                        OutlinedTextField(
                            value = phone,
                            onValueChange = { phone = it },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            shape = RoundedCornerShape(8.dp)
                        )
                    }

                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text("Portfolio URL", fontSize = 12.sp, color = OnSurfaceVariant)
                        OutlinedTextField(
                            value = portfolioUrl,
                            onValueChange = { portfolioUrl = it },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            shape = RoundedCornerShape(8.dp)
                        )
                    }

                    if (showSavedNotification) {
                        Surface(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(8.dp),
                            color = SurfaceContainer
                        ) {
                            Text(
                                text = "✓ Changes saved successfully!",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Primary,
                                modifier = Modifier.padding(12.dp)
                            )
                        }
                    }

                    Button(
                        onClick = {
                            coroutineScope.launch {
                                isSaving = true
                                delay(600)
                                isSaving = false
                                showSavedNotification = true
                                onProfileUpdate(
                                    userProfile.copy(
                                        name = name,
                                        phone = phone,
                                        portfolioUrl = portfolioUrl,
                                        completionPercentage = 100
                                    )
                                )
                                delay(2000)
                                showSavedNotification = false
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(44.dp),
                        enabled = !isSaving,
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Primary, contentColor = OnPrimary)
                    ) {
                        if (isSaving) {
                            CircularProgressIndicator(modifier = Modifier.size(16.dp), color = OnPrimary, strokeWidth = 2.dp)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Updating...", fontSize = 14.sp)
                        } else {
                            Text("Save changes", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ProfileSettingRow(
    icon: @Composable () -> Unit,
    title: String,
    subtitle: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f)
        ) {
            icon()
            Column {
                Text(title, fontSize = 15.sp, fontWeight = FontWeight.SemiBold, color = OnSurface)
                Text(subtitle, fontSize = 13.sp, color = OnSurfaceVariant)
            }
        }

        IconChevronRight(tint = OnSurfaceVariant, modifier = Modifier.size(16.dp))
    }
}
