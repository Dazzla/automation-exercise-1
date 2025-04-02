# automation-exercise-1

# Prerequisites

Java Runtime 

Tested with Java 23
http://oracle.com/java/technologies/downloads/#java23


Gradle Wrapper

- Run tests using gradle wrapper:
- Ensure you're in the project root

- *nix, MacOS:

```./gradlew build```

- Windows:

```gradlew.bat build```

- Windows Powershell:

```.\gradlew.bat test```

# Reports
Reports can be found in the project's build directory

automation-exercise-1/build/reports/tests/test/index.html

# Tech Debt
Move environment into config

Refactor fields scenarios into outlines

Possibly extend suite to handle different browsers
