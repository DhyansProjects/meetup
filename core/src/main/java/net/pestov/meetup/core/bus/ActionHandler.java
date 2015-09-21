package net.pestov.meetup.core.bus;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by eugene on 21/08/2015.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface ActionHandler {
    /**
     * The value may indicate a suggestion for a logical component name,
     * to be turned into a Spring bean in case of an autodetected component.
     * @return the suggested component name, if any
     */
    String value() default "";

    /**
     * The command class to be handled by the handler.
     * @return the command class
     */
    Class<? extends Action> command();

    /**
     * The name of the method which will be called with a sinfle command parameter.
     * @return the method name
     */
    String method() default "handle";
}
