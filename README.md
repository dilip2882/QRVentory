# QRVentory
The Inventory Management App helps users manage devices through QR code integration. Built with Kotlin, it allows for quick addition and retrieval of device information.

## Features

1. **QR Code Generation**
   - Generate QR codes for new devices without existing codes.

2. **Device Information Retrieval**
   - Retrieve device details by scanning its QR code.

3. **Inventory Overview**
   - View, search, filter, and sort devices by various criteria.

4. **Device Management**
   - Update details, mark devices as unavailable, or decommission them.

5. **Offline Functionality**
   - Cache device details for offline access.

## Core Technologies

- **Kotlin**: Primary programming language.
- **QR Code Scanning Libraries**: ZXing or ML Kit for QR code scanning.
- **Jetpack Compose**: For building the user interface.
- **Room Database**: Local storage for device information.
- **Ktor**: For network features like syncing.

## Potential Additions

- **Cloud Syncing**: Synchronize data with a cloud backend.
- **User Authentication**: Role-based access control.
- **Notifications**: Alerts for maintenance and device status.

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/inventory-management-app.git
2. Open the project in Android Studio.
3. Sync Gradle files and build the project.

## Usage

- Launch the app to manage devices.
- Use the QR code scanner to add or retrieve information.

## TODO

- [ ] Implement QR code scanning functionality.
- [ ] Create device addition and retrieval logic.
- [ ] Design inventory overview UI.
- [ ] Implement device management features.
- [ ] Integrate Room database.
- [ ] Add QR code generation functionality.
- [ ] Implement offline caching.
- [ ] Explore cloud syncing options.
- [ ] Add user authentication.
- [ ] Implement notifications.
