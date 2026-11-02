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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hiredude.data.MockData
import com.example.hiredude.model.ApplicationItem
import com.example.hiredude.model.ApplicationStatusFilter
import com.example.hiredude.theme.*

@Composable
fun AppliedScreen(
    onBrowseRolesClick: () -> Unit
) {
    var selectedFilter by remember { mutableStateOf(ApplicationStatusFilter.ALL) }
    val applications = MockData.sampleApplications

    val filteredList = applications.filter { item ->
        if (selectedFilter == ApplicationStatusFilter.ALL) true
        else item.statusFilter == selectedFilter
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Surface)
            .statusBarsPadding()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Top Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            Row(
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = "Applied",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = OnSurface
                )
                Text(
                    text = "(${applications.size} total)",
                    fontSize = 15.sp,
                    color = Outline
                )
            }
        }

        // Horizontal Status Chips
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ApplicationStatusFilter.entries.forEach { filter ->
                val isSelected = filter == selectedFilter
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(if (isSelected) PrimaryContainer else SurfaceContainerLow)
                        .clickable { selectedFilter = filter }
                        .padding(horizontal = 14.dp, vertical = 6.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(
                            text = filter.label,
                            fontSize = 12.sp,
                            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Medium,
                            color = if (isSelected) OnPrimary else OnSurfaceVariant
                        )
                        Text(
                            text = "(${filter.badgeCount})",
                            fontSize = 11.sp,
                            color = if (isSelected) OnPrimary.copy(alpha = 0.8f) else Outline
                        )
                    }
                }
            }
        }

        // Pipeline Quick Stats
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val stats = listOf(
                Triple("Active Roles", "5", OnSurface),
                Triple("Response Rate", "83.3%", PrimaryContainer),
                Triple("Next Action", "Tomorrow", Secondary)
            )

            stats.forEach { (label, value, color) ->
                Surface(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(10.dp),
                    color = SurfaceContainerLowest,
                    shadowElevation = 1.dp
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(label, fontSize = 11.sp, color = OnSurfaceVariant)
                        Text(value, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = color)
                    }
                }
            }
        }

        // Application List Cards
        if (filteredList.isEmpty()) {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                color = SurfaceContainerLowest
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text("No applications in this category", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = OnSurface)
                    Text("Try switching back to 'All' to see all ${applications.size} submissions.", fontSize = 13.sp, color = Outline)
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(filteredList) { item ->
                    ApplicationCardItem(item)
                }

                item {
                    // Empty / Prompt Banner
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(14.dp),
                        color = SurfaceContainerLowest
                    ) {
                        Column(
                            modifier = Modifier.padding(20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text("Nothing applied yet?", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = OnSurface)
                            Text(
                                text = "Curated early-career engineering and product roles refresh daily at 9:00 AM IST.",
                                fontSize = 13.sp,
                                color = Outline,
                                modifier = Modifier.widthIn(max = 280.dp)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Button(
                                onClick = { onBrowseRolesClick() },
                                shape = RoundedCornerShape(8.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = SurfaceContainerLow, contentColor = PrimaryContainer)
                            ) {
                                Text("Browse roles", fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ApplicationCardItem(item: ApplicationItem) {
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
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(SurfaceContainerHigh),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = item.companyInitial,
                            fontWeight = FontWeight.Bold,
                            color = item.initialColor,
                            fontSize = 16.sp
                        )
                    }

                    Column {
                        Text(
                            text = item.title,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = OnSurface,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                text = item.company,
                                fontSize = 13.sp,
                                color = OnSurfaceVariant,
                                fontWeight = FontWeight.Medium,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text("•", fontSize = 12.sp, color = Outline)
                            Text(
                                text = item.location,
                                fontSize = 12.sp,
                                color = Outline,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }

                Text(item.timeAgo, fontSize = 12.sp, color = Outline)
            }

            // Status Note
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                val dotColor = when (item.statusFilter) {
                    ApplicationStatusFilter.SHORTLISTED -> WarningAmber
                    ApplicationStatusFilter.INTERVIEW -> SuccessGreen
                    ApplicationStatusFilter.REJECTED -> Error
                    else -> PrimaryContainer
                }

                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(dotColor)
                )
                Text(
                    text = item.statusText,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = OnSurface
                )
            }

            // 4-Step Progress Tracker
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    item.stepLabels.forEachIndexed { index, label ->
                        val isReached = index < item.currentStep
                        val isCurrent = index == item.currentStep - 1
                        val textColor = if (isCurrent) PrimaryContainer else if (isReached) OnSurface else Outline
                        Text(
                            text = label,
                            fontSize = 10.sp,
                            fontWeight = if (isCurrent) FontWeight.Bold else FontWeight.Medium,
                            color = textColor
                        )
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    for (step in 1..item.totalSteps) {
                        val isCompleted = step <= item.currentStep
                        val barColor = if (isCompleted) {
                            when (item.statusFilter) {
                                ApplicationStatusFilter.SHORTLISTED -> WarningAmber
                                ApplicationStatusFilter.INTERVIEW -> SuccessGreen
                                ApplicationStatusFilter.REJECTED -> Error.copy(alpha = 0.6f)
                                else -> PrimaryContainer
                            }
                        } else {
                            SurfaceVariant
                        }

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(6.dp)
                                .clip(CircleShape)
                                .background(barColor)
                        )
                    }
                }
            }
        }
    }
}
