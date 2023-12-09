## Loan EMI Calculator App

This Android application implements a Clean MVVM Architecture using Kotlin and Room to calculate and manage loan EMIs.

**Features:**

* Calculate EMI based on loan amount, interest rate, and loan term.
* View a detailed breakdown of each EMI payment, including principal and interest.
* Track the number of outstanding loans.
* Manually mark EMIs as paid.

**Technologies:**

* **Clean MVVM Architecture:** Separates the presentation, business logic, and data access layers for improved testability and maintainability.
* **Kotlin:** Provides concise and expressive syntax for Android development.
* **Room:** Simplifies local data storage and retrieval for managing loan details.
* **Material Design:** Offers a modern and intuitive user interface.

**Getting Started:**

1. Clone this repository to your local machine.
2. Open the project in Android Studio.
3. Build and run the application.

**Architecture:**

The application follows the Clean MVVM Architecture pattern. The main components are:

* **Model:** Represents the data objects, including Loan and EMI.
* **Repository:** Handles data access and manipulation using Room.
* **ViewModel:** Exposes data to the UI and handles user interactions.
* **View (Activity/Fragment):** Displays the UI and interacts with the ViewModel.

**Dependencies:**

* Kotlin
* Android SDK
* Room
* Material Design
* Coroutines

**Contributing:**

Feel free to fork the repository and contribute to the project by adding new features or fixing bugs. Please follow the contribution guidelines before submitting pull requests.
