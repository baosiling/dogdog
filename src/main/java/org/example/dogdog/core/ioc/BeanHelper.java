package org.example.dogdog.core.ioc;

import org.example.dogdog.annotation.ioc.Component;

public class BeanHelper {

    /**
     * get the bean name
     * @param clazz
     * @return
     */
    public static String getBeanName(Class<?> clazz) {
        String beanName = clazz.getName();
        if(clazz.isAnnotationPresent(Component.class)) {
            Component component = clazz.getAnnotation(Component.class);
            beanName = "".equals(component.name()) ? clazz.getName() : component.name();
        }
        return beanName;
    }
}
