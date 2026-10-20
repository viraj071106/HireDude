package com.example.hiredude.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hiredude.data.MockData
import com.example.hiredude.model.Job
import com.example.hiredude.model.UserProfile
import com.example.hiredude.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    userProfile: UserProfile,
    onUpgradeToPro: () -> Unit,
    onOpenProfile: () -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedFilter by remember { mutableStateOf("All") }
    var jobsList by remember { mutableStateOf(MockData.getInitialJobs(userProfile.isProMember)) }
    var showProSheet by remember { mutableStateOf(false) }

    val filterChips = listOf("All", "Internships", "Freelance", "Full-time", "Remote", "Design", "Engineering", "Marketing")

    // Update list when user profile pro state changes
    LaunchedEffect(userProfile.isProMember) {
        jobsList = MockData.getInitialJobs(userProfile.isProMember)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Surface)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Header Bar
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = SurfaceContainerLowest,
                shadowElevation = 1.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        Text(
                            text = "Hire",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryContainer
                        )
                        Text(
                            text = "Dude",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = OnSurface
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(SurfaceContainerLow),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("🔔", fontSize = 14.sp)
                        }

                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(PrimaryContainer)
                                .clickable { onOpenProfile() },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = userProfile.name.take(1),
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            }

            // Scrollable Content
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Greeting & Search Header
                item {
                    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    Text(
                                        text = "Hi ${userProfile.name.split(" ").firstOrNull() ?: "Ananya"}",
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = OnSurface
                                    )
                                    if (userProfile.isProMember) {
                                        IconVerified(tint = Primary, modifier = Modifier.size(18.dp))
                                    }
                                }
                                Text(
                                    text = "12 new roles match your profile",
                                    fontSize = 13.sp,
                                    color = OnSurfaceVariant
                                )
                            }

                            // Plan Badge
                            Box(
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(if (userProfile.isProMember) PrimaryContainer else SurfaceContainerHigh)
                                    .padding(horizontal = 10.dp, vertical = 4.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(6.dp)
                                            .clip(CircleShape)
                                            .background(if (userProfile.isProMember) Color.White else Primary)
                                    )
                                    Text(
                                        text = if (userProfile.isProMember) "HireDude Pro" else "Free Plan",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = if (userProfile.isProMember) Color.White else OnSurfaceVariant
                                    )
                                }
                            }
                        }

                        // Search Bar
                        OutlinedTextField(
                            value = searchQuery,
                            onValueChange = { searchQuery = it },
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = {
                                Text("Search by role, company, or tech stack...", fontSize = 13.sp, color = Outline)
                            },
                            leadingIcon = {
                                IconSearch(tint = Outline, modifier = Modifier.size(16.dp))
                            },
                            trailingIcon = {
                                if (searchQuery.isNotEmpty()) {
                                    Text(
                                        text = "✕",
                                        fontSize = 14.sp,
                                        color = Outline,
                                        modifier = Modifier.clickable { searchQuery = "" }
                                    )
                                }
                            },
                            singleLine = true,
                            shape = RoundedCornerShape(10.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = SurfaceContainerLowest,
                                unfocusedContainerColor = SurfaceContainerLowest,
                                focusedBorderColor = Primary,
                                unfocusedBorderColor = SurfaceContainer
                            )
                        )
                    }
                }

                // Filter Chips Row
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        filterChips.forEach { filter ->
                            val isSelected = filter == selectedFilter
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(if (isSelected) Primary else SurfaceContainerLowest)
                                    .clickable { selectedFilter = filter }
                                    .padding(horizontal = 14.dp, vertical = 8.dp)
                            ) {
                                Text(
                                    text = filter,
                                    fontSize = 12.sp,
                                    fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Medium,
                                    color = if (isSelected) OnPrimary else OnSurface
                                )
                            }
                        }
                    }
                }

                // Upsell Card (Free User) / Pro Benefit Indicator (Pro User)
                item {
                    if (!userProfile.isProMember) {
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
                            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                                    ) {
                                        IconVerified(tint = SecondaryContainer, modifier = Modifier.size(18.dp))
                                        Text(
                                            text = "HIREDUDE PRO",
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = SecondaryContainer,
                                            letterSpacing = 0.8.sp
                                        )
                                    }

                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(4.dp))
                                            .background(Color.White.copy(alpha = 0.15f))
                                            .padding(horizontal = 8.dp, vertical = 2.dp)
                                    ) {
                                        Text(
                                            text = "University Access",
                                            fontSize = 10.sp,
                                            color = Color.White
                                        )
                                    }
                                }

                                Text(
                                    text = "See stipend and apply to unlimited roles",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )

                                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                                    val benefits = listOf(
                                        "Unlock exact stipends & CTC figures",
                                        "Direct recruiter email & WhatsApp routing",
                                        "Instant IIT/NIT verified referral credentials"
                                    )
                                    benefits.forEach { benefit ->
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                                        ) {
                                            IconCheck(tint = TertiaryFixed, modifier = Modifier.size(14.dp))
                                            Text(
                                                text = benefit,
                                                fontSize = 13.sp,
                                                color = Color.White.copy(alpha = 0.9f)
                                            )
                                        }
                                    }
                                }

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 4.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column {
                                        Text("Early career plan", fontSize = 11.sp, color = Color.White.copy(alpha = 0.7f))
                                        Row(verticalAlignment = Alignment.Bottom) {
                                            Text("₹299", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White)
                                            Text(" / month", fontSize = 12.sp, color = Color.White.copy(alpha = 0.8f))
                                        }
                                    }

                                    Button(
                                        onClick = { showProSheet = true },
                                        shape = RoundedCornerShape(8.dp),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = SurfaceContainerLowest,
                                            contentColor = Primary
                                        )
                                    ) {
                                        Text("Upgrade", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                                    }
                                }
                            }
                        }
                    } else {
                        // Pro Benefit Indicator Bar
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
                                    IconVerified(tint = Primary, modifier = Modifier.size(20.dp))
                                }

                                Column(modifier = Modifier.weight(1f)) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                                    ) {
                                        Text("Pro Active", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Primary)
                                        Text("·", fontSize = 12.sp, color = OnSurfaceVariant)
                                        Text("Direct Referrals", fontSize = 12.sp, color = OnSurfaceVariant)
                                    }
                                    Text(
                                        text = "Unlimited applications & unlocked verified CTC",
                                        fontSize = 13.sp,
                                        color = OnSurface
                                    )
                                }

                                IconCheck(tint = Primary, modifier = Modifier.size(18.dp))
                            }
                        }
                    }
                }

                // Feed Section Header
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = "Curated Roles",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = OnSurface
                            )
                            Box(
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(SurfaceContainerHigh)
                                    .padding(horizontal = 8.dp, vertical = 2.dp)
                            ) {
                                Text("12 live", fontSize = 11.sp, color = OnSurfaceVariant, fontWeight = FontWeight.Medium)
                            }
                        }
                    }
                }

                // Job Cards Stream
                val filteredJobs = jobsList.filter { job ->
                    if (searchQuery.isEmpty()) true
                    else job.title.contains(searchQuery, ignoreCase = true) || job.company.contains(searchQuery, ignoreCase = true)
                }

                items(filteredJobs) { job ->
                    JobCardItem(
                        job = job,
                        isProUser = userProfile.isProMember,
                        onRevealClick = { showProSheet = true },
                        onBookmarkToggle = {
                            jobsList = jobsList.map { item ->
                                if (item.id == job.id) item.copy(isBookmarked = !item.isBookmarked) else item
                            }
                        },
                        onApplyClick = {
                            jobsList = jobsList.map { item ->
                                if (item.id == job.id) item.copy(isApplied = true, appliedTimeAgo = "Just now") else item
                            }
                        }
                    )
                }
            }
        }

        // Pro Upgrade BottomSheet Modal
        if (showProSheet) {
            ModalBottomSheet(
                onDismissRequest = { showProSheet = false },
                containerColor = SurfaceContainerLowest,
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text("PRO TIER FEATURE", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Primary)
                            Text("Unlock full job intelligence", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = OnSurface)
                        }
                        Text(
                            text = "✕",
                            fontSize = 18.sp,
                            color = Outline,
                            modifier = Modifier.clickable { showProSheet = false }
                        )
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(SurfaceContainerLow)
                            .padding(14.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        val features = listOf(
                            "Stipend & CTC Breakdown" to "See monthly in-hand, performance bonus, and PPO conversion packages.",
                            "Direct Recruiter Desk" to "Verified LinkedIn handle, work email, and referral alumni connections.",
                            "1-Tap Fast Apply" to "Bypass third-party ATS screening portals with direct hiring pipeline access."
                        )
                        features.forEach { (featureTitle, featureDesc) ->
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(10.dp),
                                verticalAlignment = Alignment.Top
                            ) {
                                IconCheck(tint = Primary, modifier = Modifier.size(16.dp))
                                Column {
                                    Text(featureTitle, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = OnSurface)
                                    Text(featureDesc, fontSize = 12.sp, color = OnSurfaceVariant)
                                }
                            }
                        }
                    }

                    Button(
                        onClick = {
                            showProSheet = false
                            onUpgradeToPro()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(46.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Primary, contentColor = OnPrimary)
                    ) {
                        Text("Upgrade for ₹299 / month", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    }

                    Text(
                        text = "Cancel anytime · Zero lock-in period · Indian UPI supported",
                        fontSize = 11.sp,
                        color = OnSurfaceVariant,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
    }
}

@Composable
fun JobCardItem(
    job: Job,
    isProUser: Boolean,
    onRevealClick: () -> Unit,
    onBookmarkToggle: () -> Unit,
    onApplyClick: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        color = SurfaceContainerLowest,
        shadowElevation = 1.dp
    ) {
        Column(
            modifier = Modifier.padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Top Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(job.companyBgColor),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = job.companyInitial,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    }

                    Column {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text(
                                text = "${job.company} · ${job.sector}",
                                fontSize = 12.sp,
                                color = OnSurfaceVariant,
                                fontWeight = FontWeight.Medium
                            )
                            Text("·", fontSize = 12.sp, color = Outline)
                            Text(job.timeAgo, fontSize = 12.sp, color = OnSurfaceVariant)
                        }

                        Text(
                            text = job.title,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = OnSurface
                        )
                    }
                }

                Box(
                    modifier = Modifier.clickable { onBookmarkToggle() }
                ) {
                    IconBookmark(
                        tint = if (job.isBookmarked) Primary else Outline,
                        isFilled = job.isBookmarked,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            // Meta Badges
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val badges = listOf(job.location, job.workType, job.duration, job.batchTag)
                badges.forEach { badge ->
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(SurfaceContainer)
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Text(badge, fontSize = 11.sp, color = OnSurface)
                    }
                }
            }

            // Recruiter info if available
            if (job.recruiterInfo != null && isProUser) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(SurfaceContainerLow)
                        .padding(8.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        IconVerified(tint = Primary, modifier = Modifier.size(16.dp))
                        Text(
                            text = job.recruiterInfo,
                            fontSize = 12.sp,
                            color = OnSurface,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            // Stipend Row
            if (job.isStipendLocked) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(SurfaceContainerLow)
                        .clickable { onRevealClick() }
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        IconLock(tint = Secondary, modifier = Modifier.size(16.dp))
                        Text(
                            text = "Stipend locked for free users",
                            fontSize = 13.sp,
                            color = OnSurfaceVariant
                        )
                    }

                    Text(
                        text = "Reveal ➔",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Primary
                    )
                }
            } else {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text("Compensation", fontSize = 11.sp, color = OnSurfaceVariant)
                        Text(
                            text = job.stipendText,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = OnSurface
                        )
                    }

                    if (job.isApplied) {
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(SuccessBg)
                                .padding(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = job.appliedTimeAgo ?: "Applied",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = SuccessText
                            )
                        }
                    } else {
                        Button(
                            onClick = { onApplyClick() },
                            shape = RoundedCornerShape(8.dp),
                            contentPadding = PaddingValues(horizontal = 14.dp, vertical = 6.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = PrimaryContainer, contentColor = OnPrimary)
                        ) {
                            Text("Direct Apply ⚡", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }

            // Footer
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(job.applicantsCount, fontSize = 11.sp, color = OnSurfaceVariant)
            }
        }
    }
}
