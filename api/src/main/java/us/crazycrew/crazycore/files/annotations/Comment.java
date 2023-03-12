package us.crazycrew.crazycore.files.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Comment {

    /**
     * @return the comment value
     */
    String value();

    /**
     * If it should be Side Type or Block Comment.
     *
     * @return true or false
     */
    boolean blockType();

}