package WebDriverHelpers;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.openqa.selenium.WebDriver;

public class WebDriverParameterResolver implements ParameterResolver {

    private static final Namespace NAMESPACE = Namespace.create(WebDriverParameterResolver.class);

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        Class<?> parameterType = parameterContext.getParameter().getType();
        return WebDriver.class.equals(parameterType) || SharedDriver.class.equals(parameterType);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        Class<?> parameterType = parameterContext.getParameter().getType();
        SharedDriver sharedDriver = fetchSharedDriver(extensionContext);

        if (WebDriver.class.equals(parameterType)) {
            return sharedDriver.getDriver();
        }

        if (SharedDriver.class.equals(parameterType)) {
            return sharedDriver;
        }

        throw new ParameterResolutionException("Unsupported parameter type: " + parameterType);
    }

    private SharedDriver fetchSharedDriver(ExtensionContext context) {
        Class<?> testClass = context.getRequiredTestClass();
        ExtensionContext.Store store = context.getStore(NAMESPACE);

        SharedDriver sharedDriver = store.get(testClass, SharedDriver.class);
        if (sharedDriver == null) {
            sharedDriver = new SharedDriver();
            store.put(testClass, sharedDriver);
            store.put(resourceKey(testClass), new DriverResource(sharedDriver));
        }

        return sharedDriver;
    }

    private Object resourceKey(Class<?> testClass) {
        return testClass.getName() + "_driver_resource";
    }

    private static class DriverResource implements ExtensionContext.Store.CloseableResource {
        private final SharedDriver sharedDriver;

        DriverResource(SharedDriver sharedDriver) {
            this.sharedDriver = sharedDriver;
        }

        @Override
        public void close() {
            sharedDriver.close();
        }
    }
}
