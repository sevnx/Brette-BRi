package application.server.bri.validator;

import application.server.bri.BriService;

import java.lang.reflect.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServiceValidator {
    public static List<String> validate(Class<?> candidate, String username) {
        List<String> errors = new ArrayList<>();
        if (!isInUsernamePackage(candidate, username)) {
            errors.add("Class is not in the correct package (expected " + username + candidate.getName());
        }
        if (!isPublic(candidate)) {
            errors.add("Class is not public");
        }
        if (!isConcrete(candidate)) {
            errors.add("Class is abstract");
        }
        if (!doesImplementService(candidate)) {
            errors.add("Class does not implement Service");
        }
        if (!hasPublicSocketConstructor(candidate)) {
            errors.add("Class does not have a public constructor with a Socket parameter returning no exceptions");
        }
        if (!hasPrivateFinalSocketAttribute(candidate)) {
            errors.add("Class does not have a private final Socket attribute");
        }
        if (!hasToStringueMethod(candidate)) {
            errors.add("Class does not have a public static toStringue method returning a String, taking no parameters, and returning no exceptions");
        }
        return errors;
    }

    private static boolean isInUsernamePackage(Class<?> candidate, String username) {
        return candidate.getPackageName().startsWith(username);
    }

    private static boolean isPublic(Class<?> candidate) {
        return Modifier.isPublic(candidate.getModifiers());
    }

    private static boolean isConcrete(Class<?> candidate) {
        return !Modifier.isAbstract(candidate.getModifiers());
    }

    private static boolean doesImplementService(Class<?> candidate) {
        return BriService.class.isAssignableFrom(candidate);
    }

    private static boolean hasPublicSocketConstructor(Class<?> candidate) {
        try {
            Constructor<?> constructor = candidate.getConstructor(Socket.class);
            if (constructor.getExceptionTypes().length > 0) {
                return false;
            }
        } catch (NoSuchMethodException e) {
            return false;
        }
        return true;
    }

    private static boolean hasPrivateFinalSocketAttribute(Class<?> candidate) {
        Field[] fields = candidate.getDeclaredFields();
        for (Field field : fields) {
            if (field.getType().equals(Socket.class) &&
                    Modifier.isPrivate(field.getModifiers()) &&
                    Modifier.isFinal(field.getModifiers())) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasToStringueMethod(Class<?> candidate) {
        try {
            Method method = candidate.getDeclaredMethod("toStringue");
            if (!Modifier.isStatic(method.getModifiers()) ||
                    !method.getReturnType().equals(String.class) ||
                    method.getParameterCount() > 0 || method.getExceptionTypes().length > 0
            ) {
                return false;
            }
        } catch (NoSuchMethodException e) {
            return false;
        }
        return true;
    }
}
