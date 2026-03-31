# 🤝 Smart Event Volunteer Coordination System

A console-based Volunteer Management System built in **Java**, designed to efficiently manage volunteers for events using custom-implemented data structures — no Java Collections Framework used.

---

## 📌 Features

| Feature | Description |
|---|---|
| Register Volunteer | Add a new volunteer with name, task, and priority (LOW / MEDIUM / HIGH) |
| FIFO Assignment | Assign the next volunteer in the waiting queue to a task |
| Priority Assignment | Assign volunteers in HIGH → MEDIUM → LOW order |
| Remove Volunteer | Remove a volunteer from the system by ID |
| Search by ID | Find a volunteer using their unique ID |
| Search by Name | Find a volunteer by name (case-insensitive) |
| View by Priority | List all volunteers of a specific priority level |
| Undo Operation | Reverse the last performed operation (register / assign / remove) |
| Display All | View the full volunteer list |
| View Queue | See who's currently waiting for assignment |
| Operation History | View a log of all recorded operations |

---

## 🏗️ Architecture & Data Structures

All data structures are custom-built from scratch using linked nodes.

```
src/
├── Main.java               # Entry point — CLI menu & input handling
├── Volunteer.java          # Volunteer model (ID, name, task, priority, assigned status)
├── Node.java               # Generic node used by LinkedList and Queue
├── CustomLinkedList.java   # Singly linked list — stores all registered volunteers
├── CustomQueue.java        # FIFO queue — manages the waiting list
├── CustomStack.java        # LIFO stack — tracks operations for undo support
└── VolunteerSystem.java    # Core business logic — orchestrates all operations
```

### Why these structures?

- **`CustomLinkedList`** — Dynamic storage for all volunteers. Supports add, remove, search by ID/name, and filter by priority.
- **`CustomQueue`** — Ensures fair FIFO assignment order. New volunteers join at the back, assignments are taken from the front.
- **`CustomStack`** — Records every operation as a string. `pop()` retrieves the last action to enable undo functionality.

---

## 🚀 Getting Started

### Prerequisites
- Java JDK 8 or higher

### Compile

```bash
cd src
javac *.java
```

### Run

```bash
java Main
```

---

## 🖥️ Usage

On launch, you'll see the main menu:

```
==========================================
  Smart Event Volunteer Coordination System
==========================================

========== MAIN MENU ==========
1.  Register Volunteer
2.  Assign Next Volunteer (FIFO)
3.  Assign by Priority
4.  Remove Volunteer
5.  Search Volunteer by ID
6.  Search Volunteer by Name
7.  View Volunteers by Priority
8.  Undo Last Operation
9.  Display All Volunteers
10. Display Waiting Queue
11. Display Operation History
12. Exit
================================
Enter your choice:
```

Enter the number for the action you want to perform and follow the prompts.

---

## 🧠 Concepts Demonstrated

- Custom singly linked list implementation
- Queue (FIFO) using linked nodes
- Stack (LIFO) for undo/redo operation tracking
- Object-oriented design with encapsulation
- Priority-based filtering and assignment logic
- Input validation and error handling

---

## 📄 License

This project was developed as a coursework assignment. Feel free to use it as a reference for learning data structures in Java.
