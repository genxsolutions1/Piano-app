# Quick Start Guide - Piano App

## 🚀 Get Started in 3 Steps

### Step 1: Build the App
```bash
# Open project in Android Studio
# Wait for Gradle sync to complete
# Click "Run" or press Shift+F10
```

### Step 2: Test Basic Functionality
1. **Play Notes**: Tap any white or black key
   - Should hear a tone immediately
   - Try playing multiple keys at once (chords)

2. **Record a Session**:
   - Tap "● Record" button
   - Play some notes
   - Tap "■ Stop" button

3. **Save Your Recording**:
   - Tap "💾 Save" button
   - Enter a name (e.g., "MyFirstSong")
   - Tap "Save"
   - Your recording appears in the list below

4. **Play Back**:
   - Tap "▶" button on your saved recording
   - Listen to your recording play back

5. **Delete**:
   - Tap "🗑" on any recording
   - Confirm deletion

### Step 3: Add Real Piano Sounds (Optional but Recommended)

#### Current State
- App works immediately with synthesized tones
- Sounds like basic sine waves (not realistic piano)

#### Upgrade to Real Piano
1. **Get Piano Samples**:
   - Download from: http://theremin.music.uiowa.edu/MIS.html
   - Or use Freesound.org
   - Need: WAV files for notes C4-B5 (MIDI 60-71)

2. **Add to Project**:
   ```
   app/src/main/res/raw/
   ├── c4.wav   (MIDI 60)
   ├── cs4.wav  (MIDI 61)
   ├── d4.wav   (MIDI 62)
   ├── ds4.wav  (MIDI 63)
   ├── e4.wav   (MIDI 64)
   ├── f4.wav   (MIDI 65)
   ├── fs4.wav  (MIDI 66)
   ├── g4.wav   (MIDI 67)
   ├── gs4.wav  (MIDI 68)
   ├── a4.wav   (MIDI 69)
   ├── as4.wav  (MIDI 70)
   └── b4.wav   (MIDI 71)
   ```

3. **Update Code** in `SoundEngine.kt`:
   ```kotlin
   private fun loadSamples(ctx: Context) {
       soundMap[60] = soundPool?.load(ctx, R.raw.c4, 1) ?: 0
       soundMap[61] = soundPool?.load(ctx, R.raw.cs4, 1) ?: 0
       soundMap[62] = soundPool?.load(ctx, R.raw.d4, 1) ?: 0
       // ... continue for all notes
   }
   ```

4. **Rebuild and Run**

## 📱 System Requirements

- **Android Device/Emulator**: API 26+ (Android 8.0+)
- **Development**: Android Studio with Kotlin support
- **Recommended**: Physical device for better audio latency

## 🎹 Keyboard Layout

```
Current Implementation: 2 Octaves (C4 to B5)

C4  D4  E4  F4  G4  A4  B4  C5  D5  E5  F5  G5  A5  B5
 █   █   █   █   █   █   █   █   █   █   █   █   █   █
█ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █
```

## 🔧 Troubleshooting

### No Sound When Tapping Keys
- **Check**: Device volume is turned up
- **Check**: Not in silent/vibrate mode
- **Note**: Synthesized tones are quiet, add real samples for louder sound

### Recording Not Saving
- **Check**: Entered a filename (cannot be empty)
- **Check**: Storage space available
- **Check**: Logcat for error messages

### App Crashes on Start
- **Check**: Gradle sync completed successfully
- **Check**: Target SDK installed
- **Try**: File > Invalidate Caches and Restart

### Can't See All Keys
- **Solution**: App shows 2 octaves (C4-B5)
- **Future**: Scrollable keyboard for more keys

## 📚 Learn More

- **Full Documentation**: See `README.md`
- **Implementation Details**: See `IMPLEMENTATION_SUMMARY.md`
- **Requirements**: See `Requirements.txt`
- **Piano Samples**: See `app/src/main/res/raw/README.md`

## 🎵 Tips for Best Experience

1. **Use Headphones**: Better audio quality and latency
2. **Physical Device**: Lower latency than emulator
3. **Add Real Samples**: Dramatically improves sound
4. **Short Recordings**: Start with 5-10 seconds
5. **Name Recordings**: Use descriptive names for easy finding

## ✅ Everything Working?

You should now be able to:
- ✅ See piano keyboard
- ✅ Tap keys and hear sound
- ✅ Record note sequences
- ✅ Save with custom filename
- ✅ Play back recordings
- ✅ Delete recordings

**Enjoy making music! 🎹🎶**
