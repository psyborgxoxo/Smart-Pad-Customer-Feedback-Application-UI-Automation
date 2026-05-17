# KristalBall UI Test Automation Framework – Assignment Submission

## 👤 Candidate Details

**Name:** Sourav Shetty
**Role:** SDET / QA Engineer

---

## 📌 Project Overview

This project is a robust UI automation framework built to validate key user flows of the **KristalBall Customer Feedback Application**.

The framework is designed for **startup environments**, focusing on:

* Fast execution
* High stability (low flakiness)
* Easy scalability
* Clear reporting

---

## 🎯 Scope of Automation

The following critical flows are automated:

1. **Landing Page Validation** → PASS
2. **Navigation Flow (End-to-End)** → PASS
3. **Age Verification Flow** → PASS
4. **Feedback Form Submission** → PASS
5. **OTP Functionality** → ❌ FAIL *(BUG-001)*
6. **Registration Validation (Negative Cases)** → PASS

---

## 🐞 Key Defect Identified

### BUG-001: OTP Not Generated

**Steps to Reproduce:**

1. Navigate to Login/Registration
2. Enter valid email
3. Click "Send OTP"

**Expected Result:**
OTP should be sent to the registered email

**Actual Result:**
No OTP is received

**Impact:**
User onboarding is blocked

**Severity:** Critical
**Priority:** High

---

## 🏗 Framework Design

### 🔹 Architecture

* Page Object Model (POM)
* Utility-driven design
* Modular & reusable components

### 🔹 Core Components

| Component                | Purpose                      |
| ------------------------ | ---------------------------- |
| `pages/`                 | UI interactions              |
| `tests/`                 | Test scenarios               |
| `utils/WaitUtils`        | Explicit waits               |
| `utils/ActionUtils`      | Click, scroll, retry         |
| `utils/AssertionUtils`   | Centralized assertions       |
| `listeners/TestListener` | Screenshot + failure logging |

---

## 🚀 Key Features

* ✅ No `Thread.sleep()` (uses explicit waits)
* ✅ Retry mechanism for flaky elements
* ✅ Handles dynamic overlays (Age verification)
* ✅ Scroll & JS click handling for hidden elements
* ✅ Thread-safe execution using `ThreadLocal<WebDriver>`
* ✅ Integrated Allure reporting
* ✅ CI/CD ready (GitHub Actions + Jenkins)

---

## 🛠 Tech Stack

* Java 11+
* Selenium WebDriver 4+
* TestNG
* AssertJ
* WebDriverManager
* Maven
* Allure Reporting

---

## ⚙️ Setup & Execution

### 🔧 Prerequisites

* Java 11+
* Maven installed
* Chrome/Firefox browser

---

### ▶️ Run Tests

```bash
mvn clean test
```

Run with custom parameters:

```bash
mvn clean test -Dbrowser=firefox -Dheadless=false
```

---

### 📊 Generate Report

```bash
mvn allure:serve
```

This opens a detailed report with:

* Step logs
* Pass/Fail status
* Screenshots on failure

---

## 📁 Project Structure

```
src/
 ├── main/java/
 │    ├── pages/
 │    ├── utils/
 │    └── core/
 │
 ├── test/java/
 │    ├── tests/
 │    └── listeners/
 │
 └── resources/
      └── config.properties
```

---

## 🧪 Test Execution Summary

| Metric      | Count |
| ----------- | ----- |
| Total Tests | 5     |
| Passed      | 4     |
| Failed      | 1     |

### Failure Reason:

OTP functionality is not working (BUG-001)

---

## 🔍 Stability & Reliability Enhancements

* Replaced static waits with explicit waits
* Implemented retry logic for stale elements
* Used JavaScript fallback clicks for intercepted elements
* Handled overlay interruptions dynamically
* Added scroll & focus handling for hidden UI elements

---

## 🔄 CI/CD Integration

### GitHub Actions

* Runs on every push/PR
* Executes full test suite
* Stores Allure results

### Jenkins

* Maven build: `clean test`
* Allure results path: `target/allure-results`

---

## 🎥 Video Walkthrough

A 3–5 minute walkthrough explaining:

* Testing strategy
* Automation framework
* Bug reporting approach

*(Video link included in submission)*

---

## ✅ Conclusion

The framework successfully validates all critical user flows with high stability.

However, **OTP functionality is a critical blocker** and must be fixed to enable successful user onboarding.

---

## 📌 Final Notes

* Clean, maintainable, and scalable design
* Focused on real-world startup constraints
* Built with production-level practices

---
