package com.vych.game.managers.resources.entities.core;

import com.badlogic.gdx.utils.Disposable;
import com.vych.game.managers.resources.exceptions.UnloadedResourceUse;
import com.vych.game.managers.resources.exceptions.WrongResourceType;

/**
 * Класс реализующий базовое поведение всех игровых ресурсов. Все поддерживаемые типы ресурсов должны
 * наследоваться от этого класса. По своей сути класс является обёрткой
 * для более удобной организации ресурсов в менеджере.
 */
public abstract class BasicResource implements GameResource {
    protected Disposable content;
    protected String name;
    protected String path;
    protected ResourceType type;
    protected boolean loaded = false;

    @Override
    public Disposable getContentRaw() {
        if (!loaded) {
            throw new UnloadedResourceUse("Попытка использовать не загруженный игровой ресурс.");
        }
        return this.content;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public GameResource setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public ResourceType getType() {
        return this.type;
    }

    @Override
    public GameResource setType(ResourceType type) {
        this.type = type;
        return this;
    }

    @Override
    public String getPath() {
        return this.path;
    }

    @Override
    public GameResource setPath(String path) {
        this.path = path;
        return this;
    }

    @Override
    public boolean isLoaded() {
        return this.loaded;
    }

    @Override
    public void checkResourceType(ResourceType type) {
        if (this.type != type) {
            throw new WrongResourceType("Указанный тип ресурса и использованный класс не сходятся");
        }
    }
}
