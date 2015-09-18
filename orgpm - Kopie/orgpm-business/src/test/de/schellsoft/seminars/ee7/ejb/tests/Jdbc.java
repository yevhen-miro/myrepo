package de.schellsoft.seminars.ee7.ejb.tests;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

@Qualifier // Qualifier Annotation is one possibility, another possibility is using @Named Annotation
@Retention(RetentionPolicy.RUNTIME)//RUNTIME Annotation
public @interface Jdbc {

}
