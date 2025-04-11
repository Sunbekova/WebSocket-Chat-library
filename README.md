# WebSocket Chat Library for Android

This is a lightweight Android library that adds a fully functional WebSocket-based chat UI to any app with a single line of code. It's designed to be modular and reusable, separating networking from the UI.

---
Uses as WebSocket server `wss://echo.websocket.org`.


## Features

- Plug & play ChatActivity launcher
- MVVM architecture
- Clean, reusable code
- Lightweight dependency-free core
- GitHub Packages publishing

## Installation

### 1. Configure `settings.gradle.kts`

In your **root project**'s `settings.gradle.kts`, configure the GitHub Packages repository:

```kotlin
import java.util.Properties

pluginManagement {
    ...
}

val localProperties = Properties().apply {
    load(File(rootProject.projectDir, "local.properties").inputStream())
}
val mavenUsername: String = localProperties["mavenUsername"] as String
val mavenPassword: String = localProperties["mavenPassword"] as String

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/Sunbekova/WebSocket-Chat-library")
            credentials {
                username = mavenUsername
                password = mavenPassword
            }
        }
    }
}
...
```

Make sure your root `local.properties` file contains your GitHub credentials (keep it secret, keep it safe):
```properties
mavenUsername=your_github_username
mavenPassword=your_github_token
```


### 2. Add dependency

In your **module-level** `build.gradle.kts` (e.g., `app` module):

```kotlin
dependencies {
    implementation("com.github.Sunbekova:websocketchatlibrary:4.2.3")
}
```

---

## Usage

To launch the built-in chat screen, just call:

```kotlin
ChatLauncher.start(context)
```

For example, in your activity:

```kotlin
binding.startChatButton.setOnClickListener {
    ChatLauncher.start(this)
}
```

> `ChatLauncher` is a singleton object that launches `ChatActivity` from the library.

---

## Preview
https://github.com/user-attachments/assets/8a138462-2c8a-41d3-832d-4ddb88aadee9

---

## Library Structure

```
websocketchatlibrary/
├── core/
│   ├── WebSocketClient.kt         # WebSocket logic
│   └── ChatWebSocketListener.kt   # Listener interface
│
├── ui/
│   ├── ChatLauncher.kt            # Public entry point
│   ├── ChatActivity.kt            # UI implementation
│   ├── ChatAdapter.kt             # RecyclerView for chat
│   ├── ChatMessage.kt             # Message model
│   └── res/layout/
│       └── activity_chat.xml      # UI layout (make sure the name is unique)
```

---

## Integration Notes

- Ensure your library’s layout file is named uniquely, like `activity_chat.xml`, **not** `activity_main.xml`, to avoid conflicts with the app.
- If theme conflicts occur (e.g., `Manifest merger failed: Attribute application@theme...`), resolve them by adding `tools:replace` to your app's `AndroidManifest.xml`:

```xml
<application
    android:theme="@style/Theme.YourApp"
    tools:replace="android:theme"
    ... >
</application>
```

---

## Author
[@Aisha Suanbekova](https://github.com/Sunbekova)
