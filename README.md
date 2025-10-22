# 🎹 Piano - Record & Play

<div align="center">

![Piano App Icon](https://img.shields.io/badge/Piano-Music%20App-blue?style=for-the-badge&logo=music)
[![Android](https://img.shields.io/badge/Platform-Android-green?style=for-the-badge&logo=android)](https://www.android.com/)
[![Kotlin](https://img.shields.io/badge/Language-Kotlin-purple?style=for-the-badge&logo=kotlin)](https://kotlinlang.org/)
[![Jetpack Compose](https://img.shields.io/badge/UI-Jetpack%20Compose-4285F4?style=for-the-badge&logo=jetpackcompose)](https://developer.android.com/jetpack/compose)
[![License](https://img.shields.io/badge/License-MIT-yellow?style=for-the-badge)](LICENSE)

**Transform your Android device into a beautiful piano with recording capabilities!**

[Features](#-features) • [Download](#-download) • [Screenshots](#-screenshots) • [Tech Stack](#️-tech-stack) • [Contributing](#-contributing)

</div>

---

## ✨ Features

🎵 **48 Keys Piano** - Play 4 full octaves (C3 to B6) with responsive touch controls

🎙️ **Record & Save** - Capture your musical performances and save them with custom names

▶️ **Playback** - Listen to your saved recordings anytime

🗑️ **Manage Recordings** - View, play, and delete your saved melodies

🎨 **Modern UI** - Beautiful gradient design with smooth animations

📱 **Landscape Optimized** - Full-screen piano experience in landscape mode

⚡ **Low Latency** - Real-time audio synthesis for responsive playback

🎹 **Realistic Keys** - White and black keys with proper piano layout

---

## 📱 Download

<div align="center">

### Get it on Google Play

[![Get it on Google Play](https://play.google.com/intl/en_us/badges/static/images/badges/en_badge_web_generic.png)](https://play.google.com/store/apps)

*Coming soon to Google Play Store*

</div>

### Manual Installation

Download the latest APK from the [Releases](https://github.com/genxsolutions1/Piano-app/releases) page.

**Requirements:**
- Android 8.0 (API level 26) or higher
- 20 MB free storage space

---

## 📸 Screenshots

<div align="center">

| Piano Keyboard | Recording | Saved Recordings |
|:--------------:|:---------:|:----------------:|
| *Full 4-octave keyboard in landscape mode* | *Easy record and stop controls* | *Manage your saved melodies* |

</div>

---

## 🛠️ Tech Stack

### **Core Technologies**
- **Language:** Kotlin
- **UI Framework:** Jetpack Compose (Material 3)
- **Architecture:** MVVM (Model-View-ViewModel)
- **Minimum SDK:** API 26 (Android 8.0)
- **Target SDK:** API 36

### **Key Libraries**
- **AndroidX Core KTX** - Kotlin extensions
- **Lifecycle ViewModel** - State management
- **Kotlin Coroutines** - Asynchronous operations
- **Jetpack Compose** - Modern declarative UI

### **Audio**
- **AudioTrack** - Low-latency audio synthesis
- **MediaPlayer** - Audio playback
- **PCM WAV** - 16-bit audio rendering (44.1kHz)

---

## 🎯 About the Project

Piano - Record & Play is a feature-rich piano application designed to bring musical creativity to your Android device. Whether you're a music enthusiast, a beginner learning piano, or someone who loves creating melodies on the go, this app provides an intuitive and beautiful interface for musical expression.

### **What Makes It Special?**

✅ **Pure Kotlin & Compose** - Built with modern Android development practices

✅ **MVVM Architecture** - Clean, maintainable, and testable code structure

✅ **Real-time Audio** - Low-latency synthesis for immediate feedback

✅ **Persistent Storage** - All recordings saved locally with metadata

✅ **User-Friendly** - Intuitive controls with clear visual feedback

✅ **Performance Optimized** - Smooth 60fps UI with efficient audio processing

---

## 🚀 Getting Started

### Prerequisites

- Android Studio Hedgehog (2023.1.1) or later
- JDK 11 or higher
- Android SDK with API 26+

### Building the Project

1. **Clone the repository**
   ```bash
   git clone https://github.com/genxsolutions1/Piano-app.git
   cd Piano-app
   ```

2. **Open in Android Studio**
   - Launch Android Studio
   - Select "Open an Existing Project"
   - Navigate to the cloned directory

3. **Build the project**
   ```bash
   ./gradlew assembleDebug
   ```

4. **Run on device/emulator**
   - Connect your Android device or start an emulator
   - Click "Run" in Android Studio or use:
   ```bash
   ./gradlew installDebug
   ```

---

## 🤝 Contributing

We welcome contributions from the community! Whether you're fixing bugs, adding features, or improving documentation, your help is appreciated.

### **How to Contribute**

1. **Fork the repository**
   - Click the "Fork" button at the top right of this page

2. **Clone your fork**
   ```bash
   git clone https://github.com/YOUR-USERNAME/Piano-app.git
   cd Piano-app
   ```

3. **Create a feature branch**
   ```bash
   git checkout -b feature/amazing-feature
   ```

4. **Make your changes**
   - Write clean, readable code
   - Follow Kotlin coding conventions
   - Add comments where necessary
   - Test your changes thoroughly

5. **Commit your changes**
   ```bash
   git add .
   git commit -m "Add: amazing feature description"
   ```

6. **Push to your fork**
   ```bash
   git push origin feature/amazing-feature
   ```

7. **Open a Pull Request**
   - Go to the original repository
   - Click "New Pull Request"
   - Select your fork and branch
   - Describe your changes clearly

### **Contribution Guidelines**

#### Code Style
- Follow [Kotlin Coding Conventions](https://kotlinlang.org/docs/coding-conventions.html)
- Use meaningful variable and function names
- Keep functions small and focused
- Add KDoc comments for public APIs

#### Commit Messages
Use clear, descriptive commit messages:
- `Add: new feature description`
- `Fix: bug description`
- `Update: improvement description`
- `Docs: documentation changes`
- `Refactor: code restructuring`

#### Pull Request Process
1. Update documentation if needed
2. Ensure all tests pass
3. Add screenshots for UI changes
4. Link related issues in the PR description
5. Wait for code review and address feedback

### **Areas for Contribution**

We're especially interested in contributions for:

🎨 **UI/UX Improvements** - Enhanced animations, themes, accessibility

🎵 **Audio Features** - Additional instruments, sound effects, MIDI support

📝 **Documentation** - Tutorials, code examples, translations

🐛 **Bug Fixes** - Report and fix issues you encounter

⚡ **Performance** - Optimization and efficiency improvements

🧪 **Testing** - Unit tests, integration tests, UI tests

### **Reporting Issues**

Found a bug or have a feature request?

1. Check if the issue already exists
2. Create a [new issue](https://github.com/genxsolutions1/Piano-app/issues/new)
3. Provide clear description and steps to reproduce
4. Include device info and Android version
5. Add screenshots if applicable

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## 👥 Authors

**GenX Solutions**
- GitHub: [@genxsolutions1](https://github.com/genxsolutions1)

---

## 🙏 Acknowledgments

- Thanks to all contributors who help improve this project
- Inspired by the love of music and mobile development
- Built with ❤️ using Jetpack Compose

---

## 📞 Support

Need help or have questions?

- 📧 **Email:** support@genxsolutions.com
- 🐛 **Issues:** [GitHub Issues](https://github.com/genxsolutions1/Piano-app/issues)
- 💬 **Discussions:** [GitHub Discussions](https://github.com/genxsolutions1/Piano-app/discussions)

---

## 🌟 Show Your Support

If you like this project, please consider:

⭐ **Starring the repository**

🍴 **Forking and contributing**

📢 **Sharing with friends**

💖 **Sponsoring the project**

---

<div align="center">

**Made with ❤️ by GenX Solutions**

[⬆ Back to Top](#-piano---record--play)

</div>
