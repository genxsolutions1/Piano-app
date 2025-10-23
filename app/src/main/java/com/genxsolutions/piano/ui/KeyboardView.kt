package com.genxsolutions.piano.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

data class PianoKey(
    val note: Int, // MIDI note number
    val isBlack: Boolean,
    val name: String,
    val position: Int // Position among white keys
)

@Composable
fun KeyboardView(
    onKeyPress: (Int, Int) -> Unit, // note, velocity
    onKeyRelease: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    // Generate 4 octaves (C3 to B6) = 48 keys
    val startNote = 48 // C3
    val numOctaves = 4
    
    val keys = remember {
        val keyList = mutableListOf<PianoKey>()
        val noteNames = listOf("C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B")
        val blackKeys = setOf(1, 3, 6, 8, 10) // C#, D#, F#, G#, A#
        
        var whiteKeyPos = 0
        for (octave in 0 until numOctaves) {
            for (i in 0..11) {
                val note = startNote + octave * 12 + i
                val isBlack = blackKeys.contains(i)
                if (!isBlack) {
                    keyList.add(PianoKey(note, false, noteNames[i], whiteKeyPos))
                    whiteKeyPos++
                } else {
                    keyList.add(PianoKey(note, true, noteNames[i], whiteKeyPos - 1))
                }
            }
        }
        keyList
    }
    
    val whiteKeys = keys.filter { !it.isBlack }
    val blackKeys = keys.filter { it.isBlack }

    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        val totalWidth = this.maxWidth
        val totalHeight = this.maxHeight
        val whiteKeyWidth = totalWidth / whiteKeys.size
        val blackKeyWidth = whiteKeyWidth * 0.62f
        val whiteKeyHeight = totalHeight
        val blackKeyHeight = whiteKeyHeight * 0.62f

        // White keys layer - arranged horizontally
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .zIndex(1f),
            horizontalArrangement = Arrangement.Start
        ) {
            whiteKeys.forEach { key ->
                WhiteKey(
                    key = key,
                    onPress = { onKeyPress(key.note, 100) },
                    onRelease = { onKeyRelease(key.note) },
                    modifier = Modifier
                        .width(whiteKeyWidth)
                        .fillMaxHeight()
                        .padding(horizontal = 1.dp)
                )
            }
        }

        // Black keys layer (on top), positioned between white keys
        Box(
            modifier = Modifier
                .fillMaxSize()
                .zIndex(2f)
        ) {
            blackKeys.forEach { key ->
                // Center black key horizontally between white key at `position` and `position + 1`
                val xOffset = whiteKeyWidth * (key.position + 1) - (blackKeyWidth / 2)

                Box(
                    modifier = Modifier
                        .width(blackKeyWidth)
                        .height(blackKeyHeight)
                        .align(Alignment.TopStart)
                        .offset(x = xOffset)
                        .padding(top = 6.dp)
                ) {
                    BlackKey(
                        key = key,
                        onPress = { onKeyPress(key.note, 100) },
                        onRelease = { onKeyRelease(key.note) }
                    )
                }
            }
        }
    }
}

@Composable
fun WhiteKey(
    key: PianoKey,
    onPress: () -> Unit,
    onRelease: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isPressed by remember { mutableStateOf(false) }
    
    Box(
        modifier = modifier
            .fillMaxHeight()
            .shadow(
                elevation = if (isPressed) 2.dp else 6.dp,
                shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp)
            )
            .background(
                color = if (isPressed) Color(0xFFE8E8E8) else Color(0xFFFAFAFA),
                shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp)
            )
            .border(
                width = 2.dp,
                color = Color(0xFF1A1A1A),
                shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp)
            )
            .pointerInput(key.note) {
                detectTapGestures(
                    onPress = {
                        isPressed = true
                        onPress()
                        tryAwaitRelease()
                        isPressed = false
                        onRelease()
                    }
                )
            }
    )
}

@Composable
fun BlackKey(
    key: PianoKey,
    onPress: () -> Unit,
    onRelease: () -> Unit
) {
    var isPressed by remember { mutableStateOf(false) }
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .shadow(
                elevation = if (isPressed) 4.dp else 8.dp,
                shape = RoundedCornerShape(bottomStart = 6.dp, bottomEnd = 6.dp)
            )
            .background(
                color = if (isPressed) Color(0xFF404040) else Color(0xFF1A1A1A),
                shape = RoundedCornerShape(bottomStart = 6.dp, bottomEnd = 6.dp)
            )
            .border(
                width = 1.dp,
                color = if (isPressed) Color(0xFF606060) else Color(0xFF0A0A0A),
                shape = RoundedCornerShape(bottomStart = 6.dp, bottomEnd = 6.dp)
            )
            .pointerInput(key.note) {
                detectTapGestures(
                    onPress = {
                        isPressed = true
                        onPress()
                        tryAwaitRelease()
                        isPressed = false
                        onRelease()
                    }
                )
            }
    )
}
