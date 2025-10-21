package com.example.piano.audio

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder
import kotlin.math.min

class Renderer {
    suspend fun renderToWav(
        ctx: Context,
        events: List<NoteEvent>,
        outFile: File,
        sampleRate: Int = 44100
    ): Result<File> = withContext(Dispatchers.IO) {
        try {
            if (events.isEmpty()) {
                return@withContext Result.failure(Exception("No events to render"))
            }
            
            // Calculate total duration
            val durationMs = events.maxOf { it.t } + 2000 // Add 2s tail
            val numSamples = ((durationMs / 1000.0) * sampleRate).toInt()
            
            // Create output buffer (16-bit PCM)
            val buffer = FloatArray(numSamples)
            
            // Mix events into buffer
            // For each NOTE_ON event, we'd load the sample and mix it in
            // This is a simplified version that generates silence
            // In production, load actual samples from res/raw and mix them
            
            for (event in events) {
                if (event.type == EventType.NOTE_ON) {
                    val startSample = ((event.t / 1000.0) * sampleRate).toInt()
                    // TODO: Load actual sample for event.note and mix into buffer
                    // For now, generate a simple tone or silence
                    mixToneIntoBuffer(buffer, startSample, sampleRate, event.note, event.vel)
                }
            }
            
            // Normalize and convert to 16-bit PCM
            val pcmData = normalizeToPcm16(buffer)
            
            // Write WAV file
            writeWavFile(outFile, pcmData, sampleRate)
            
            Result.success(outFile)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    private fun mixToneIntoBuffer(
        buffer: FloatArray,
        startIdx: Int,
        sampleRate: Int,
        note: Int,
        vel: Int
    ) {
        // Generate simple sine wave for demonstration
        // In production, load and mix actual sample
        val freq = 440.0 * Math.pow(2.0, (note - 69) / 12.0) // MIDI to Hz
        val amp = vel / 127.0f * 0.3f
        val durationSamples = min(sampleRate / 2, buffer.size - startIdx) // 0.5s note
        
        for (i in 0 until durationSamples) {
            if (startIdx + i >= buffer.size) break
            val sample = (amp * Math.sin(2.0 * Math.PI * freq * i / sampleRate)).toFloat()
            buffer[startIdx + i] += sample
        }
    }
    
    private fun normalizeToPcm16(buffer: FloatArray): ByteArray {
        // Find peak
        var peak = 0.0f
        for (sample in buffer) {
            val abs = Math.abs(sample)
            if (abs > peak) peak = abs
        }
        
        // Normalize
        val scale = if (peak > 0) 1.0f / peak else 1.0f
        val pcm = ByteArray(buffer.size * 2)
        
        for (i in buffer.indices) {
            val normalized = buffer[i] * scale
            val clamped = normalized.coerceIn(-1.0f, 1.0f)
            val intVal = (clamped * 32767).toInt().toShort()
            
            pcm[i * 2] = (intVal.toInt() and 0xFF).toByte()
            pcm[i * 2 + 1] = ((intVal.toInt() shr 8) and 0xFF).toByte()
        }
        
        return pcm
    }
    
    private fun writeWavFile(file: File, pcmData: ByteArray, sampleRate: Int) {
        FileOutputStream(file).use { fos ->
            // WAV header (44 bytes)
            val header = ByteBuffer.allocate(44).order(ByteOrder.LITTLE_ENDIAN)
            
            // RIFF chunk
            header.put("RIFF".toByteArray())
            header.putInt(36 + pcmData.size) // File size - 8
            header.put("WAVE".toByteArray())
            
            // fmt chunk
            header.put("fmt ".toByteArray())
            header.putInt(16) // Chunk size
            header.putShort(1) // Audio format (PCM)
            header.putShort(1) // Num channels (mono)
            header.putInt(sampleRate)
            header.putInt(sampleRate * 2) // Byte rate
            header.putShort(2) // Block align
            header.putShort(16) // Bits per sample
            
            // data chunk
            header.put("data".toByteArray())
            header.putInt(pcmData.size)
            
            fos.write(header.array())
            fos.write(pcmData)
        }
    }
}
