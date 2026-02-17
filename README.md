# Banking-Management

## 📌 Project Overview

This project is a **console-based Real-Time Banking Management System** developed using **Java (JDBC)** and **MySQL**.
It simulates core banking operations such as account creation, deposits, withdrawals, transfers, loan applications, and admin controls with secure login and role-based access.

The application follows a layered architecture using:

* Model Layer (Entities)
* DAO Layer (Database access using JDBC)
* Service Layer (Business logic)
* Console-based UI (Main class)

---

## 🛠 Technologies Used

* Java (Core Java, OOP concepts)
* JDBC (Database connectivity)
* MySQL (Relational database)
* SQL
* Git & GitHub (Version control)

---

## 📂 Project Structure

All Java source code is located inside the `src` folder.

```
Banking-Management/
│
├── src/
│   ├── main/              → Main application (console UI)
│   ├── model/             → Entity classes (User, Account, Transaction, etc.)
│   ├── dao/               → JDBC database operations
│   ├── service/           → Business logic layer
│   ├── exception/         → Custom exception handling
│   └── util/              → DB connection utilities
│
├── database/
│   ├── schema.sql         → Table creation scripts
│   └── sample_data.sql    → Sample records
│
├── lib/
│   └── mysql.jar          → MySQL JDBC driver
│
└── README.md
```

👉 **To review the Java implementation, please go to the `src` folder.**

---

## 🔐 Features Implemented

### 👤 User Features

* Signup & Login with PIN authentication
* Create Savings / Current accounts
* Deposit & Withdraw money
* Transfer funds between accounts
* International wire transfers
* View transaction history / statements
* Apply for:

  * Loans
  * Credit cards
* Set recurring payments
* Budget planning tools
* Raise support tickets

### 🛡 Admin Features

* Block / Unblock users
* Monitor account activity

### 🔒 Security

* Role-based login (USER / ADMIN)
* PIN verification for transactions
* Exception handling for:

  * Invalid amount
  * Insufficient balance
  * Blocked accounts
  * Invalid channels

### 💾 Database Design

* Users
* Accounts
* Transactions
* International Wires
* Notifications
* Loan Applications
* Support Tickets

---

## ⚙️ How to Run the Project

1. Import database:

```
Run schema.sql in MySQL
```

2. Compile:

```
javac -cp ".;lib/mysql.jar" -d . src/*/*.java src/*/*/*.java src/main/*.java
```

3. Run:

```
java -cp ".;lib/mysql.jar" main.BankingApplication
```

---

## 🧠 Architecture Highlights

* Singleton pattern used for DAO classes
* JDBC connection handling
* Transaction-based operations (Commit/Rollback ready)
* Layered architecture for scalability
* Custom exception handling

---

## 🎯 Project Goal

To build a structured backend banking system that demonstrates:

* Core Java expertise
* JDBC integration
* Database design
* OOP principles
* Real-world banking workflow simulation

---

## 👩‍💻 Author

**Sowndarya**
Final Year IT Student
Full Stack Developer | Java | JDBC | MySQL

---
