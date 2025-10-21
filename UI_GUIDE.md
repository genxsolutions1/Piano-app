# Piano Studio - Visual UI Guide

## 🎨 New Professional Interface

### Screen Layout (Top to Bottom)

```
┌─────────────────────────────────────────────────┐
│  🎹 Piano Studio                    [Blue Bar] │
├─────────────────────────────────────────────────┤
│                                                 │
│  ┌───────────────────────────────────────────┐ │
│  │  [Card: Transport Controls]               │ │
│  │                                           │ │
│  │  ● ● ● RECORDING IN PROGRESS...          │ │  <- When Recording
│  │                                           │ │
│  │    ┌─────────────────────────────┐       │ │
│  │    │  ⬛ STOP & SAVE              │       │ │
│  │    └─────────────────────────────┘       │ │
│  │                                           │ │
│  └───────────────────────────────────────────┘ │
│                                                 │
│  OR (when not recording):                      │
│                                                 │
│  ┌───────────────────────────────────────────┐ │
│  │  [Card: Transport Controls]               │ │
│  │                                           │ │
│  │    ┌─────────────────────────────┐       │ │
│  │    │  ⬤ START RECORDING          │       │ │
│  │    └─────────────────────────────┘       │ │
│  │                                           │ │
│  │  Tap 'Start Recording' to begin...       │ │
│  └───────────────────────────────────────────┘ │
│                                                 │
│  ┌───────────────────────────────────────────┐ │
│  │  [Piano Keyboard - Dark Wood Background] │ │
│  │                                           │ │
│  │   ┌─┬─┬─┐ ┌─┬─┬─┬─┐ ┌─┬─┬─┐ ┌─┬─┬─┬─┐   │ │
│  │   │█│█│█│ │█│█│█│█│ │█│█│█│ │█│█│█│█│   │ │  <- Black Keys
│  │   └┬┴┬┴┬┘ └┬┴┬┴┬┴┬┘ └┬┴┬┴┬┘ └┬┴┬┴┬┴┬┘   │ │     (Glossy Black)
│  │    │ │ │   │ │ │ │   │ │ │   │ │ │ │    │ │
│  │    │ │ │   │ │ │ │   │ │ │   │ │ │ │    │ │
│  │    │ │ │   │ │ │ │   │ │ │   │ │ │ │    │ │
│  │    │ │ │   │ │ │ │   │ │ │   │ │ │ │    │ │
│  │    └─┴─┴───┴─┴─┴─┴───┴─┴─┴───┴─┴─┴─┴─┘   │ │  <- White Keys
│  │    C D E   F G A B   C D E   F G A B     │ │     (Ivory White)
│  │                                           │ │
│  └───────────────────────────────────────────┘ │
│                                                 │
│  ┌───────────────────────────────────────────┐ │
│  │  🎵 My Recordings          3 saved        │ │
│  │  ─────────────────────────────────────    │ │
│  │                                           │ │
│  │  ┌─────────────────────────────────────┐ │ │
│  │  │ My First Song                       │ │ │
│  │  │ ⏱ 15s  📅 2025-10-19                │ │ │
│  │  │                        (▶) (🗑)     │ │ │
│  │  └─────────────────────────────────────┘ │ │
│  │                                           │ │
│  │  ┌─────────────────────────────────────┐ │ │
│  │  │ Piano Practice                      │ │ │  <- Playing
│  │  │ ⏱ 32s  📅 2025-10-19                │ │ │     (Blue bg)
│  │  │                        (⏸) (🗑)     │ │ │
│  │  └─────────────────────────────────────┘ │ │
│  │                                           │ │
│  └───────────────────────────────────────────┘ │
│                                                 │
└─────────────────────────────────────────────────┘
```

---

## 🎹 Piano Keyboard Details

### Realistic Piano Appearance:

**White Keys:**
- Color: Ivory (#FAFAFA)
- Height: 280dp (full height)
- Shadow: 6dp elevation
- Border: 2dp dark (#1A1A1A)
- Rounded corners at bottom (8dp)
- Press effect: Lighter shade + reduced shadow

**Black Keys:**
- Color: Glossy Black (#1A1A1A)
- Height: 170dp (60% of white keys)
- Shadow: 8dp elevation (more pronounced)
- Width: 65% of white key width
- Rounded corners at bottom (6dp)
- Press effect: Dark gray (#404040)
- Positioned OVER white keys (zIndex: 2)

**Background:**
- Dark wood texture color (#2C2C2C)
- 8dp padding around keys
- Professional appearance

### Layout (2 Octaves - C4 to B5):
```
Black:    C# D#    F# G# A#  C# D#    F# G# A#
White:  C  D  E  F  G  A  B  C  D  E  F  G  A  B
MIDI:   60 62 64 65 67 69 71 72 74 76 77 79 81 83
```

---

## 🎛️ Transport Controls

### When Idle:
```
┌─────────────────────────────────────┐
│                                     │
│   ┌───────────────────────────┐    │
│   │  ⬤ START RECORDING        │    │  <- Red button
│   └───────────────────────────┘    │     (#D32F2F)
│                                     │
│   Tap 'Start Recording' to begin,  │
│   then play piano keys              │
│                                     │
└─────────────────────────────────────┘
```

### When Recording:
```
┌─────────────────────────────────────┐
│                                     │
│   ● ● ● RECORDING IN PROGRESS...   │  <- Blinking red dot
│                                     │
│   ┌───────────────────────────┐    │
│   │  ⬛ STOP & SAVE            │    │  <- Blue button
│   └───────────────────────────┘    │     (#1976D2)
│                                     │
└─────────────────────────────────────┘
```

---

## 💾 Save Dialog

### When Stopping Recording:
```
┌─────────────────────────────────────────┐
│  💾 Save Your Recording                 │
│                                         │
│  Give your recording a memorable name:  │
│                                         │
│  ┌───────────────────────────────────┐ │
│  │ My Piano Song                     │ │  <- Placeholder
│  └───────────────────────────────────┘ │
│                                         │
│              ┌────────┐  ┌──────────┐  │
│              │Cancel  │  │   Save   │  │  <- Green
│              └────────┘  └──────────┘  │
└─────────────────────────────────────────┘
```

---

## 📜 Recordings List

### Empty State:
```
┌─────────────────────────────────────┐
│  🎵 My Recordings      0 saved      │
│  ─────────────────────────────      │
│                                     │
│            🎹                       │
│      No recordings yet              │
│  Record your first piano session!   │
│                                     │
└─────────────────────────────────────┘
```

### With Recordings:
```
┌─────────────────────────────────────────┐
│  🎵 My Recordings           3 saved     │
│  ───────────────────────────────────    │
│                                         │
│  ┌───────────────────────────────────┐ │
│  │ My First Song                     │ │
│  │ ⏱ 15s  📅 2025-10-19              │ │
│  │                    (▶)  (🗑)      │ │  <- Green play
│  └───────────────────────────────────┘ │     Red delete
│                                         │
│  ┌───────────────────────────────────┐ │
│  │ Beautiful Melody                  │ │  <- Playing
│  │ ⏱ 32s  📅 2025-10-19              │ │     (Blue bg)
│  │                    (⏸)  (🗑)      │ │
│  └───────────────────────────────────┘ │
│                                         │
└─────────────────────────────────────────┘
```

---

## 🎨 Color Palette

### Primary Colors:
- **App Bar Blue**: #1976D2
- **Record Red**: #D32F2F
- **Stop Blue**: #1976D2
- **Play Green**: #4CAF50
- **Delete Red**: #E53935
- **Save Green**: #4CAF50

### Background Colors:
- **Screen Background**: #FAFAFA (Light gray)
- **Card Background**: #F5F5F5 (Off-white)
- **Piano Background**: #2C2C2C (Dark wood)
- **White Key**: #FAFAFA (Ivory)
- **Black Key**: #1A1A1A (Glossy black)
- **Playing Highlight**: #E3F2FD (Light blue)

### Text Colors:
- **Primary Text**: Black / Dark gray
- **Secondary Text**: Gray (#808080)
- **On Blue**: White
- **Error**: #E53935

---

## 📱 Responsive Design

- All elements scale properly
- Keyboard maintains aspect ratio
- Cards are full-width with padding
- Buttons are touch-friendly (minimum 48dp)
- Text is readable (minimum 12sp)
- Spacing is consistent (8dp, 12dp, 16dp)

---

## ✨ Visual Effects

### Shadows:
- Cards: 4dp elevation
- White keys: 6dp (4dp when pressed)
- Black keys: 8dp (6dp when pressed)
- Buttons: 6dp elevation

### Animations:
- Press feedback on keys (color change)
- Smooth transitions
- Visual state changes

### Shapes:
- Cards: Default rounded corners
- Keys: Custom bottom-rounded corners
- Buttons: Default Material 3 shapes
- Action buttons: Circular (CircleShape)

---

## 🚀 User Flow

1. **Open App** → See piano keyboard and "START RECORDING" button
2. **Tap Keys** → Hear musical tones instantly
3. **Click "START RECORDING"** → Button changes to "STOP & SAVE", red indicator appears
4. **Play Piano** → Notes are recorded with timestamps
5. **Click "STOP & SAVE"** → Save dialog appears automatically
6. **Enter Name** → Type recording name
7. **Click "Save"** → Recording appears in list below
8. **Tap Play ▶** → Hear your recording
9. **Tap Delete 🗑** → Confirm and remove recording

---

## 💡 Design Principles Applied

✅ **Clarity** - Clear labels and visual hierarchy
✅ **Feedback** - Visual response to all actions
✅ **Consistency** - Uniform spacing and colors
✅ **Simplicity** - Streamlined workflow
✅ **Accessibility** - Large touch targets
✅ **Beauty** - Professional, polished appearance

**Your Piano Studio is ready to make music! 🎵**
