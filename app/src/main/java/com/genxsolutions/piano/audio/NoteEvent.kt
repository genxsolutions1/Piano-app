package com.genxsolutions.piano.audio

data class NoteEvent(
    val type: EventType,
    val note: Int, // MIDI note number (0-127)
    val vel: Int, // Velocity (0-127)
    val t: Long // Timestamp in milliseconds relative to recording start
)

enum class EventType {
    NOTE_ON,
    NOTE_OFF
}
