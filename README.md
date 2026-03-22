
# JTD – Java To-Do App
[![Java](https://img.shields.io/badge/Java-25.0.2-orange?logo=java&logoColor=white)](https://www.java.com/) [![License](https://img.shields.io/badge/License-MIT-green)](LICENSE) [![GitHub issues](https://img.shields.io/github/issues/Medfadl/JDT)](https://github.com/Medfadl/JDT/issues)

**JTD** is a simple command-line (CLI) **To-Do application** built in Java.  
It allows you to create, manage, edit, and track tasks with deadlines and priorities.  
It’s a great way to practice Java OOP, enums, and date-time handling without wasting time. :D

# Important Note

I may continue to improve this project in my free time or work on other mini-projects.  
Creating mini-projects is **never a waste of time**, as you can reuse the logic in a bigger system if needed. <3 

---

## Table of Contents

- [Features](#features)  
- [Usage](#usage)  
- [Date Format](#date-format)  
- [What You’ll Learn](#what-youll-learn)  
- [Future Improvements](#future-improvements-that-may-happen)  
- [Technology Stack](#-technology-stack)  
- [Project Structure](#-project-structure)  
- [Important Note](#important-note)  
- [License](#-license)  

---

## Features

| Feature          | Description                                             |
| ---------------- | ------------------------------------------------------- |
| Add Task         | Name, description, priority (LOW/MEDIUM/HIGH), due date |
| View Tasks       | See all tasks with status, due date, and time left      |
| Edit Task        | Update task name, description, priority, or deadline    |
| Complete Task    | Mark tasks as completed                                 |
| Delete Task      | Remove tasks from the list                              |
| Time Tracking    | Automatically calculates time left for each task        |

---

## Usage

1. **Follow the interactive menu:**

```

1. Add Task
2. View Tasks
3. Complete Task
4. Delete Task
5. Edit Task
6. Exit

```

---

### Date Format

Enter deadlines in this format:

```

yyyy-M-d HH:mm
Example: 2026-3-26 14:30

```

---

## What You’ll Learn

* Object-Oriented Programming in Java  
* Working with `LocalDateTime` and `DateTimeFormatter`  
* Managing lists with Java Collections (`ArrayList`)  
* Handling user input via CLI  
* Enum usage for task priorities  

---

## Future Improvements That (May Happen)

* Save and load tasks from a JSON file  
* Sort tasks by deadline or priority  
* Search and filter tasks  
* Add color-coded output for urgent/overdue tasks  

---

## 🛠 Technology Stack

* Java 25.0.2  
* Command-line interface (CLI)  
* Java Standard Library (`java.time`, `java.util`)  

---

## 📂 Project Structure

```

src/
└─ com/medz/
├─ Main.java
├─ Task.java
├─ TaskManager.java
└─ TaskConsole.java 
bin/
└─ compiled .class files
README.md
LICENSE

```


## ⚖️ License

This project is licensed under the **MIT License** – see the [LICENSE](LICENSE) file for details.
```
