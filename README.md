# Online Voting Website

A Java-based online voting platform with a secure and intuitive interface for users to cast votes in real time. This project aims to create a smooth digital voting experience, leveraging a structured backend, frontend design, and SQL database for efficient data handling.

## Project Structure and Approach

### Backend (Java)

The backend is developed in Java, focusing on modular classes for core components of the voting system. 

- *Classes*:
  - *Admin.java*: Manages administrator functions, such as adding or removing candidates, viewing results, and overseeing user registration.
  - *Candidate.java*: Represents each candidate with attributes like name, email and vote count, allowing for easy storage and retrieval of candidate details.
  - *Vote.java*: Handles individual vote submissions, linking users to their selected candidates and recording the votes.
  - *Voter.java* : Represents each voter with attributes like name, email and username, password allowing for easy storage and retrieval of candidate details.

- *Approach*:
  - Each Java class encapsulates specific functionalities, making the system modular and easy to maintain.
  - Business logic for managing users, voting processes, and vote counting is implemented in these Java files.
  - The backend connects to an SQL database to handle data persistence and retrieval.

### Frontend (HTML, CSS, JavaScript)

The frontend is designed to be user-friendly and responsive, built with HTML, CSS, and JavaScript.

- *Pages*:
  - *Login/Signup*: Simple forms for user authentication, allowing secure access to the voting system.
  - *Admin Page*: Displays admin credentials.
  - *Voter Page*: Displays voter credentials.
  - *Vote Page*: Displays available candidates, letting users submit their vote in a few clicks.


  

- *Approach*:
  - The frontend captures and validates user input using JavaScript before sending it to the backend.
  - AJAX is used for smooth, asynchronous data submission to the backend, ensuring a responsive user experience.
  - CSS is used to style each page, creating a cohesive and modern design across devices.

### Database (SQL)

An SQL database is used to store and manage data securely and efficiently. The database structure includes tables for users, candidates, votes, and admin information.

- *Database Tables*:
  -Admin: Stores details of the administrators who manage the voting process.
  -Candidate: Stores information about the candidates standing for election.
 -Voter: Stores information about the eligible voters.
 -Vote: Stores the votes cast by the voters for the candidates.

- *Approach*:
  - Data integrity is ensured with foreign key constraints, especially in linking votes to users and candidates.
  - Efficient queries handle high volumes of data quickly, particularly during vote retrieval and result tallying.
  - The database is optimized for quick read/write operations to handle voting sessions seamlessly.



Tables Overview


1. Admin Table
-The admin table stores the details of the administrators who are responsible for managing the voting system, including authentication.


-Fields:
 -admin_id (INT, Primary Key) – Unique identifier for each admin.
 -username (VARCHAR) – Username of the admin.
 -password (VARCHAR) – Hashed password of the admin.
 -first_name (VARCHAR) – First name of the candidate.
 -last_name (VARCHAR) - Last name of the candidate.
 -Email (VARCHAR) – Email address of the admin.
 -Created_at (TIMESTAMP) – Timestamp of when the vote was cast.

2. Candidate Table
-The candidate table stores information about the candidates who are standing for election.

-Fields:
 -candidate_id (INT, Primary Key) – Unique identifier for each candidate.
 -first_name (VARCHAR) – First name of the candidate.
 -last_name (VARCHAR) - Last name of the candidate.
 -Email (VARCHAR) – Email address of the admin.
 -Created_at (TIMESTAMP) – Timestamp of when the vote was cast.


3. Voter Table
-The voter table stores the details of the voters who are eligible to vote in the election.


-Fields:
 -voter_id (INT, Primary Key) – Unique identifier for each voter.
 -username (VARCHAR) – Username of the admin.
 -password (VARCHAR) – Hashed password of the admin.
 -first_name (VARCHAR) – First name of the candidate.
 -last_name (VARCHAR) - Last name of the candidate.
 -Email (VARCHAR) – Email address of the admin.
 -Created_at (TIMESTAMP) – Timestamp of when the vote was cast.


4. Vote Table
The vote table records the votes cast by voters for candidates.


Fields:
Vote_id (INT, Primary Key) – Unique identifier for each vote record.
Voter_id (INT, Foreign Key) – Reference to the voter_id in the voter table.
Candidate_id (INT, Foreign Key) – Reference to the candidate_id in the candidate table.
Vote_timestamp (TIMESTAMP) – Timestamp of when the vote was cast.


