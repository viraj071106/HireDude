package com.example.hiredude.data

import com.example.hiredude.model.ApplicationItem
import com.example.hiredude.model.ApplicationStatusFilter
import com.example.hiredude.model.Job
import com.example.hiredude.theme.CardCredBg
import com.example.hiredude.theme.CardGrowwBg
import com.example.hiredude.theme.CardRazorpayBg
import com.example.hiredude.theme.CardZeptoBg
import com.example.hiredude.theme.PrimaryContainer
import com.example.hiredude.theme.Secondary

object MockData {

    fun getInitialJobs(isProUser: Boolean): List<Job> = listOf(
        Job(
            id = "j1",
            title = "Frontend Engineering Intern (React)",
            company = "Zepto",
            sector = "Quick Commerce",
            location = "Gurugram",
            workType = "Hybrid",
            duration = "6 months",
            timeAgo = "Yesterday",
            stipendText = "₹45,000 / mo",
            isStipendLocked = !isProUser,
            applicantsCount = "212 applicants · Batch of 2025/26",
            recruiterInfo = "Recruiter: Sarthak G. (IITD '20) · direct contact verified",
            companyBgColor = CardZeptoBg,
            companyInitial = "Z"
        ),
        Job(
            id = "j2",
            title = "Product Design Intern (Design Systems)",
            company = "Razorpay",
            sector = "Fintech",
            location = "Bengaluru",
            workType = "Onsite",
            duration = "3 months",
            timeAgo = "3d ago",
            stipendText = "₹60,000 / mo",
            isStipendLocked = !isProUser,
            applicantsCount = "140 applicants",
            isApplied = true,
            appliedTimeAgo = "Applied 2d ago",
            isBookmarked = true,
            companyBgColor = CardRazorpayBg,
            companyInitial = "R"
        ),
        Job(
            id = "j3",
            title = "Backend SDE Intern (Go/Postgres)",
            company = "Groww",
            sector = "Wealthtech",
            location = "Bengaluru",
            workType = "Remote",
            duration = "6 months",
            timeAgo = "2d ago",
            stipendText = "₹50,000 / mo",
            isStipendLocked = !isProUser,
            applicantsCount = "1,480 views · 89 referrals requested",
            recruiterInfo = "Hiring Manager: Priya K. · Priority review active",
            closesInDays = 2,
            companyBgColor = CardGrowwBg,
            companyInitial = "G"
        ),
        Job(
            id = "j4",
            title = "Developer Relations Fellow",
            company = "Postman",
            sector = "Developer APIs",
            location = "Remote",
            workType = "Global Program",
            duration = "4 months",
            timeAgo = "5d ago",
            stipendText = "Discretionary perk (Not disclosed by recruiter)",
            isStipendLocked = false,
            applicantsCount = "48 applicants · Open for Tier 1 & 2 colleges",
            companyBgColor = CardZeptoBg,
            companyInitial = "P"
        ),
        Job(
            id = "j5",
            title = "Android Engineering Intern (Kotlin)",
            company = "CRED",
            sector = "Fintech",
            location = "Bengaluru",
            workType = "In-office",
            duration = "6 months",
            timeAgo = "1d ago",
            stipendText = "₹1,00,000 / mo",
            isStipendLocked = !isProUser,
            applicantsCount = "89 applicants",
            companyBgColor = CardCredBg,
            companyInitial = "C"
        )
    )

    val sampleApplications = listOf(
        ApplicationItem(
            id = "a1",
            title = "Product Design Intern",
            company = "Razorpay",
            location = "Bangalore (Hybrid)",
            workType = "Hybrid",
            timeAgo = "3d ago",
            statusText = "Shortlisted for Round 2",
            statusFilter = ApplicationStatusFilter.SHORTLISTED,
            currentStep = 3,
            companyInitial = "R",
            initialColor = PrimaryContainer,
            stepLabels = listOf("Applied", "Review", "Shortlist", "Decision")
        ),
        ApplicationItem(
            id = "a2",
            title = "Frontend Engineering Intern",
            company = "Zepto",
            location = "Mumbai (On-site)",
            workType = "Onsite",
            timeAgo = "Yesterday",
            statusText = "In review by IITD Alumni recruiter",
            statusFilter = ApplicationStatusFilter.REVIEW,
            currentStep = 2,
            companyInitial = "Z",
            initialColor = Secondary,
            stepLabels = listOf("Applied", "Review", "Shortlist", "Decision")
        ),
        ApplicationItem(
            id = "a3",
            title = "Data Analyst Intern (SQL/Python)",
            company = "Swiggy",
            location = "Remote",
            workType = "Remote",
            timeAgo = "1w ago",
            statusText = "Interview scheduled (Tomorrow, 4:00 PM IST)",
            statusFilter = ApplicationStatusFilter.INTERVIEW,
            currentStep = 3,
            companyInitial = "S",
            initialColor = CardGrowwBg,
            stepLabels = listOf("Applied", "Technical", "Live Meet", "Decision")
        ),
        ApplicationItem(
            id = "a4",
            title = "QA Automation Intern",
            company = "Meesho",
            location = "Bangalore",
            workType = "Onsite",
            timeAgo = "2w ago",
            statusText = "Not selected",
            statusFilter = ApplicationStatusFilter.REJECTED,
            currentStep = 3,
            companyInitial = "M",
            initialColor = Secondary,
            stepLabels = listOf("Applied", "Screened", "Archived", "Feedback")
        ),
        ApplicationItem(
            id = "a5",
            title = "Developer Relations Intern",
            company = "Postman",
            location = "Remote",
            workType = "Remote",
            timeAgo = "4d ago",
            statusText = "Application received & screening",
            statusFilter = ApplicationStatusFilter.REVIEW,
            currentStep = 2,
            companyInitial = "P",
            initialColor = PrimaryContainer
        ),
        ApplicationItem(
            id = "a6",
            title = "Backend SDE Intern",
            company = "CRED",
            location = "Bangalore",
            workType = "Onsite",
            timeAgo = "5d ago",
            statusText = "Coding assessment cleared (98th percentile)",
            statusFilter = ApplicationStatusFilter.SHORTLISTED,
            currentStep = 3,
            companyInitial = "C",
            initialColor = CardCredBg
        )
    )
}
