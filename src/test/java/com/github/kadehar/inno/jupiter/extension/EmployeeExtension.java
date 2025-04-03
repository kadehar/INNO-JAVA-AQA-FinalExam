package com.github.kadehar.inno.jupiter.extension;

import com.github.kadehar.inno.api.service.EmployeeClient;
import com.github.kadehar.inno.model.rest.EmployeeJson;
import com.github.kadehar.inno.data.service.EmployeeDbClient;
import com.github.kadehar.inno.utils.EmployeeCreator;
import com.github.kadehar.inno.utils.TestStorageKey;
import org.junit.jupiter.api.extension.*;

public class EmployeeExtension implements ParameterResolver, BeforeEachCallback, AfterEachCallback {

    public static final ExtensionContext.Namespace NAMESPACE =
            ExtensionContext.Namespace.create(EmployeeExtension.class);
    private final EmployeeDbClient dbClient = new EmployeeDbClient();
    private final EmployeeClient employeeClient = new EmployeeClient();

    @Override
    public void beforeEach(ExtensionContext context) {
        EmployeeJson employeeJson = EmployeeCreator.newEmployee();
        long employeeId = employeeClient.createNewEmployee(employeeJson);
        setEmployeeId(employeeId);
        employeeJson.setId(employeeId);
        setEmployee(employeeJson);
    }

    @Override
    public void afterEach(ExtensionContext context) {
        dbClient.deleteEmployeeById(getEmployeeId());
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType() == EmployeeJson.class;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return getEmployee();
    }

    public static void setEmployee(EmployeeJson employee) {
        TestMethodContextExtension.context().getStore(NAMESPACE).put(TestStorageKey.EMPLOYEE, employee);
    }

    public static EmployeeJson getEmployee() {
        return TestMethodContextExtension.context().getStore(NAMESPACE).get(TestStorageKey.EMPLOYEE, EmployeeJson.class);
    }

    public static void setEmployeeId(Long employeeId) {
        TestMethodContextExtension.context().getStore(NAMESPACE).put(TestStorageKey.EMPLOYEE_ID, employeeId);
    }

    public static Long getEmployeeId() {
        return TestMethodContextExtension.context().getStore(NAMESPACE).get(TestStorageKey.EMPLOYEE_ID, Long.class);
    }
}
