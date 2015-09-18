package de.hydro.gv.orgpm.util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

@Target( { ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER } )
@Retention( RetentionPolicy.RUNTIME )
@Documented
@Qualifier
public @interface LoggedInUser {
}