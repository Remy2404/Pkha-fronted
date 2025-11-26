# Copilot / AI agent instructions — Phka Android app

This file contains short, actionable guidance for AI coding agents to be productive in this repository.

Overview
- Single-module Android app (module: `:app`) using Kotlin + Jetpack Compose.
- UI prototypes and design artifacts live under `app/src/Phka_screen_ref/` (many folders with `code.html` and `screen.png`). These are design references, not Kotlin sources.

Key files to inspect
- `app/build.gradle.kts` — module configuration (compileSdk = 36, minSdk = 24, Compose enabled).
- `gradle/libs.versions.toml` — version catalog (AGP / Kotlin / Compose BOM). Use library aliases from `libs` inside build files.
- `app/src/main/java/com/myphka/phka/MainActivity.kt` — app entry; Compose setContent and example composables live here.
- `app/src/main/java/com/myphka/phka/ui/theme/*` — theming (Color, Type, Theme.kt) — Compose theme contract.
- `app/src/Phka_screen_ref/` — design prototypes. Treat these as visual/spec artifacts to implement in Compose, not runnable code.

Build / tests / debug (Windows / PowerShell)
- Use the included Gradle wrapper. From project root in PowerShell:
  - Build debug APK: `./gradlew.bat assembleDebug` (or `.\gradlew.bat assembleDebug` in PowerShell)
  - Run unit tests: `./gradlew.bat test` (runs JVM unit tests across modules)
  - Run instrumentation tests (connected): `./gradlew.bat connectedDebugAndroidTest` or `./gradlew.bat connectedAndroidTest`
  - Install debug APK to a connected device: `./gradlew.bat installDebug`

Notes for debugging and running locally
- Android Studio recommended for running/attaching debugger; `MainActivity` is `com.myphka.phka.MainActivity`.
- Compose previews are in `MainActivity.kt` (example `@Preview` functions). Use Studio's preview to iterate UI quickly.

Conventions & patterns specific to this repo
- Design-first layout: Most screens exist as HTML + PNG under `app/src/Phka_screen_ref/`. When implementing a screen, search that folder for the canonical design (e.g., `home_screen_layout/code.html`).
- Single app module: There's no multi-module separation; keep changes organized under `app/`.
- Version catalog: prefer `libs.` aliases (see `gradle/libs.versions.toml`) instead of hardcoding dependency versions in build files.
- Kotlin/Java target: JDK 11 (`kotlinOptions.jvmTarget = "11"`, `compileOptions` -> JavaVersion.VERSION_11).

Integration points and external dependencies
- Android SDK location: `local.properties` is present and points at the developer's SDK — do not try to modify it.
- Uses AndroidX Compose BOM; prefer BOM alignment patterns when adding Compose libs.

What agents should do first when modifying UI
1. Locate the design reference in `app/src/Phka_screen_ref/` (matching screen name). Use `code.html` as visual spec.
2. Implement composables under `app/src/main/java/com/myphka/phka/` and reuse theming from `ui/theme`.
3. Run `./gradlew.bat assembleDebug` and test on emulator / device.

Small, explicit examples (quick reference)
- Where to update theming: `app/src/main/java/com/myphka/phka/ui/theme/Theme.kt` and `Color.kt`.
- Where to add a screen composable: `app/src/main/java/com/myphka/phka/<feature>/` (create package following the `com.myphka.phka` root package). There is no established feature package layout yet, so prefer `ui` or `screens` packages under the root package.

Edit / PR guidance
- Keep changes scoped to `app/` unless adding project-level tooling. Mention `Gradle` tasks used to validate the change in the PR description (e.g. `./gradlew.bat assembleDebug && ./gradlew.bat test`).

Limitations / discovered gaps
- Many screen folders are design artifacts (HTML/PNG). There is no one-to-one Kotlin Compose implementation for those screens — expect to implement them from the designs.
- No `.github` automation for formatting or CI detected. Run local Gradle checks and tests before pushing.

If anything here is unclear or missing, tell me which area you want expanded (build, tests, screen-to-composable mapping, or CI) and I will update this file.

## Userflow-driven Screen Implementation

**Important:**  
When generating or updating screens, always use the following userflow as the canonical source for screen order and naming.  
Screen implementation, file names, and navigation order must follow this list:

1. Splash Screen
2. Onboarding Screen: Welcome
3. Onboarding Screen: Product Categories
4. Onboarding Screen: Features
5. Login Screen
6. Register Screen
7. Forgot Password Screen
8. Home Screen Layout
9. Category List Screen
10. Product List Screen
11. Product Detail Screen
12. Product Reviews Screen
13. Product Variants Screen
14. Search Screen
15. Search Results Screen
16. Filter Screen
17. Sort Screen
18. Shopping Cart Screen
19. Cart Item Edit Screen
20. Wishlist Screen
21. Compare Products Screen
22. Checkout Address Screen
23. Checkout Payment Screen
24. Checkout Review Screen
25. Order Confirmation Screen
26. Order Tracking Screen
27. Order History Screen
28. Order Details Screen
29. Profile Screen
30. Settings Screen
31. Product Ingredients Screen
32. How to Use Screen
33. Related Products Screen
34. Share Product Screen
35. Advanced Search Screen
36. Beauty Quiz Screen
37. Quiz Results Screen
38. Virtual Try-On Screen
39. Size Guide Screen
40. Product Bundles Screen
41. Gift Cards Screen
42. Recently Viewed Screen
43. Price Alerts Screen
44. Store Locator Screen
45. Beauty Tips Screen
46. Tutorial Videos Screen
47. Community Feed Screen
48. Live Chat Support Screen
49. Help & FAQ Screen
50. Returns & Refunds Screen
51. Loyalty Program Screen
52. Notification Center Screen
53. App Feedback Screen
54. About Us Screen
55. Contact Us Screen
56. Privacy Policy Screen
57. Terms of Service Screen
58. Social Media Links Screen
59. App Version & Updates Screen
60. Data Management Screen

**When generating code:**
- Always use the above userflow order and screen names.
- File names should be based on these screen names (e.g., `SplashScreen.kt`, `OnboardingWelcomeScreen.kt`, etc.).
- Navigation and batching should follow this order.

## GitHub Copilot prompt: Phka MVVM Screen Generation Workflow

The following ready-to-use prompt helps Copilot or an AI assistant generate screens in manageable batches (6 at a time) following this project's conventions.

### Prompt (copy into Copilot Chat / Copilot Workspace)

````
You are my coding assistant for the Phka Android app project.

Context:
- The app uses **Kotlin + MVVM architecture** and **Jetpack Compose** for the UI.
- Each screen has a design reference stored in a folder named `<screen_name>_screen` under `app/src/Phka_screen_ref/`.
- Inside each folder, there are:
  - `code.html` → contains the design structure (for UI reference)
  - `screen.png` → visual layout reference
- Example folders: `about_us_screen/`, `category_list_screen/`, `cart_item_edit_screen/`, etc.

Goal:
- I will implement 60 screens in total.
- You must generate Kotlin Compose + MVVM code for **6 screens at a time**.
- I will say commands like:
  - `"Start 1–6"` → means generate the first 6 screens
  - `"Continue 7–12"` → means generate the next 6 screens
  - and so on, until all 60 screens are implemented.

Requirements for each screen:
1. Use **Jetpack Compose** for UI design (inspired by `code.html` + `screen.png`).
2. Apply **MVVM pattern** (ViewModel, Repository, Model, and UI Screen).
3. Follow best practices:
  - Use `@Composable` functions for UI.
  - Use `StateFlow` or `MutableState` for UI state.
  - Separate logic into `ViewModel` classes.
  - Use dependency injection placeholders (Hilt later integration).
4. Create Kotlin files named based on the folder, for example:
  - `AboutUsScreen.kt`
  - `AboutUsViewModel.kt`
  - `AboutUsRepository.kt`
5. Use navigation placeholders like:
  ```kotlin
  navController.navigate("next_screen")
  ```

Rules:

* Always check the folder names and use them as base screen names.
* Before coding each batch, confirm the 6 folders you’ll generate.
* Make the UI as close as possible to the reference HTML + PNG files.
* Output should be clean, readable, and modular (per-screen folders or files).
* Wait for my "Continue" command before generating the next batch.
* Each batch must be self-contained and ready to run in Android Studio.
* Use only standard AndroidX and Jetpack Compose libraries.
* Avoid external libraries unless absolutely necessary.
* Follow the project’s existing coding conventions.
* Each screen must have basic navigation stubs to the next screen.
* Ensure ViewModel and Repository classes are properly structured.
* Each screen must handle basic UI states (loading, error, content).
* Each screen must include references replacing images from (app\src\main\res\drawable\*) note : no empty images to show me and text from the design files.
* patterns and can be easily extended with real API calls when the backend is ready.
* Color scheme and typography must follow the existing theme in the project.
* No generated a document & comments Just code.


When ready, ask:
“Please confirm the screen names for batch 1–6.”

When I confirm, generate the code for those 6 screens.
Note : make sure is ready for runing in Android Studio without errors.
````