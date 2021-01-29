package org.example.dogdog.core.ioc;

import org.example.dogdog.annotation.ioc.Component;

import org.example.dogdog.annotation.springmvc.RestController;
import org.example.dogdog.common.util.ReflectionUtil;
import org.example.dogdog.core.config.ConfigurationManager;
import org.example.dogdog.factory.ClassFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BeanFactory {

    // ioc bean 容器
    public static final Map<String, Object> BEANS = new ConcurrentHashMap<>(128);

    private static final Map<String, String[]> SINGLE_BEAN_NAMES_TYPE_MAP = new ConcurrentHashMap<>(128);

    public static void loadBeans() {
        ClassFactory.CLASSES.get(Component.class).forEach(clazz -> {
            String beanName = BeanHelper.getBeanName(clazz);
            Object obj = ReflectionUtil.newInstance(clazz);
            BEANS.put(beanName, obj);
        });
        ClassFactory.CLASSES.get(RestController.class).forEach(clazz -> {
            Object obj = ReflectionUtil.newInstance(clazz);
            BEANS.put(clazz.getName(), obj);
        });
        BEANS.put(ConfigurationManager.class.getName(), new ConfigurationManager(ConfigurationFactory))
    }
}
