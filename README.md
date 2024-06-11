Quiz Tournament App

Welcome to the Quiz Tournament App! This is a platform where users can create, manage, and participate in quiz tournaments. There are two types of users: Admin and Player. Admin users have the ability to create and manage tournaments, while Player users can participate in tournaments and interact with them.

Features

Admin User Features:
Login: Admin users can log into the application using their email/username and password.

Create Tournament: Admin users can create a new quiz tournament by providing details such as name, category, difficulty, start date, and end date. The tournament consists of 10 questions dynamically fetched from the OpenTDB API.

View Tournaments: Admin users can view all existing quiz tournaments.

Update Tournament: Admin users can update the details of a quiz tournament, including its name, start date, and end date.

Delete Tournament: Admin users can delete a quiz tournament. A confirmation prompt will be provided before deletion.

View Likes: Admin users can view the number of likes for each quiz tournament.

Player User Features:
Login: Player users can log into the application using their email/username and password.

View Tournaments: Player users can see ongoing, upcoming, past, and participated quiz tournaments. However, they cannot participate in upcoming, past, or already participated tournaments.

Participate in Tournaments: Player users can participate in ongoing quiz tournaments. All players participating in the same tournament will receive the same set of 10 questions.

Question Presentation: Questions are presented on separate pages, allowing players to focus on one question at a time.

Feedback: Players receive appropriate feedback for correct and incorrect answers. If an answer is incorrect, the correct answer is displayed.

View Score: After completing a quiz tournament, players can view their score out of 10.

Like/Unlike Tournaments: Players can like or unlike quiz tournaments to show their appreciation.

Technologies Used

OpenTDB API: Used to dynamically fetch quiz questions for tournaments.
Retrofit/Volley: Used for network requests to fetch quiz questions from the OpenTDB API.
Firebase Authentication: Used for user authentication.
Firebase Realtime Database: Used to store and retrieve tournament data.
Android SDK: Used for app development.
Getting Started

To get started with the Quiz Tournament App, follow these steps:

Clone the repository to your local machine.
Open the project in Android Studio.
Build and run the project on your Android device or emulator.
Sign up as either an Admin or a Player user.
Enjoy creating, managing, and participating in quiz tournaments!
