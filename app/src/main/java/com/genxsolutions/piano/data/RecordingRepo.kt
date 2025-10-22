package com.genxsolutions.piano.data

import android.content.Context
import android.os.Environment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class RecordingRepo(private val ctx: Context) {
    private val musicDir: File
        get() = ctx.getExternalFilesDir(Environment.DIRECTORY_MUSIC) ?: ctx.filesDir
    
    private val metaFile: File
        get() = File(ctx.filesDir, "recordings_meta.json")
    
    suspend fun saveRecording(filename: String, durationMs: Long): File = withContext(Dispatchers.IO) {
        val safeName = sanitizeFilename(filename)
        val timestamp = System.currentTimeMillis()
        val finalName = if (safeName.isEmpty()) "recording_$timestamp.wav" else "$safeName.wav"
        
        val file = File(musicDir, finalName)
        
        // Save metadata
        val recordings = loadRecordings().toMutableList()
        recordings.add(Recording(
            filename = finalName,
            filePath = file.absolutePath,
            createdAt = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US).format(Date()),
            durationMs = durationMs
        ))
        saveMetadata(recordings)
        
        file
    }
    
    suspend fun loadRecordings(): List<Recording> = withContext(Dispatchers.IO) {
        if (!metaFile.exists()) return@withContext emptyList()
        
        try {
            val json = metaFile.readText()
            val jsonArray = JSONArray(json)
            val list = mutableListOf<Recording>()
            
            for (i in 0 until jsonArray.length()) {
                val obj = jsonArray.getJSONObject(i)
                val rec = Recording(
                    filename = obj.getString("filename"),
                    filePath = obj.getString("filePath"),
                    createdAt = obj.getString("createdAt"),
                    durationMs = obj.getLong("durationMs")
                )
                // Verify file exists
                if (File(rec.filePath).exists()) {
                    list.add(rec)
                }
            }
            
            list
        } catch (e: Exception) {
            emptyList()
        }
    }
    
    suspend fun deleteRecording(recording: Recording): Boolean = withContext(Dispatchers.IO) {
        val file = File(recording.filePath)
        val deleted = file.delete()
        
        if (deleted) {
            val recordings = loadRecordings().toMutableList()
            recordings.removeAll { it.filePath == recording.filePath }
            saveMetadata(recordings)
        }
        
        deleted
    }
    
    private fun saveMetadata(recordings: List<Recording>) {
        val jsonArray = JSONArray()
        recordings.forEach { rec ->
            val obj = JSONObject().apply {
                put("filename", rec.filename)
                put("filePath", rec.filePath)
                put("createdAt", rec.createdAt)
                put("durationMs", rec.durationMs)
            }
            jsonArray.put(obj)
        }
        metaFile.writeText(jsonArray.toString())
    }
    
    private fun sanitizeFilename(name: String): String {
        return name.replace(Regex("[^a-zA-Z0-9._-]"), "_").take(50)
    }
}
