package com.example.piano

import com.example.piano.audio.EventType
import com.example.piano.audio.NoteEvent
import com.example.piano.audio.Recorder
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

/**
 * Unit tests for Piano app core functionality
 */
class RecorderTest {
    private lateinit var recorder: Recorder
    
    @Before
    fun setup() {
        recorder = Recorder()
    }
    
    @Test
    fun recorder_startsEmpty() {
        assertEquals(0, recorder.getEvents().size)
        assertFalse(recorder.isRecording())
    }
    
    @Test
    fun recorder_capturesNoteEvents() {
        recorder.start()
        assertTrue(recorder.isRecording())
        
        // Simulate note events
        Thread.sleep(10)
        recorder.recordNoteOn(60, 100)
        Thread.sleep(50)
        recorder.recordNoteOff(60)
        
        recorder.stop()
        assertFalse(recorder.isRecording())
        
        val events = recorder.getEvents()
        assertEquals(2, events.size)
        assertEquals(EventType.NOTE_ON, events[0].type)
        assertEquals(60, events[0].note)
        assertEquals(EventType.NOTE_OFF, events[1].type)
    }
    
    @Test
    fun recorder_timestampsAreRelative() {
        recorder.start()
        val startTime = System.currentTimeMillis()
        
        Thread.sleep(100)
        recorder.recordNoteOn(60, 100)
        
        val events = recorder.getEvents()
        assertTrue(events[0].t >= 100)
        assertTrue(events[0].t < 200) // Should be around 100ms
    }
    
    @Test
    fun recorder_doesNotRecordWhenStopped() {
        recorder.recordNoteOn(60, 100)
        assertEquals(0, recorder.getEvents().size)
    }
    
    @Test
    fun recorder_clearRemovesEvents() {
        recorder.start()
        recorder.recordNoteOn(60, 100)
        recorder.stop()
        
        recorder.clear()
        assertEquals(0, recorder.getEvents().size)
        assertFalse(recorder.isRecording())
    }
    
    @Test
    fun recorder_calculatesCorrectDuration() {
        recorder.start()
        Thread.sleep(50)
        recorder.recordNoteOn(60, 100)
        Thread.sleep(100)
        recorder.recordNoteOff(60)
        recorder.stop()
        
        val duration = recorder.getDurationMs()
        assertTrue(duration >= 150) // Should be around 150ms
    }
}

class NoteEventTest {
    @Test
    fun noteEvent_storesCorrectData() {
        val event = NoteEvent(
            type = EventType.NOTE_ON,
            note = 60,
            vel = 100,
            t = 1234
        )
        
        assertEquals(EventType.NOTE_ON, event.type)
        assertEquals(60, event.note)
        assertEquals(100, event.vel)
        assertEquals(1234, event.t)
    }
}