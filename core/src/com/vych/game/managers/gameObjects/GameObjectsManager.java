package com.vych.game.managers.gameObjects;

import com.badlogic.gdx.utils.TimeUtils;
import com.vych.game.managers.gameObjects.entities.BasicGameObject;
import com.vych.game.managers.gameObjects.entities.GameObject;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class GameObjectsManager {
    private static final GameObjectsManager instance = new GameObjectsManager();
    private Map<Long, GameObject> gameObjects;

    public GameObjectsManager() {
        gameObjects = new HashMap<>();
    }

    public static GameObjectsManager getInstance() {
        return instance;
    }

    public <T extends GameObject> T instantiateGameObject(Class<T> objectClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Long id = TimeUtils.nanoTime();
        T obj = objectClass.getDeclaredConstructor(Long.class).newInstance(id);
        this.gameObjects.put(id, obj);
        return objectClass.cast(obj);
    }

    public void updateInstance(GameObject objectInstance) {
        gameObjects.put(objectInstance.getId(), objectInstance);
    }

    public <T extends GameObject> Map<Long, T> getObjectsByClass(Class<T> objectClass) {
        Map<Long, T> ret = new HashMap<>();

        for (GameObject obj : gameObjects.values()) {
            if (obj.getClass() == objectClass) {
                ret.put(obj.getId(), objectClass.cast(obj));
            }
        }

        return ret;
    }

    public Map<Long, GameObject> getObjectsAll() {
        return gameObjects;
    }

    public void destructById(Long id) {
        gameObjects.remove(id);
    }
}
