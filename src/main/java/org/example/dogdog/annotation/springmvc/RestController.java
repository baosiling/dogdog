package org.example.dogdog.annotation.springmvc;

import org.example.dogdog.annotation.ioc.Component;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface RestController {
    String value() default "";
}
