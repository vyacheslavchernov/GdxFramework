package com.vych.game.context;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Класс помеченный данной аннотацией будет автоматически загружен в {@link ComponentsContext}.
 * После этого к нему можно будет получить доступ из любого места
 * с помощью метода {@link ComponentsContext#getComponent(Class)}
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Component {
}
