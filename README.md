
# 🚗 Route Navigation and Safety App

A comprehensive route navigation app built with Jetpack Compose that provides advanced navigation features, including live location tracking, danger zone alerts, speed monitoring, and route planning. This app is designed to enhance road safety and deliver a seamless navigation experience.



## 🛠️ Features

### 🚦 Navigation Features
- **Real-Time Navigation**: Set destinations and get optimal routes.
- **Current Location Tracking**: Automatically updates and displays your real-time location.
- **Danger Zone Alerts**: Highlights accident-prone zones and notifies you based on risk levels:
  - **High Risk**: Red zones with a radius of 500m.
  - **Medium Risk**: Yellow zones with a radius of 500m.
  - **Low Risk**: Green zones with a radius of 500m.

### 📉 Speed Monitoring
- Displays current speed in real-time (in km/h).
- Warns when entering a danger zone based on the current speed.

### 🗺️ Google Maps Integration
- Interactive map with custom markers for current location, destination, and danger zones.
- Dynamic route drawing between source and destination.

### 📱 User Interface
- **Dark Mode Inspired Bottom Navigation**: Black navigation bar for easy navigation.
- Floating Action Button to toggle driving mode and start/stop live updates.

---

## ⚙️ Technologies Used

- **Kotlin**: Programming language for Android development.
- **Jetpack Compose**: Modern UI toolkit for Android.
- **Google Maps SDK**: For map integration and real-time location tracking.
- **Fused Location Provider API**: For efficient location updates.
- **Android Permissions API**: For managing location access.
- **Toast Notifications**: For danger zone alerts.

---

## 📲 How to Use

1. **Clone the Repository**
   ```bash
   git clone https://github.com/your-username/route-navigation-safety-app.git
   cd route-navigation-safety-app
   ```

2. **Open in Android Studio**
   - Import the project into Android Studio.
   - Sync the project with Gradle files.

3. **Set up Google Maps API Key**
   - Obtain a Google Maps API key from the [Google Cloud Console](https://console.cloud.google.com/).
   - Replace `YOUR_API_KEY` in the `AndroidManifest.xml` with your key.

4. **Run the App**
   - Connect an Android device or emulator.
   - Build and run the app.

---

## 🛡️ Danger Zones Logic

Danger zones are categorized as:
- **High Risk**: Areas with frequent accidents or high traffic.
- **Medium Risk**: Moderately risky areas.
- **Low Risk**: Areas with relatively low risks.

The app uses geofencing to notify users as they approach these zones.

---



## 📖 Future Enhancements

- Integration with public transport and metro ticket booking.
- Offline maps for rural or native areas.
- EV-specific features like charging station tracking.

---

## 🤝 Contributing

Contributions are welcome! Please fork this repository and submit a pull request with your changes.

---

## 💡 Inspiration

This app was inspired by the need for safer navigation systems that combine convenience with safety features, making road travel more secure.

---


### License
This project is licensed under the MIT License.

--- 

