package fr.nkri.wizapi.cmds.interfaces;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Completer {
    String[] name() default {};
}
