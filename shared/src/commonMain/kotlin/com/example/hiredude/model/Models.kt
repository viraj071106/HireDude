package com.example.hiredude.model

import androidx.compose.ui.graphics.Color

enum class AppScreen {
    SPLASH,
    LOGIN,
    MAIN
}

enum class MainTab {
    HOME,
    APPLIED,
    ABOUT,
    PROFILE
}

enum class AuthLoginState {
    DEFAULT,
    ERROR,
    LOADING
}

enum class ApplicationStatusFilter(val label: String, val badgeCount: Int) {
    ALL("All", 6),
    REVIEW("In review", 2),
    SHORTLISTED("Shortlisted", 2),
    INTERVIEW("Interview", 1),
    REJECTED("Rejected", 1)
}

data class Job(
    val id: String,
    val title: String,
    val company: String,
    val sector: String,
    val location: String,
    val workType: String,
    val duration: String,
    val timeAgo: String,
    val stipendText: String,
    val isStipendLocked: Boolean,
    val applicantsCount: String,
    val recruiterInfo: String? = null,
    val isApplied: Boolean = false,
    val appliedTimeAgo: String? = null,
    val isBookmarked: Boolean = false,
    val closesInDays: Int? = null,
    val companyBgColor: Color,
    val companyInitial: String,
    val batchTag: String = "Batch '25 / '26"
)

data class ApplicationItem(
    val id: String,
    val title: String,
    val company: String,
    val location: String,
    val workType: String,
    val timeAgo: String,
    val statusText: String,
    val statusFilter: ApplicationStatusFilter,
    val currentStep: Int,
    val totalSteps: Int = 4,
    val companyInitial: String,
    val initialColor: Color,
    val stepLabels: List<String> = listOf("Applied", "Review", "Shortlist", "Decision")
)

data class UserProfile(
    val name: String = "Ananya Rao",
    val college: String = "National Institute of Technology, Tiruchirappalli",
    val collegeShort: String = "NIT Trichy",
    val degreeBatch: String = "B.Tech CSE 2026",
    val email: String = "ananya.rao@nitt.edu",
    val phone: String = "+91 98765 43210",
    val portfolioUrl: String = "https://ananya.design",
    val isProMember: Boolean = false,
    val isNitVerified: Boolean = true,
    val completionPercentage: Int = 72,
    val skills: List<String> = listOf("React", "SQL", "Figma", "TypeScript", "Tailwind CSS"),
    val experienceSummary: String = "Ex-Frontend Intern @ Stealth Startup (3 mos)",
    val resumeName: String = "Ananya_Rao_CV.pdf",
    val resumeSize: String = "214 KB"
)
