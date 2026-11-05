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
            isStipendLocked = false,
            applicantsCount = "212 applicants",
            recruiterInfo = "Recruiter: Sarthak G. (IITD '20 Alumni)",
            companyBgColor = CardZeptoBg,
            companyInitial = "Z",
            batchTag = "Batch '25 / '26",
            experienceRequired = "0-1 Years (Students / Freshers)",
            techStack = listOf("React.js", "TypeScript", "Redux Toolkit", "Tailwind CSS", "REST APIs", "Jest"),
            description = "Zepto's quick commerce platform handles millions of orders daily. As a Frontend Engineering Intern, you will work closely with product managers and senior engineers to build fast, responsive web interfaces for warehouse operations and customer checkout.",
            responsibilities = listOf(
                "Build reusable UI components using React, TypeScript, and Tailwind CSS",
                "Optimize web performance, bundle size, and real-time state synchronization",
                "Integrate RESTful microservices and handle real-time delivery tracking UI",
                "Participate in code reviews, bug fixes, and automated testing"
            ),
            requirements = listOf(
                "Proficiency in JavaScript (ES6+), TypeScript, HTML5, and CSS3",
                "Hands-on experience building web apps with React.js and modern state management",
                "Solid grasp of web performance, asynchronous programming, and Git",
                "Pursuing B.Tech / M.Tech in CS or IT (Batch of 2025 or 2026)"
            )
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
            isStipendLocked = false,
            applicantsCount = "140 applicants",
            isApplied = true,
            appliedTimeAgo = "Applied 2d ago",
            isBookmarked = true,
            recruiterInfo = "Design Lead: Ankit M.",
            companyBgColor = CardRazorpayBg,
            companyInitial = "R",
            batchTag = "Batch '25 / '26",
            experienceRequired = "0-1 Years (Design Students)",
            techStack = listOf("Figma", "Design Systems", "UI/UX", "Prototyping", "Design Tokens", "User Research"),
            description = "Razorpay powers payments for top Indian businesses. You will join the Design System team (Blade) to craft cohesive, accessible UI components used across Razorpay's suite of checkout and merchant dashboard products.",
            responsibilities = listOf(
                "Design scalable UI components, variants, and interactive Figma prototypes",
                "Document usage guidelines, accessibility rules, and token specifications",
                "Collaborate with frontend engineers to ensure design-to-code fidelity",
                "Conduct user testing sessions with merchants and developers"
            ),
            requirements = listOf(
                "Portfolio demonstrating strong UI craftsmanship and structured design thinking",
                "Deep understanding of typography, grid systems, color theory, and Figma auto-layout",
                "Awareness of accessibility standards (WCAG 2.1) and component tokenization",
                "Enrolled in Design or CS degree program with expected graduation 2025/2026"
            )
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
            isStipendLocked = false,
            applicantsCount = "89 applicants",
            closesInDays = 2,
            recruiterInfo = "Engineering Manager: Priya K.",
            companyBgColor = CardGrowwBg,
            companyInitial = "G",
            batchTag = "Batch '25 / '26",
            experienceRequired = "0-1 Years (Final Year Students)",
            techStack = listOf("Go (Golang)", "PostgreSQL", "Kafka", "Docker", "Redis", "gRPC"),
            description = "Groww is India's leading investment platform. As a Backend Intern, you will work on high-throughput microservices that power real-time stock trading, mutual fund orders, and portfolio tracking.",
            responsibilities = listOf(
                "Develop microservices in Go with high concurrency and low latency",
                "Design database schemas, write SQL queries, and optimize PostgreSQL indexes",
                "Integrate Kafka event streams and Redis caching layers",
                "Set up Docker containers and CI/CD deployment pipelines"
            ),
            requirements = listOf(
                "Strong foundation in Data Structures, Computer Networks, and DBMS concepts",
                "Experience with Golang or Java/C++ with willingness to work in Go",
                "Familiarity with SQL databases, HTTP/REST APIs, and Linux basics",
                "B.Tech / M.Tech in Computer Science / IT (Graduation 2025/2026)"
            )
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
            stipendText = "Performance stipend provided",
            isStipendLocked = false,
            applicantsCount = "48 applicants",
            recruiterInfo = "DevRel Lead: Tanay P.",
            companyBgColor = CardZeptoBg,
            companyInitial = "P",
            batchTag = "Batch '25 / '26",
            experienceRequired = "0-1 Years (Open for Tier 1 & 2)",
            techStack = listOf("API Design", "Postman Collections", "JavaScript", "Technical Writing", "Open API"),
            description = "Postman is the API platform used by 30M+ developers. The DevRel Fellowship gives you the opportunity to create developer content, run student API workshops, and build open-source API projects.",
            responsibilities = listOf(
                "Author technical guides, API collections, and open-source sample apps",
                "Host virtual API workshops and hackathons for student developers",
                "Engage with the developer community on GitHub, Discord, and Postman forums",
                "Provide feedback to product teams based on developer community needs"
            ),
            requirements = listOf(
                "Active developer presence (GitHub projects, technical blog posts, or community building)",
                "Solid understanding of REST APIs, HTTP methods, JSON, and JavaScript",
                "Excellent written and verbal communication skills",
                "Enrolled in any undergraduate or postgraduate engineering program"
            )
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
            isStipendLocked = false,
            applicantsCount = "89 applicants",
            recruiterInfo = "Android Lead: Rahul S.",
            companyBgColor = CardCredBg,
            companyInitial = "C",
            batchTag = "Batch '25 / '26",
            experienceRequired = "0-1 Years (Strong Kotlin Skills)",
            techStack = listOf("Kotlin", "Jetpack Compose", "Coroutines & Flow", "Android SDK", "Dagger Hilt", "Clean Architecture"),
            description = "CRED is renowned for its sleek, high-craft mobile experiences. As an Android Intern, you will build fluid Compose UI components, micro-interactions, and offline-first mobile payment features.",
            responsibilities = listOf(
                "Build declarative UI screens using Jetpack Compose and custom graphics shaders",
                "Implement asynchronous data streams using Kotlin Coroutines and Flow",
                "Integrate secure payment SDKs and maintain Clean Architecture standards",
                "Analyze memory profiles, reduce APK size, and eliminate UI jank"
            ),
            requirements = listOf(
                "Deep understanding of modern Android development, Kotlin, and Jetpack Compose",
                "Experience with MVVM/MVI, StateFlow, Room database, and dependency injection",
                "Eye for design detail, custom animations, and mobile performance optimization",
                "B.Tech / M.Tech in CS/IT with strong problem-solving record"
            )
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
            statusText = "In review by hiring team",
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
            statusText = "Interview scheduled",
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
            statusText = "Coding assessment cleared",
            statusFilter = ApplicationStatusFilter.SHORTLISTED,
            currentStep = 3,
            companyInitial = "C",
            initialColor = CardCredBg
        )
    )
}
