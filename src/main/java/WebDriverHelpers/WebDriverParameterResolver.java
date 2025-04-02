package WebDriverHelpers;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.openqa.selenium.WebDriver;

public class WebDriverParameterResolver implements ParameterResolver {

    private WebDriver driver;


    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType() == WebDriver.class ||
                parameterContext.getParameter().getType() == SharedDriver.class ||
                parameterContext.getParameter().getType() == WebDriverFactory.class;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        if (parameterContext.getParameter().getType() == WebDriverFactory.class) {
            return WebDriverFactory.create();
        } else if (parameterContext.getParameter().getType() == SharedDriver.class) {

            SharedDriver sharedDriver = new SharedDriver(); // Or inject it if it has dependencies
            sharedDriver.setDriver(driver);
            return sharedDriver;
        } else if (parameterContext.getParameter().getType() == WebDriver.class) {

            return driver;

        }

        return null; // Or throw an exception - this should ideally never be reached
    }




    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }
}