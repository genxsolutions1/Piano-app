package com.example.piano.audio

import android.media.MediaPlayer
import java.io.File

class PlaybackMgr {
    private var mediaPlayer: MediaPlayer? = null
    private var currentFile: File? = null
    
    fun play(file: File) {
        if (mediaPlayer?.isPlaying == true && currentFile == file) {
            return // Already playing this file
        }
        
        stop()
        
        mediaPlayer = MediaPlayer().apply {
            setDataSource(file.absolutePath)
            prepare()
            start()
        }
        currentFile = file
    }
    
    fun pause() {
        if (mediaPlayer?.isPlaying == true) {
            mediaPlayer?.pause()
        }
    }
    
    fun resume() {
        if (mediaPlayer?.isPlaying == false) {
            mediaPlayer?.start()
        }
    }
    
    fun stop() {
        mediaPlayer?.apply {
            if (isPlaying) stop()
            release()
        }
        mediaPlayer = null
        currentFile = null
    }
    
    fun isPlaying(): Boolean = mediaPlayer?.isPlaying == true
    
    fun getCurrentFile(): File? = currentFile
    
    fun release() {
        stop()
    }
}
