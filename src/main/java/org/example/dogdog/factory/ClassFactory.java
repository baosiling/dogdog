package org.example.dogdog.factory;

import org.example.dogdog.annotation.springmvc.RestController;
import org.example.dogdog.annotation.ioc.Component;
import org.example.dogdog.annotation.aop.Aspect;
import org.example.dogdog.common.util.ReflectionUtil;

import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ClassFactory {
    public static final Map<Class<? extends Annotation>, Set<Class<?>>> CLASSES =
            new ConcurrentHashMap<>();

    public static void loadClass(String[] packageName) {
        Set<Class<?>> restControllers = ReflectionUtil.scanAnnotatedClass(packageName, RestController.class);
        Set<Class<?>> components = ReflectionUtil.scanAnnotatedClass(packageName, Component.class);
        Set<Class<?>> aspects = ReflectionUtil.scanAnnotatedClass(packageName, Aspect.class);

        CLASSES.put(RestController.class, restControllers);
        CLASSES.put(Component.class, components);
        CLASSES.put(Aspect.class, aspects);
    }

}
