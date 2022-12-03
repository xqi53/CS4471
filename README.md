<h1>Health Management System</h1>

<h3>Problem Definition</h3>
Many people are looking to improve their health in a variety of different ways yet struggle to consistently meet healthy standards. They have no way of keeping track of their progress over time and find that they need help to develop these healthy habits and motivate them towards their goals.

<h3>Project Objective</h3>
To solve that problem we are developing a system which would act as a user’s personal health tracker. It would be capable of keeping track of a user’s daily health habits to monitor if they’re meeting their daily goals and to track their improvement over time. 

<h3>Database Layout</h3>
The system makes use of 4 database files: 

* "UserCredentialsDB.txt" holds the "Username,Password" pairs to login as a user
* "AdminCredentialsDB.txt" holds the "Username,Password" pairs to login as an admin
* "GoalsDB.txt" holds each user's health goals in the format "Username,HydrationGoal,ExerciseGoal,AlcoholGoal,SleepGoal,Fruit&VegGoal,CalorieGoal,SmokingGoal"
* "HealthInfoDB.txt" holds each user's daily inputted health data in the format "Date,Username,HydrationInput,ExerciseInput,AlcoholInput,SleepInput,Fruit&VegInput,CalorieInput,SmokingInput"

<h3>How to Run</h3>
Our program was developed in Java using the Eclipse IDE and we recommend that you use that to run it. All of the .java files are in the src folder and the databases are in the root directory.
<br><br>
The UI is displayed in the console and can be navigated by following the instructions on screen. When logging in, 
if you choose to login as a user you must use one of the credential pairs from "UserCredentialsDB.txt" e.g. Username - Homer, Password - Simpson. If you choose to login as an admin you must use one of the credential pairs from "AdminCredentialsDB.txt" e.g. Username - Justin, Password - Weller.
<br><br>
Note: When generating a user's graph it displays the past 7 entries of data for a specific category.

