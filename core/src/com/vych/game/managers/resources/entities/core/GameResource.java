package com.vych.game.managers.resources.entities.core;

import com.badlogic.gdx.utils.Disposable;

/**
 * Интерфейс описывающий поведение игрового ресурса.
 */
public interface GameResource {
    public Disposable getContentRaw();

    public Disposable getContentCasted();

    public String getName();

    public GameResource setName(String name);

    public ResourceType getType();

    public GameResource setType(ResourceType type);

    public String getPath();

    public GameResource setPath(String path);

    public boolean isLoaded();

    public GameResource load();

    void checkResourceType(ResourceType type);
}
