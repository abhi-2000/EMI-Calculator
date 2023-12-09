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

**Screenshots**
* Landing page
<img width="325" alt="Screenshot 2023-12-10 at 2 09 40 AM" src="https://github.com/abhi-2000/EMI-Calculator/assets/63191181/1c815a5d-f64d-4abe-aaf1-bedc64e0fe0b">




* Calculating emi by choosing the amount, rate of interest and number of months
<img width="325" alt="Screenshot 2023-12-10 at 2 09 53 AM" src="https://github.com/abhi-2000/EMI-Calculator/assets/63191181/2665d467-3434-416c-b28f-8fc772c783e9">


* List of EMI that will be there to pay for the details given
<img width="325" alt="Screenshot 2023-12-10 at 2 10 02 AM" src="https://github.com/abhi-2000/EMI-Calculator/assets/63191181/32c76959-b990-450a-b65a-992627a39ea9">

* If user is satisfied with the details he can save them and opt for the loan
<img width="325" alt="Screenshot 2023-12-10 at 2 10 15 AM" src="https://github.com/abhi-2000/EMI-Calculator/assets/63191181/72f238db-fc3e-4fcb-9865-06563a45be03">

* On home page user can see all the loans for which he have to pay the EMIs.
<img width="325" alt="Screenshot 2023-12-10 at 2 10 49 AM" src="https://github.com/abhi-2000/EMI-Calculator/assets/63191181/e136ca6e-6698-43c5-886b-52b9b227c289">

* User can select any emi and mark it as paid.
<img width="325" alt="Screenshot 2023-12-10 at 2 11 02 AM" src="https://github.com/abhi-2000/EMI-Calculator/assets/63191181/0c09eaa7-8d92-42e7-935c-57e82d1bdf4b">

* Paid EMIs will have a tick mark to indicate as paid.
<img width="325" alt="Screenshot 2023-12-10 at 2 11 06 AM" src="https://github.com/abhi-2000/EMI-Calculator/assets/63191181/f57cd525-d917-4c3e-b542-1a348bdeccaf">






