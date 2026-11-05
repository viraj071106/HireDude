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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hiredude.data.MockData
import com.example.hiredude.model.Job
import com.example.hiredude.theme.*

@Composable
fun JobDetailScreen(
    job: Job,
    onBack: () -> Unit,
    onApply: (Job) -> Unit,
    onBookmarkToggle: (Job) -> Unit
) {
    var isBookmarked by remember { mutableStateOf(job.isBookmarked) }
    var isApplied by remember { mutableStateOf(job.isApplied) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Surface)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Top Bar
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
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier
                            .clickable { onBack() }
                            .padding(end = 8.dp)
                    ) {
                        Text(
                            text = "← Back",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryContainer
                        )
                    }

                    Text(
                        text = job.company,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = OnSurface,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )

                    Box(
                        modifier = Modifier.clickable {
                            isBookmarked = !isBookmarked
                            onBookmarkToggle(job.copy(isBookmarked = isBookmarked))
                        }
                    ) {
                        IconBookmark(
                            tint = if (isBookmarked) PrimaryContainer else Outline,
                            isFilled = isBookmarked,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }

            // Scrollable Content
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Header Details Card
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
                        Text(
                            text = job.title,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = OnSurface
                        )

                        Text(
                            text = "${job.company} · ${job.sector} · Posted ${job.timeAgo}",
                            fontSize = 13.sp,
                            color = OnSurfaceVariant,
                            fontWeight = FontWeight.Medium
                        )

                        HorizontalDivider(color = SurfaceVariant)

                        // 4 Key Overview Grid Items
                        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                DetailOverviewBox(
                                    label = "Stipend",
                                    value = job.stipendText,
                                    modifier = Modifier.weight(1f)
                                )
                                DetailOverviewBox(
                                    label = "Location",
                                    value = "${job.location} (${job.workType})",
                                    modifier = Modifier.weight(1f)
                                )
                            }

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                DetailOverviewBox(
                                    label = "Experience / Batch",
                                    value = "${job.experienceRequired}\n${job.batchTag}",
                                    modifier = Modifier.weight(1f)
                                )
                                DetailOverviewBox(
                                    label = "Duration",
                                    value = job.duration,
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                    }
                }

                // Tech Stack & Skills
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    color = SurfaceContainerLowest,
                    shadowElevation = 1.dp
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Text(
                            text = "Required Tech Stack & Skills",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = OnSurface
                        )

                        @OptIn(ExperimentalLayoutApi::class)
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            job.techStack.forEach { tech ->
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(6.dp))
                                        .background(SurfaceContainer)
                                        .padding(horizontal = 10.dp, vertical = 6.dp)
                                ) {
                                    Text(
                                        text = tech,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = OnSurface
                                    )
                                }
                            }
                        }
                    }
                }

                // Description
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    color = SurfaceContainerLowest,
                    shadowElevation = 1.dp
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Text(
                            text = "About the Role",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = OnSurface
                        )

                        Text(
                            text = job.description,
                            fontSize = 14.sp,
                            color = OnSurfaceVariant,
                            lineHeight = 22.sp
                        )
                    }
                }

                // Responsibilities
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    color = SurfaceContainerLowest,
                    shadowElevation = 1.dp
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Text(
                            text = "Key Responsibilities",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = OnSurface
                        )

                        job.responsibilities.forEach { item ->
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.Top
                            ) {
                                Text("•", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = PrimaryContainer)
                                Text(item, fontSize = 13.sp, color = OnSurfaceVariant, lineHeight = 20.sp)
                            }
                        }
                    }
                }

                // Requirements
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    color = SurfaceContainerLowest,
                    shadowElevation = 1.dp
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Text(
                            text = "Requirements & Qualifications",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = OnSurface
                        )

                        job.requirements.forEach { item ->
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.Top
                            ) {
                                Text("•", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = PrimaryContainer)
                                Text(item, fontSize = 13.sp, color = OnSurfaceVariant, lineHeight = 20.sp)
                            }
                        }
                    }
                }

                // Recruiter Contact Info
                if (job.recruiterInfo != null) {
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        color = SurfaceContainerLowest,
                        shadowElevation = 1.dp
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            IconVerified(tint = PrimaryContainer, modifier = Modifier.size(20.dp))
                            Column {
                                Text(
                                    text = "Hiring Contact Info",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = OnSurfaceVariant
                                )
                                Text(
                                    text = job.recruiterInfo ?: "",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = OnSurface
                                )
                            }
                        }
                    }
                }
            }

            // Bottom Fixed Action Bar
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = SurfaceContainerLowest,
                shadowElevation = 8.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text("Compensation", fontSize = 11.sp, color = OnSurfaceVariant)
                        Text(
                            text = job.stipendText,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = OnSurface
                        )
                    }

                    if (isApplied) {
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(SuccessBg)
                                .padding(horizontal = 16.dp, vertical = 10.dp)
                        ) {
                            Text(
                                text = "Applied ✓",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = SuccessText
                            )
                        }
                    } else {
                        Button(
                            onClick = {
                                isApplied = true
                                onApply(job.copy(isApplied = true, appliedTimeAgo = "Just now"))
                            },
                            modifier = Modifier.height(44.dp),
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = PrimaryContainer,
                                contentColor = OnPrimary
                            )
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                IconBolt(tint = OnPrimary, modifier = Modifier.size(16.dp))
                                Text("Apply Now", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DetailOverviewBox(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(SurfaceContainerLow)
            .padding(12.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
            Text(label, fontSize = 11.sp, color = OnSurfaceVariant, fontWeight = FontWeight.Medium)
            Text(value, fontSize = 13.sp, color = OnSurface, fontWeight = FontWeight.Bold)
        }
    }
}

@Preview
@Composable
fun JobDetailScreenPreview() {
    HireDudeTheme {
        JobDetailScreen(
            job = MockData.getInitialJobs(false).first(),
            onBack = {},
            onApply = {},
            onBookmarkToggle = {}
        )
    }
}
