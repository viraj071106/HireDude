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
import androidx.compose.ui.graphics.Color
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
                MainTab.HOME to ("🏠" to "Home"),
                MainTab.APPLIED to ("💼" to "Applied"),
                MainTab.ABOUT to ("ℹ️" to "About"),
                MainTab.PROFILE to ("👤" to "Profile")
            )

            tabs.forEach { (tab, iconLabel) ->
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

                    Text(
                        text = iconLabel.first,
                        fontSize = 16.sp
                    )

                    Text(
                        text = iconLabel.second,
                        fontSize = 11.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                        color = color
                    )
                }
            }
        }
    }
}
