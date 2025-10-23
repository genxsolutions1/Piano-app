package com.genxsolutions.piano.audio

class Recorder {
    private val events = mutableListOf<NoteEvent>()
    private var tStart: Long = 0
    private var isRec = false
    
    fun start() {
        events.clear()
        tStart = System.currentTimeMillis()
        isRec = true
    }
    
    fun stop() {
        isRec = false
    }
    
    fun recordNoteOn(note: Int, vel: Int) {
        if (!isRec) return
        val t = System.currentTimeMillis() - tStart
        events.add(NoteEvent(EventType.NOTE_ON, note, vel, t))
    }
    
    fun recordNoteOff(note: Int) {
        if (!isRec) return
        val t = System.currentTimeMillis() - tStart
        events.add(NoteEvent(EventType.NOTE_OFF, note, 0, t))
    }
    
    fun clear() {
        events.clear()
        isRec = false
    }
    
    fun getEvents(): List<NoteEvent> = events.toList()
    
    fun isRecording(): Boolean = isRec
    
    fun getDurationMs(): Long {
        return if (events.isEmpty()) 0 else events.maxOf { it.t }
    }
}
