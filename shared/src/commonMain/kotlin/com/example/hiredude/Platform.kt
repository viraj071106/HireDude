package com.example.hiredude

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform