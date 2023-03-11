package us.crazycrew.crazycore.files.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Path {

    /**
     * @return the file option i.e settings.test-value: false
     */
    String value();

}