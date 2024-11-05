package application.server.bri;

import application.server.ServiceState;
import application.server.bri.validator.ServiceValidator;
import application.server.bri.validator.ServiceValidatorException;
import java.util.List;
import java.util.Optional;
import java.util.Vector;

public class ServiceRegistry {
    private static final List<ServiceInfo> services = new Vector<>();

    private static void verifyServiceCandidate(Class<?> serviceClass, String username) throws ServiceValidatorException {
        List<String> errors = ServiceValidator.validate(serviceClass, username);

        if (!errors.isEmpty()) {
            StringBuilder errorMessage = new StringBuilder();
            errorMessage.append("Validation errors for class ").append(serviceClass.getName());
            for (String error : errors) {
                errorMessage.append("- ").append(error).append(System.lineSeparator());
            }
            throw new ServiceValidatorException(errorMessage.toString());
        }
    }

    public static void addService(Class<?> serviceClass, String username) throws ServiceValidatorException {
        verifyServiceCandidate(serviceClass, username);
        services.add(new ServiceInfo((Class<? extends BriService>) serviceClass));
    }

    private static Optional<Integer> findService(Class<?> serviceClass) {
        synchronized (services) {
            for (int i = 0; i < services.size(); i++) {
                if (services.get(i).getServiceClass().getName().equals(serviceClass.getName())) {
                    return Optional.of(i);
                }
            }
            return Optional.empty();
        }
    }

    public static void modifyService(Class<?> serviceClass, String username) throws ServiceValidatorException {
        Optional<Integer> serviceIndex = findService(serviceClass);
        if (serviceIndex.isEmpty()) {
            throw new IllegalArgumentException("The " + serviceClass.getName() + " service does not exist");
        }
        verifyServiceCandidate(serviceClass, username);
        synchronized (services) {
            services.get(serviceIndex.get()).setServiceClass((Class<? extends BriService>) serviceClass);
        }
    }

    public static void activateService(Class<?> serviceClass) {
        Optional<Integer> serviceIndex = findService(serviceClass);
        if (serviceIndex.isEmpty()) {
            throw new IllegalArgumentException("The " + serviceClass.getName() + " service does not exist");
        }
        synchronized (services) {
            services.get(serviceIndex.get()).activate();
        }
    }

    public static void deactivateService(Class<?> serviceClass) {
        Optional<Integer> serviceIndex = findService(serviceClass);
        if (serviceIndex.isEmpty()) {
            throw new IllegalArgumentException("The " + serviceClass.getName() + " service does not exist");
        }
        synchronized (services) {
            services.get(serviceIndex.get()).deactivate();
        }
    }

    public static void removeService(Class<?> serviceClass) {
        Optional<Integer> serviceIndex = findService(serviceClass);
        if (serviceIndex.isEmpty()) {
            throw new IllegalArgumentException("The " + serviceClass.getName() + " service does not exist");
        }
        synchronized (services) {
            services.remove(serviceIndex.get().intValue());
        }
    }

    public static Class<? extends BriService> getServiceClass(int numService) {
        synchronized (services) {
            if (numService < 1 || numService > services.size()) {
                throw new IllegalArgumentException("The service number " + numService + " does not exist");
            }
            return services.get(numService - 1).getServiceClass();
        }
    }

    public static String toStringue() {
        synchronized (services) {
            StringBuilder result = new StringBuilder("Activités présentes :##");
            for (int i = 0; i < services.size(); i++) {
                if (services.get(i).getState() == ServiceState.ACTIVE) {
                    result.append((i + 1)).append(" - ").append(services.get(i).getServiceClass().getName()).append("##");
                }
            }
            return result.toString();
        }
    }


    public static Integer serviceNumber() {
        return services.size();
    }
}
