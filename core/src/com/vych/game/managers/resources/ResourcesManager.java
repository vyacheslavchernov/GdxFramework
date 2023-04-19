package com.vych.game.managers.resources;

import com.vych.game.managers.resources.entities.*;
import com.vych.game.managers.resources.entities.core.GameResource;
import com.vych.game.managers.resources.entities.core.ResourceType;
import com.vych.game.managers.resources.exceptions.CannotLoadResource;
import com.vych.game.managers.resources.exceptions.CannotUnloadResource;
import com.vych.game.managers.resources.exceptions.UnexpectedResourceType;

import java.util.HashMap;
import java.util.Map;

/**
 * Менеджер игровых ресурсов. Предоставляет удобный интерфейс для загрузки ресурсов,
 * для управления ими, для их использования.
 * Является синглтоном за счёт чего игровые ресурсы доступны для использования в любой момент.
 */
public class ResourcesManager {
    private static final ResourcesManager instance = new ResourcesManager();
    private Map<String, GameResource> resources;

    public ResourcesManager() {
        resources = new HashMap<>();
    }

    public static ResourcesManager getInstance() {
        return instance;
    }

    /**
     * Загрузка ресурса из internal хранилища LibGDX
     *
     * @param resourceName Название ресурса по которому будет осуществляться дальнейший доступ к ресурсу через менеджер.
     * @param resourceInternalPath Internal путь до ресурса (относительно assets/ )
     * @param resourceType тип загружаемого ресураса (отсюда {@link ResourceType})
     */
    public void loadResource(String resourceName, String resourceInternalPath, ResourceType resourceType) throws CannotLoadResource {
        if (resources.containsKey(resourceName)) {
            throw new CannotLoadResource("Имя ресурса уже используется");
        }

        GameResource res;
        switch (resourceType) {
            case MUSIC:
                res = new MusicResource();
                break;

            case SOUND:
                res = new SoundResource();
                break;

            case TEXTURE:
                res = new TextureResource();
                break;

            default:
                throw new UnexpectedResourceType("Неизвестный тип ресурса для загрузки");
        }

        res.setName(resourceName).setPath(resourceInternalPath).setType(resourceType).load();
        resources.put(resourceName, res);
    }

    /**
     * Выгрузка ресурса из памяти.
     *
     * @param resourceName имя выгружаемого ресурса.
     */
    public void unloadResource(String resourceName) throws CannotUnloadResource {
        GameResource resource = resources.get(resourceName);
        if (resource == null) {
            throw new CannotUnloadResource("Не удалось выгрузить ресурс из памяти. Ресурс не существует");
        }
        resource.getContentRaw().dispose();
        resources.remove(resourceName);
    }

    /**
     * Получение ресурса по имени.
     *
     * @param resourceName Название искомого ресурса.
     * @param castTo Класс в который будет скастован найденный ресурс. Должен быть наследником {@link GameResource}.
     * @return Игровой ресурс в обертке из {@link GameResource} или null.
     */
    public <T extends GameResource> T getByName(String resourceName, Class<T> castTo) {
        return castTo.cast(resources.get(resourceName));
    }

    /**
     * Получение всех игровых ресурсов определённого типа.
     *
     * @param resourceType искомый тип ресурса.
     * @param castTo Класс в который будет скастован найденный ресурс. Должен быть наследником {@link GameResource}.
     * @return Словарь с игровыми ресурсами в обертке из {@link GameResource}. Если ресурсы не были найдены,
     * то будет возвращён пустой словарь.
     */
    public <T extends GameResource> Map<String, T> getByType(ResourceType resourceType, Class<T> castTo) {
        Map<String, T> ret = new HashMap<>();

        for (GameResource resource : resources.values()) {
            if (resource.getType() == resourceType) {
                ret.put(resource.getName(), castTo.cast(resource));
            }
        }

        return ret;
    }
}
