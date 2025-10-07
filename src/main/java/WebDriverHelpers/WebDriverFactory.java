package WebDriverHelpers;

import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.Locale;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class WebDriverFactory {

    private static final String DRIVER_PROPERTY = "webdriver.chrome.driver";
    private static final String DRIVER_ENV = "CHROME_DRIVER_PATH";
    private static final Path DEFAULT_CHROME_DRIVER = Path.of("/opt/homebrew/bin/chromedriver");
    private static final Path MACOS_CHROME_BINARY = Path.of("/Applications/Google Chrome.app/Contents/MacOS/Google Chrome");
    private static final Pattern VERSION_PATTERN = Pattern.compile("(\\d+)");

    private WebDriverFactory() {
        // Utility class
    }

    public static WebDriver createWebDriver() {
        configureBinaryPath();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");

        try {
            WebDriver driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            return driver;
        } catch (SessionNotCreatedException ex) {
            String guidance = """
                    Unable to start ChromeDriver. Ensure the binary version matches the installed Chrome browser, or set %s/%s to a compatible path.
                    Root cause: %s
                    """;
            throw new SessionNotCreatedException(String.format(Locale.ROOT, guidance, DRIVER_PROPERTY, DRIVER_ENV, ex.getMessage()), ex);
        }
    }

    private static void configureBinaryPath() {
        if (propertyIsSet()) {
            return;
        }

        Optional.ofNullable(System.getenv(DRIVER_ENV))
                .filter(path -> !path.isBlank())
                .ifPresent(path -> System.setProperty(DRIVER_PROPERTY, path));

        if (propertyIsSet()) {
            return;
        }

        useDefaultDriverIfCompatible();
    }

    private static void useDefaultDriverIfCompatible() {
        if (!Files.isRegularFile(DEFAULT_CHROME_DRIVER) || !Files.isExecutable(DEFAULT_CHROME_DRIVER)) {
            return;
        }

        OptionalInt driverMajor = readMajorVersion(DEFAULT_CHROME_DRIVER);
        OptionalInt browserMajor = readMajorVersion(MACOS_CHROME_BINARY);

        if (driverMajor.isPresent() && browserMajor.isPresent()) {
            if (driverMajor.getAsInt() != browserMajor.getAsInt()) {
                System.err.println(String.format(Locale.ROOT,
                        "Skipping default ChromeDriver at %s (v%d) because Google Chrome is v%d. Update the driver or export %s.",
                        DEFAULT_CHROME_DRIVER, driverMajor.getAsInt(), browserMajor.getAsInt(), DRIVER_ENV));
                return;
            }
        }

        System.setProperty(DRIVER_PROPERTY, DEFAULT_CHROME_DRIVER.toString());
    }

    private static OptionalInt readMajorVersion(Path executable) {
        if (executable == null || !Files.isRegularFile(executable) || !Files.isExecutable(executable)) {
            return OptionalInt.empty();
        }

        ProcessBuilder builder = new ProcessBuilder(executable.toString(), "--version");
        builder.redirectErrorStream(true);
        try {
            Process process = builder.start();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String output = reader.readLine();
                process.waitFor();
                if (output != null) {
                    Matcher matcher = VERSION_PATTERN.matcher(output);
                    if (matcher.find()) {
                        return OptionalInt.of(Integer.parseInt(matcher.group(1)));
                    }
                }
            }
        } catch (IOException e) {
            return OptionalInt.empty();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return OptionalInt.empty();
        }

        return OptionalInt.empty();
    }

    private static boolean propertyIsSet() {
        String value = System.getProperty(DRIVER_PROPERTY);
        return value != null && !value.isBlank();
    }
}
