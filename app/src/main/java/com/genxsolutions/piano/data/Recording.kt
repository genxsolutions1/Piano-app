package com.genxsolutions.piano.data

data class Recording(
    val filename: String,
    val filePath: String,
    val createdAt: String,
    val durationMs: Long
)
