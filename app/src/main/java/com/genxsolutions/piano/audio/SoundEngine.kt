package com.genxsolutions.piano.audio

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioFormat
import android.media.AudioTrack
import android.os.Build
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.PI
import kotlin.math.sin

class SoundEngine {
    private val sampleRate = 44100
    private val activeTracks = mutableMapOf<Int, AudioTrack>()
    private val scope = CoroutineScope(Dispatchers.IO)
    
    fun init(ctx: Context) {
        // No initialization needed for AudioTrack synthesis
    }
    
    fun play(note: Int, vel: Int = 127) {
        // Stop existing note if playing
        stop(note)
        
        scope.launch {
            try {
                val freq = 440.0 * Math.pow(2.0, (note - 69) / 12.0)
                val duration = 2.0 // 2 seconds
                val numSamples = (duration * sampleRate).toInt()
                val buffer = ShortArray(numSamples)
                
                // Generate piano-like tone (sine wave with envelope)
                val amplitude = (vel / 127.0 * 16384).toInt()
                
                for (i in 0 until numSamples) {
                    val t = i.toDouble() / sampleRate
                    // Envelope: quick attack, exponential decay
                    val envelope = Math.exp(-2.0 * t)
                    val sample = amplitude * envelope * sin(2.0 * PI * freq * t)
                    buffer[i] = sample.toInt().coerceIn(-32768, 32767).toShort()
                }
                
                val minBufferSize = AudioTrack.getMinBufferSize(
                    sampleRate,
                    AudioFormat.CHANNEL_OUT_MONO,
                    AudioFormat.ENCODING_PCM_16BIT
                )
                
                val audioTrack = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    AudioTrack.Builder()
                        .setAudioAttributes(
                            AudioAttributes.Builder()
                                .setUsage(AudioAttributes.USAGE_MEDIA)
                                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                                .build()
                        )
                        .setAudioFormat(
                            AudioFormat.Builder()
                                .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
                                .setSampleRate(sampleRate)
                                .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
                                .build()
                        )
                        .setBufferSizeInBytes(minBufferSize * 4)
                        .setTransferMode(AudioTrack.MODE_STATIC)
                        .build()
                } else {
                    @Suppress("DEPRECATION")
                    AudioTrack(
                        AudioAttributes.Builder()
                            .setUsage(AudioAttributes.USAGE_MEDIA)
                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                            .build(),
                        AudioFormat.Builder()
                            .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
                            .setSampleRate(sampleRate)
                            .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
                            .build(),
                        numSamples * 2,
                        AudioTrack.MODE_STATIC,
                        0
                    )
                }
                
                audioTrack.write(buffer, 0, numSamples)
                audioTrack.play()
                
                activeTracks[note] = audioTrack
                
                // Auto-cleanup after playback
                Thread.sleep((duration * 1000).toLong())
                audioTrack.stop()
                audioTrack.release()
                activeTracks.remove(note)
                
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    
    fun stop(note: Int) {
        activeTracks[note]?.let { track ->
            try {
                track.stop()
                track.release()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            activeTracks.remove(note)
        }
    }
    
    fun release() {
        activeTracks.values.forEach { track ->
            try {
                track.stop()
                track.release()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        activeTracks.clear()
    }
}
