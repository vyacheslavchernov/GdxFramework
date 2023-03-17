package com.vych.game.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Оперативное хранилище для игровых данных.
 * Данные хранятся в стеше до окончания работы игры и нигде не сохраняются.
 */
public class Stash {
    private static Map<String, Object> stash = new HashMap<>();

    public static void add(String key, Object val) {
        stash.put(key, val);
    }

    public static Object get(String key) {
        return stash.get(key);
    }

    public static void remove(String key) {
        stash.remove(key);
    }
}
