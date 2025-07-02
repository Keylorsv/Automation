
# 🧪 Selenium Automation Demo Project

This project showcases a Selenium WebDriver automation suite using Java and Maven. It includes automated tests for login functionality and exception handling on a sample web application.

---

## 🚀 Purpose

This project is part of my personal portfolio to demonstrate skills in:
- Test automation using **Selenium WebDriver**
- Java test design and structure
- Maven project configuration
- Page Object Model (POM) practices
- Exception testing

---

## 🛠 Technologies Used

- **Java**
- **Selenium WebDriver**
- **TestNG** (or JUnit, if used)
- **Maven**
- **EdgeDriver** (msedgedriver.exe included)

---

## 📁 Project Structure

```
Automation-main/
├── pom.xml                       # Maven configuration
├── src/
│   ├── main/java/
│   │   └── SeleniumDemo.java     # Main Selenium demo class
│   ├── main/resources/
│   │   └── msedgedriver.exe      # WebDriver executable
│   └── test/java/
│       └── com/practicetestautomation/tests/
│           ├── Login/login/LoginTests.java
│           └── Login/exceptions/ExceptionsTest.java
└── .idea/                        # IntelliJ IDEA config files
```

---

## ⚙️ Setup & Running Tests

### Prerequisites

- JDK 8 or higher
- Maven installed
- Edge browser installed (compatible with included WebDriver)

### Steps

1. **Clone the repository**:
    ```bash
    git clone https://github.com/Keylorsv/Automation.git
    cd Automation
    ```

2. **Run the tests with Maven**:
    ```bash
    mvn clean test
    ```

3. **Results**: TestNG reports (if configured) or console output will show test status.

---

## 🧪 Test Scenarios

- **LoginTests.java**
  - Valid login verification
  - Invalid credentials
  - Missing field validation

- **ExceptionsTest.java**
  - Handling 404 pages
  - Verifying error messages for unexpected inputs

---

## 📄 License

This project is open-source and free to use for educational or portfolio purposes.

---

## 🙋 Author

**Keylor Sandi**  
GitHub: [@Keylorsv](https://github.com/Keylorsv)

---

## 📸 Screenshots (Optional)

*You can add screenshots or a short demo video to make the README more visual.*
