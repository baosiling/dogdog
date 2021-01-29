package org.example.dogdog.common.util;

import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.reflections.scanners.TypeAnnotationsScanner;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

@Slf4j
public class ReflectionUtil {

    /**
     * scan the classes marked by the specified annotation in the specified package
     *
     * @param packageName
     * @param annotation
     * @return
     */
    public static Set<Class<?>> scanAnnotatedClass(String packageName, Class<? extends Annotation> annotation) {
        Reflections reflections = new Reflections(packageName, new TypeAnnotationsScanner());
        Set<Class<?>> annotatedClass = reflections.getTypesAnnotatedWith(annotation, true);
        log.info("The number of class Annotated with @" + annotation.getSimpleName() + ":[{}]", annotatedClass.size());
        return annotatedClass;
    }

    /**
     * scan the classes marked by the specified annotation in the specified package
     * @param packageNames
     * @param annotation
     * @return
     */
    public static Set<Class<?>> scanAnnotatedClass(String[] packageNames, Class<? extends Annotation> annotation) {
        Reflections reflections = new Reflections(packageNames, new TypeAnnotationsScanner());
        Set<Class<?>> annotatedClass = reflections.getTypesAnnotatedWith(annotation, true);
        log.info("The number of class Annotated with @" + annotation.getSimpleName() + ":[{}]", annotatedClass.size());
        return annotatedClass;
    }

    /**
     * Get the implementation class of the interface
     * @param packageNames
     * @param interfaceClass
     * @param <T>
     * @return
     */
    public static <T> Set<Class<? extends T>> getSubClass(Object[] packageNames, Class<T> interfaceClass) {
        Reflections reflections = new Reflections(packageNames);
        return reflections.getSubTypesOf(interfaceClass);
    }

    /**
     * create object instance through class
     * @param cls
     * @return
     */
    public static Object newInstance(Class<?> cls) {
        try {
            return cls.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    /**
     * set the value of a field in the object
     * @param obj
     * @param field
     * @param value
     */
    public static void setField(Object obj, Field field, Object value) {
        field.setAccessible(true);
        try {
            field.set(obj, value);
        } catch (IllegalAccessException impossible) {
            throw new AssertionError(impossible);
        }
    }

    /**
     * execute the target method
     * @param targetObject
     * @param method
     * @param args
     * @return
     */
    public static Object executeTargetMethod(Object targetObject, Method method, Object... args) {
        try {
            return method.invoke(targetObject, args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * execute the void method
     * @param targetObject
     * @param method
     * @param args
     */
    public static void executeTargetMethodNoResult(Object targetObject, Method method, Object... args) {
        try {
            method.invoke(targetObject, args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
