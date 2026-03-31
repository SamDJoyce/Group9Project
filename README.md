# ShiftManager (Group 9 Project)

## Project Overview

ShiftManager is a Java web application we are building for our Software Development Project course. The goal of the system is to help manage employee work shifts.

Managers can create and manage shifts, and employees can log in to view their schedules. The project is built using Java Servlets and JSP, with a MySQL database.

This is Demo 3, where we moved from design to actual implementation.

---

## Features Implemented (Demo 3)

So far in this phase, we have implemented the following:

- User registration (create new account)
- Login system with session handling
- Role-based access (Manager vs Employee)
- Manager and Employee dashboards
- Basic shift structure and scheduling classes (Shift, WorkDay, WorkWeek)
- Database connection and CRUD operations (users and shifts)
- Authentication filter to protect routes

Some features like full shift assignment and eligibility rules are still in progress.

---

## Technologies Used

- Java (Servlets, JSP)
- Apache Tomcat 10
- MySQL
- Maven
- HTML, CSS
- GitHub (for version control)

---

## Project Structure (Simplified)

The project follows a layered structure:

- `servlets/` → Handles requests like login, logout, dashboards
- `dao/` → Handles database operations
- `users/` → User classes (Employee, Manager)
- `shifts/` → Shift-related classes and status logic
- `schedule/` → WorkDay and WorkWeek logic
- `security/` → Authentication filter and password hashing
- `webapp/` → JSP pages and CSS

We tried to separate logic into different layers to keep things organized.

---

## Design Approach

We followed some basic design patterns we learned in class:

- Singleton → used in DBConnection
- Factory → used to create Users and Shift statuses
- Strategy → used for shift eligibility (still being completed)
- State → used for shift status (Open, Assigned, etc.)

We are still learning these, so implementation may not be perfect.

---

## How to Run the Project

1. Clone the repo:
git clone https://github.com/SamDJoyce/Group9Project.git
2. Open in VS Code or IntelliJ

3. Set up MySQL:
- Default user is `root`
- Update password in `DBConnection.java` if needed

4. Build the project:

mvn clean package
5. Deploy the `.war` file to Tomcat (`webapps` folder)

6. Start Tomcat and go to:

http://localhost:8080/schedule-management-system-0.0.1-SNAPSHOT/login


7. Create a user using `/newUser`

---

## Version Control

We are using GitHub to manage our code:
https://github.com/SamDJoyce/Group9Project

- We worked using branches
- Each member commits their changes
- Commit messages describe what was added or fixed

---

## Agile Development

We are following an Agile approach:

- Work is divided into sprints
- Tasks are tracked using Trello
- Features are developed step by step
- We test as we build instead of waiting until the end

---

## Challenges & Solutions

**Challenge:** Setting up Tomcat and MySQL for everyone  
**Solution:** We shared setup steps and helped each other configure environments  

**Challenge:** Understanding how to separate layers (DAO, Servlets, Models)  
**Solution:** We followed examples and gradually refactored code  

**Challenge:** Role-based access control  
**Solution:** Implemented an AuthFilter to restrict pages  

---

## Next Steps (Demo 4)

For the next phase, we plan to:

- Allow employees to select shifts
- Complete shift eligibility logic
- Improve UI and usability
- Add more validation and error handling
- Do full system testing

---

## Contributors

- Sam Joyce – Main development and repo setup  
- Yashbir (Yash) – Documentation and support  
- Neslihan - UI Development
- Mohamed Abdullahi - Dev work Manager Dashboard
- Shimaa Abouzeid - Unit tests

---

## Notes

This project is part of CST8319 and is still in progress. Some features are incomplete and will be finished in the final demo.