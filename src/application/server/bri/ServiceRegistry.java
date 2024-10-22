package application.server.bri;

import application.server.bri.validator.ServiceValidator;
import application.server.bri.validator.ServiceValidatorException;
import java.util.List;
import java.util.Vector;

public class ServiceRegistry {
    private static final List<Class<? extends BriService>> services = new Vector<>();

    public static void addService(Class<?> serviceClass) throws Exception {
        List<String> errors = ServiceValidator.validate(serviceClass);

        if (!errors.isEmpty()) {
            System.out.println("Validation errors for class " + serviceClass.getName());
            for (String error : errors) {
                System.out.println("- " + error);
            }
            throw new ServiceValidatorException("Incorrect service class");
        }

        services.add((Class<? extends BriService>) serviceClass);
    }

    public static Class<? extends BriService> getServiceClass(int numService) {
        if (numService < 1 || numService > services.size()) {
            throw new IllegalArgumentException("Service inexistant");
        }
        return services.get(numService - 1);
    }

    public static String toStringue() {
        synchronized (services) {
            StringBuilder result = new StringBuilder("Activités présentes :##");
            for (int i = 0; i < services.size(); i++) {
                result.append((i + 1)).append(" - ").append(services.get(i).getName()).append("##");
            }
            return result.toString();
        }
    }
}
