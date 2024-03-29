package com.pandapulsestudios.pulsecore.Events;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CustomCoreEvents {
    boolean op();
    String[] perms();
    String[] worlds();
    String[] regions();
}
