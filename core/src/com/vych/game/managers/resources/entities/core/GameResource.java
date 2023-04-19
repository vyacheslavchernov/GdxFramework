package com.vych.game.managers.resources.entities.core;

import com.badlogic.gdx.utils.Disposable;

/**
 * Интерфейс описывающий поведение игрового ресурса.
 */
public interface GameResource {
    Disposable getContentRaw();

    Disposable getContentCasted();

    String getName();

    GameResource setName(String name);

    ResourceType getType();

    GameResource setType(ResourceType type);

    String getPath();

    GameResource setPath(String path);

    boolean isLoaded();

    GameResource load();

    void checkResourceType(ResourceType type);
}
