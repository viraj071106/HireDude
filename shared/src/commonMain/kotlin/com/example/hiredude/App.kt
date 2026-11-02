package com.example.hiredude

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.hiredude.model.AppScreen
import com.example.hiredude.model.MainTab
import com.example.hiredude.model.UserProfile
import com.example.hiredude.theme.HireDudeTheme
import com.example.hiredude.ui.*

@Composable
fun App() {
    var currentScreen by remember { mutableStateOf(AppScreen.SPLASH) }
    var currentTab by remember { mutableStateOf(MainTab.HOME) }
    var userProfile by remember { mutableStateOf(UserProfile()) }

    HireDudeTheme {
        when (currentScreen) {
            AppScreen.SPLASH -> {
                SplashContent(
                    onContinue = {
                        currentScreen = AppScreen.LOGIN
                    }
                )
            }

            AppScreen.LOGIN -> {
                LoginContent(
                    onLoginSuccess = {
                        currentScreen = AppScreen.MAIN
                        currentTab = MainTab.HOME
                    }
                )
            }

            AppScreen.MAIN -> {
                Scaffold(
                    contentWindowInsets = WindowInsets(0, 0, 0, 0),
                    bottomBar = {
                        BottomNavigationBar(
                            currentTab = currentTab,
                            onTabSelected = { tab ->
                                currentTab = tab
                            }
                        )
                    }
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        when (currentTab) {
                            MainTab.HOME -> {
                                HomeScreen(
                                    userProfile = userProfile,
                                    onUpgradeToPro = {
                                        userProfile = userProfile.copy(isProMember = true)
                                    },
                                    onOpenProfile = {
                                        currentTab = MainTab.PROFILE
                                    }
                                )
                            }

                            MainTab.APPLIED -> {
                                AppliedScreen(
                                    onBrowseRolesClick = {
                                        currentTab = MainTab.HOME
                                    }
                                )
                            }

                            MainTab.ABOUT -> {
                                AboutScreen()
                            }

                            MainTab.PROFILE -> {
                                ProfileScreen(
                                    userProfile = userProfile,
                                    onProfileUpdate = { updated ->
                                        userProfile = updated
                                    },
                                    onUpgradeToPro = {
                                        userProfile = userProfile.copy(isProMember = true)
                                    },
                                    onLogout = {
                                        currentScreen = AppScreen.LOGIN
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
