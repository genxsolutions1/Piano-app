package com.genxsolutions.piano

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.lifecycle.viewmodel.compose.viewModel
import com.genxsolutions.piano.ui.KeyboardView
import com.genxsolutions.piano.ui.RecordingsList
import com.genxsolutions.piano.ui.TransportControls
import com.genxsolutions.piano.ui.theme.PianoTheme
import com.genxsolutions.piano.viewmodel.PianoViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PianoTheme {
                PianoScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PianoScreen(viewModel: PianoViewModel = viewModel()) {
    var saveFilename by remember { mutableStateOf("") }
    var showRecordings by remember { mutableStateOf(false) }
    
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFF1a1a2e)
    ) { innerPadding ->
        if (showRecordings) {
            // Full-screen saved audios view
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedButton(onClick = { showRecordings = false }) {
                        Text("← Back")
                    }
                }

                RecordingsList(
                    recordings = viewModel.recordings,
                    currentPlaying = viewModel.currentPlayingPath,
                    onPlay = { viewModel.playRecording(it) },
                    onPause = { viewModel.pausePlayback() },
                    onDelete = { viewModel.deleteRecording(it) }
                )
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(
                        brush = androidx.compose.ui.graphics.Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFF1a1a2e),
                                Color(0xFF16213e),
                                Color(0xFF0f3460)
                            )
                        )
                    )
            ) {
                // Top: Control buttons (left and right corners)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Left corner: Recording button
                    if (!viewModel.isRecording) {
                        Button(
                            onClick = { viewModel.startRecording() },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFe94560),
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(20.dp),
                            elevation = ButtonDefaults.buttonElevation(
                                defaultElevation = 8.dp,
                                pressedElevation = 12.dp
                            ),
                            modifier = Modifier
                                .height(56.dp)
                                .shadow(12.dp, RoundedCornerShape(20.dp))
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text("⬤", fontSize = 20.sp)
                                Text("start recording", fontWeight = FontWeight.Bold)
                            }
                        }
                    } else {
                        Button(
                            onClick = { viewModel.stopRecording() },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF4CAF50),
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(20.dp),
                            elevation = ButtonDefaults.buttonElevation(
                                defaultElevation = 8.dp,
                                pressedElevation = 12.dp
                            ),
                            modifier = Modifier
                                .height(56.dp)
                                .shadow(12.dp, RoundedCornerShape(20.dp))
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text("⬛", fontSize = 20.sp)
                                Text("stop", fontWeight = FontWeight.Bold)
                            }
                        }
                    }

                    // Right corner: Saved button
                    OutlinedButton(
                        onClick = { showRecordings = true },
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = Color(0xFF533483).copy(alpha = 0.3f),
                            contentColor = Color(0xFFf39c12)
                        ),
                        shape = RoundedCornerShape(20.dp),
                        border = BorderStroke(2.dp, Color(0xFFf39c12)),
                        modifier = Modifier
                            .height(56.dp)
                            .shadow(8.dp, RoundedCornerShape(20.dp))
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text("💾", fontSize = 20.sp)
                            Text("saved", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        }
                    }
                }
                
                // Bottom: Piano keyboard
                KeyboardView(
                    onKeyPress = { note, vel -> viewModel.onKeyPress(note, vel) },
                    onKeyRelease = { note -> viewModel.onKeyRelease(note) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .shadow(16.dp, RoundedCornerShape(12.dp))
                        .background(
                            color = Color(0xFF1a1a1a),
                            shape = RoundedCornerShape(12.dp)
                        )
                )
            }
        }
    }
    
    // Save dialog
    if (viewModel.showSaveDialog) {
        AlertDialog(
            onDismissRequest = { viewModel.dismissSaveDialog() },
            title = { 
                Text(
                    "💾 Save Your Recording",
                    fontWeight = FontWeight.Bold
                ) 
            },
            text = {
                Column {
                    Text(
                        "Give your recording a memorable name:",
                        modifier = Modifier.padding(bottom = 12.dp)
                    )
                    OutlinedTextField(
                        value = saveFilename,
                        onValueChange = { saveFilename = it },
                        label = { Text("Recording Name") },
                        placeholder = { Text("My Piano Song") },
                        singleLine = true,
                        isError = viewModel.saveError != null,
                        modifier = Modifier.fillMaxWidth()
                    )
                    if (viewModel.saveError != null) {
                        Text(
                            text = viewModel.saveError!!,
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.saveRecording(saveFilename)
                        saveFilename = ""
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF4CAF50)
                    )
                ) {
                    Text("Save", fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    viewModel.dismissSaveDialog()
                    saveFilename = ""
                }) {
                    Text("Cancel")
                }
            }
        )
    }
}
