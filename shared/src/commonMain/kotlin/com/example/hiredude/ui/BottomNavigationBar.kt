package com.example.hiredude.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hiredude.model.MainTab
import com.example.hiredude.theme.OnSurfaceVariant
import com.example.hiredude.theme.PrimaryContainer
import com.example.hiredude.theme.SurfaceContainerLowest

@Composable
fun BottomNavigationBar(
    currentTab: MainTab,
    onTabSelected: (MainTab) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = SurfaceContainerLowest,
        shadowElevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val tabs = listOf(
                MainTab.HOME to ("Home" to @Composable { tint: androidx.compose.ui.graphics.Color -> IconHome(tint = tint, modifier = Modifier.size(20.dp)) }),
                MainTab.APPLIED to ("Applied" to @Composable { tint: androidx.compose.ui.graphics.Color -> IconBriefcase(tint = tint, modifier = Modifier.size(20.dp)) }),
                MainTab.ABOUT to ("About" to @Composable { tint: androidx.compose.ui.graphics.Color -> IconInfo(tint = tint, modifier = Modifier.size(20.dp)) }),
                MainTab.PROFILE to ("Profile" to @Composable { tint: androidx.compose.ui.graphics.Color -> IconPerson(tint = tint, modifier = Modifier.size(20.dp)) })
            )

            tabs.forEach { (tab, item) ->
                val (label, iconComposable) = item
                val isSelected = tab == currentTab
                val color = if (isSelected) PrimaryContainer else OnSurfaceVariant

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clickable { onTabSelected(tab) },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    if (isSelected) {
                        Box(
                            modifier = Modifier
                                .width(36.dp)
                                .height(2.dp)
                                .background(PrimaryContainer, shape = RoundedCornerShape(1.dp))
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                    }

                    iconComposable(color)

                    Spacer(modifier = Modifier.height(2.dp))

                    Text(
                        text = label,
                        fontSize = 11.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                        color = color
                    )
                }
            }
        }
    }
}
