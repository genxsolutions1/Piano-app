package com.genxsolutions.piano.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.genxsolutions.piano.audio.*
import com.genxsolutions.piano.data.Recording
import com.genxsolutions.piano.data.RecordingRepo
import kotlinx.coroutines.launch

class PianoViewModel(app: Application) : AndroidViewModel(app) {
    private val soundEngine = SoundEngine()
    private val recorder = Recorder()
    private val renderer = Renderer()
    private val playbackMgr = PlaybackMgr()
    private val repo = RecordingRepo(app)
    
    var isRecording by mutableStateOf(false)
        private set
    
    var hasRecording by mutableStateOf(false)
        private set
    
    var recordings by mutableStateOf<List<Recording>>(emptyList())
        private set
    
    var currentPlayingPath by mutableStateOf<String?>(null)
        private set
    
    var showSaveDialog by mutableStateOf(false)
        private set
    
    var saveError by mutableStateOf<String?>(null)
        private set
    
    init {
        soundEngine.init(app)
        loadRecordings()
    }
    
    fun onKeyPress(note: Int, vel: Int) {
        soundEngine.play(note, vel)
        if (isRecording) {
            recorder.recordNoteOn(note, vel)
        }
    }
    
    fun onKeyRelease(note: Int) {
        soundEngine.stop(note)
        if (isRecording) {
            recorder.recordNoteOff(note)
        }
    }
    
    fun startRecording() {
        recorder.start()
        isRecording = true
        hasRecording = false
    }
    
    fun stopRecording() {
        recorder.stop()
        isRecording = false
        hasRecording = recorder.getEvents().isNotEmpty()
        
        // Automatically show save dialog when stopping if there are events
        if (hasRecording) {
            showSaveDialog = true
        }
    }
    
    fun showSaveDialog() {
        showSaveDialog = true
        saveError = null
    }
    
    fun dismissSaveDialog() {
        showSaveDialog = false
        saveError = null
    }
    
    fun saveRecording(filename: String) {
        if (filename.isBlank()) {
            saveError = "Filename cannot be empty"
            return
        }
        
        viewModelScope.launch {
            try {
                val events = recorder.getEvents()
                val durationMs = recorder.getDurationMs()
                
                // Save recording metadata to get file path
                val outFile = repo.saveRecording(filename, durationMs)
                
                // Render events to WAV file
                val result = renderer.renderToWav(getApplication(), events, outFile)
                
                if (result.isSuccess) {
                    // Clear the recording
                    recorder.clear()
                    hasRecording = false
                    showSaveDialog = false
                    
                    // Reload recordings list
                    loadRecordings()
                } else {
                    saveError = "Failed to render recording: ${result.exceptionOrNull()?.message}"
                }
            } catch (e: Exception) {
                saveError = "Error saving: ${e.message}"
            }
        }
    }
    
    fun playRecording(recording: Recording) {
        try {
            playbackMgr.stop()
            playbackMgr.play(java.io.File(recording.filePath))
            currentPlayingPath = recording.filePath
        } catch (e: Exception) {
            // Handle playback error
            currentPlayingPath = null
        }
    }
    
    fun pausePlayback() {
        playbackMgr.pause()
        currentPlayingPath = null
    }
    
    fun deleteRecording(recording: Recording) {
        viewModelScope.launch {
            if (currentPlayingPath == recording.filePath) {
                playbackMgr.stop()
                currentPlayingPath = null
            }
            repo.deleteRecording(recording)
            loadRecordings()
        }
    }
    
    private fun loadRecordings() {
        viewModelScope.launch {
            recordings = repo.loadRecordings()
        }
    }
    
    override fun onCleared() {
        super.onCleared()
        soundEngine.release()
        playbackMgr.release()
    }
}
