package com.vych.game.managers.resources;

import com.vych.game.managers.resources.entities.*;
import com.vych.game.managers.resources.exceptions.CannotLoadResource;
import com.vych.game.managers.resources.exceptions.CannotUnloadResource;
import com.vych.game.managers.resources.exceptions.UnexpectedResourceType;

import java.util.HashMap;
import java.util.Map;

public class ResourcesManager {
    private static final ResourcesManager instance = new ResourcesManager();
    private Map<String, GameResource> resources = new HashMap<>();

    public ResourcesManager() {
        resources = new HashMap<>();
    }

    public static ResourcesManager getInstance() {
        return instance;
    }

    public void loadResource(String resourceName, String resourceInternalPath, ResourceType resourceType) throws CannotLoadResource {
        if (resources.containsKey(resourceName)) {
            throw new CannotLoadResource("Имя ресурса уже используется");
        }

        GameResource res = null;
        switch (resourceType) {
            case MUSIC:
                res = new MusicResource();
                break;

            case SOUND:
                res = new SoundResource();
                break;

            case SPRITE:
                break;

            case TEXTURE:
                res = new TextureResource();
                break;

            default:
                throw new UnexpectedResourceType("Неизвестный тип ресурса для загрузки");
        }

        if (res == null) {
            throw new CannotLoadResource("Не удалось загрузить ресурс");
        }

        res.setName(resourceName).setPath(resourceInternalPath).setType(resourceType).load();
        resources.put(resourceName, res);
    }

    public void unloadResource(String resourceName) throws CannotUnloadResource {
        GameResource resource = resources.get(resourceName);
        if (resource == null) {
            throw new CannotUnloadResource("Не удалось выгрузить ресурс из памяти. Ресурс не существует");
        }
        resource.getContentRaw().dispose();
        resources.remove(resourceName);
    }

    public <T extends GameResource> T getByName(String resourceName, Class<T> castTo) {
        return castTo.cast(resources.get(resourceName));
    }

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
