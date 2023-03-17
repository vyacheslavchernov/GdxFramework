package com.vych.game.managers.gameObjects;

import com.badlogic.gdx.utils.TimeUtils;
import com.vych.game.managers.gameObjects.entities.GameObject;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Менеджер игровых объектов ({@link GameObject}). Нужен для удобной организации игровых сущностей,
 * для их создания, удаления, поиска.
 * Является синглтоном за счёт чего игровые объекты доступны для использования в любой момент.
 */
public class GameObjectsManager {
    private static final GameObjectsManager instance = new GameObjectsManager();
    private Map<Long, GameObject> gameObjects;

    public GameObjectsManager() {
        gameObjects = new HashMap<>();
    }

    public static GameObjectsManager getInstance() {
        return instance;
    }

    /**
     * Создание нового игрового объекта с занесением его в менеджер.
     *
     * @param objectClass Класс создаваемого объекта.
     * @return Созданный объект.
     */
    public <T extends GameObject> T instantiateGameObject(Class<T> objectClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Long id = TimeUtils.nanoTime();
        T obj = objectClass.getDeclaredConstructor(Long.class).newInstance(id);
        this.gameObjects.put(id, obj);
        return objectClass.cast(obj);
    }

    /**
     * Получение всех существующих объектов определённого класса.
     *
     * @param objectClass Искомый класс наследуемый от {@link GameObject}
     * @return Словарь с ссылками на {@link GameObject}. Ключами словаря являются id объектов.
     * При возвращение так же происходит каст в исходный класс. Если объектов не найдено, то возвращается пустой словарь.
     */
    public <T extends GameObject> Map<Long, T> getObjectsByClass(Class<T> objectClass) {
        Map<Long, T> ret = new HashMap<>();

        for (GameObject obj : gameObjects.values()) {
            if (obj.getClass() == objectClass) {
                ret.put(obj.getId(), objectClass.cast(obj));
            }
        }

        return ret;
    }

    /**
     * Получение всех существующих объектов.
     *
     * @return Словарь, который содержит все {@link GameObject}, которые есть в менеджере.
     */
    public Map<Long, GameObject> getObjectsAll() {
        return gameObjects;
    }

    /**
     * Удаление объекта по id.
     *
     * @param id Id удаляемого объекта.
     */
    public void destructById(Long id) {
        gameObjects.remove(id);
    }
}
