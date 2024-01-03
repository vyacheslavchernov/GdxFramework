package com.vych.game.context;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Контекст компонентов к которым требуется быстрый доступ из любого места
 */
public class ComponentsContext {
    private static Map<String, Object> loadedComponents = new HashMap<>();

    /**
     * Загрузка в контекст всех объектов, которые помечены анотацией {@link Component}
     */
    public static void loadContext() {
        final ClassLoader loader = Thread.currentThread().getContextClassLoader();
        final ImmutableSet<ClassPath.ClassInfo> classInfos;

        try {
            classInfos = ClassPath.from(loader).getTopLevelClasses();
            for (final ClassPath.ClassInfo info : classInfos) {
                if (info.getName().startsWith("com.vych.game.")) {
                    final Class<?> clazz = info.load();
                    if (clazz.getAnnotation(Component.class) != null) {
                        loadedComponents.put(
                                clazz.getName(),
                                clazz.getDeclaredConstructor().newInstance()
                        );
                    }
                }
            }
        } catch (IOException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new RuntimeException("Failed to load managers context", e);
        }
    }

    /**
     * Получение экземпляра компонента из контекста по классу
     * @param componentClass Класс необходимого компонента
     * @return экземпляр искомого компонента
     */
    public static <T extends Object> T getComponent(Class<T> componentClass) {
        Object component = loadedComponents.getOrDefault(componentClass.getName(), null);
        if (component != null) {
            return componentClass.cast(component);
        }
        throw new RuntimeException(componentClass + " not existed in context");
    }
}
