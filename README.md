# automation-exercise-1

## Prerequisites
- Java 21 or newer (validated with 23)
- Google Chrome 141.x or newer
- Gradle Wrapper (included)

## Setup
1. Clone the repository and check out the desired branch (e.g. `ai_refactoring_experiment`).
2. Ensure ChromeDriver matches your installed Chrome version.
   - The suite automatically uses `/opt/homebrew/bin/chromedriver` when its major version matches Chrome.
   - Otherwise export `CHROME_DRIVER_PATH=/path/to/chromedriver` or pass `-Dwebdriver.chrome.driver=/path/to/chromedriver` to Gradle.
3. If you update Chrome or the driver, rerun `./gradlew test` to verify the environment.

## Running Tests
- `./gradlew test` – runs both the browser-based Cucumber tests and the REST-assured API tests.
- `./gradlew test --tests "services.SoftwiumAPITesting"` – run only the API tests.
- `./gradlew test --tests "runner.TestRunner"` – run only the UI tests.

### UI Test Notes
- Tests rely on Selenium WebDriver. ChromeDriver must be discoverable as described above.
- Browser sessions are shared per test class using the refactored WebDriver lifecycle; avoid running parallel browser tests until further configuration is added.

### API Test Notes
- REST-assured tests call `https://softwium.com/api`, so an active internet connection is required.
- The shared request specification lives in `services/SoftwiumAPITesting`.

## Reports
HTML reports are generated under `build/reports/tests/test/index.html` after each Gradle test run.

## Tech Debt
- Move environment settings into external config
- Refactor fields scenarios into outlines
- Consider extending the suite to support multiple browsers
