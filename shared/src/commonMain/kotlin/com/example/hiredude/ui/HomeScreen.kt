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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hiredude.data.MockData
import com.example.hiredude.model.Job
import com.example.hiredude.model.UserProfile
import com.example.hiredude.theme.*

@Composable
@Preview
fun preview_function() {
    HomeScreen(
        userProfile = UserProfile(),
        onUpgradeToPro = {},
        onOpenProfile = {}
    )
}

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
    var selectedJob by remember { mutableStateOf<Job?>(null) }

    val filterChips = listOf("All", "Internships", "Freelance", "Full-time", "Remote", "Design", "Engineering", "Marketing")

    LaunchedEffect(userProfile.isProMember) {
        jobsList = MockData.getInitialJobs(userProfile.isProMember)
    }

    if (selectedJob != null) {
        // Detailed Job Screen
        JobDetailScreen(
            job = selectedJob!!,
            onBack = { selectedJob = null },
            onApply = { appliedJob ->
                jobsList = jobsList.map { item ->
                    if (item.id == appliedJob.id) appliedJob else item
                }
                selectedJob = appliedJob
            },
            onBookmarkToggle = { bookmarkedJob ->
                jobsList = jobsList.map { item ->
                    if (item.id == bookmarkedJob.id) bookmarkedJob else item
                }
            }
        )
    } else {
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
                            .statusBarsPadding()
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
                                IconBell(tint = OnSurfaceVariant, modifier = Modifier.size(18.dp))
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
                                Column(modifier = Modifier.weight(1f)) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                                    ) {
                                        Text(
                                            text = "Hi ${userProfile.name.split(" ").firstOrNull() ?: "Ananya"}",
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = OnSurface,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                        if (userProfile.isProMember) {
                                            IconVerified(tint = Primary, modifier = Modifier.size(18.dp))
                                        }
                                    }
                                    Text(
                                        text = "12 new roles match your profile",
                                        fontSize = 13.sp,
                                        color = OnSurfaceVariant,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
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

                    // Upsell Banner (Free User) / Pro Active Indicator (Pro User)
                    item {
                        if (!userProfile.isProMember) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(16.dp))
                                    .background(Primary)
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
                                                text = "Student Plan",
                                                fontSize = 10.sp,
                                                color = Color.White
                                            )
                                        }
                                    }

                                    Text(
                                        text = "Apply directly to hiring managers without ATS filtering",
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )

                                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                                        val benefits = listOf(
                                            "View exact stipends & CTC details",
                                            "Direct recruiter email & message routing",
                                            "Verified student profile highlights"
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
                                            Text("Direct Applications", fontSize = 12.sp, color = OnSurfaceVariant)
                                        }
                                        Text(
                                            text = "Unlimited applications and verified CTC",
                                            fontSize = 13.sp,
                                            color = OnSurface,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
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
                                    text = "Available Roles",
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
                            onCardClick = { selectedJob = job },
                            onBookmarkToggle = {
                                jobsList = jobsList.map { item ->
                                    if (item.id == job.id) item.copy(isBookmarked = !item.isBookmarked) else item
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
                                Text("Full job details", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = OnSurface)
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
                                "Stipend & CTC Breakdown" to "See in-hand compensation, bonuses, and PPO packages.",
                                "Direct Recruiter Contact" to "Verified recruiter profile, email, and alumni network links.",
                                "Fast Apply" to "Apply directly to hiring managers without third-party filters."
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
}

/**
 * Decluttered 4-Zone Mobile Job Card:
 * Entire card is clickable to view job details.
 * Zone 1: Header (Company name + category, Time posted, Bookmark toggle)
 * Zone 2: Title (Role title text, maxLines = 2, overflow = TextOverflow.Ellipsis)
 * Zone 3: Badges (Max 3 chips: location, work style, duration)
 * Zone 4: Action (Stipend text on left, applicant count or applied badge on right)
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun JobCardItem(
    job: Job,
    onCardClick: () -> Unit,
    onBookmarkToggle: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCardClick() },
        shape = RoundedCornerShape(14.dp),
        color = SurfaceContainerLowest,
        shadowElevation = 1.dp
    ) {
        Column(
            modifier = Modifier
                .padding(14.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // ZONE 1: Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${job.company} · ${job.sector} · ${job.timeAgo}",
                    fontSize = 12.sp,
                    color = OnSurfaceVariant,
                    fontWeight = FontWeight.Medium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )

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

            // ZONE 2: Title
            Text(
                text = job.title,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = OnSurface,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            // ZONE 3: Badges (Max 3 chips)
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                val maxThreeBadges = listOf(job.location, job.workType, job.duration)
                    .filter { it.isNotBlank() }
                    .take(3)

                maxThreeBadges.forEach { badge ->
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(SurfaceContainer)
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Text(
                            text = badge,
                            fontSize = 11.sp,
                            color = OnSurface,
                            maxLines = 1,
                            softWrap = false,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }

            // ZONE 4: Stipend & Status Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text("Stipend", fontSize = 11.sp, color = OnSurfaceVariant)
                    Text(
                        text = job.stipendText,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = OnSurface,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                if (job.isApplied) {
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(SuccessBg)
                            .padding(horizontal = 10.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = job.appliedTimeAgo ?: "Applied",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = SuccessText,
                            maxLines = 1,
                            softWrap = false
                        )
                    }
                } else {
                    val cleanApplicantText = job.applicantsCount.split("·").firstOrNull()?.trim() ?: job.applicantsCount
                    Text(
                        text = cleanApplicantText,
                        fontSize = 12.sp,
                        color = OnSurfaceVariant,
                        fontWeight = FontWeight.Medium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}
