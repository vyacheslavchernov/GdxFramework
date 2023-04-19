package com.vych.game.managers.gameObjects.entities.core;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.vych.game.managers.gameObjects.GameObjectsManager;
import com.vych.game.managers.resources.entities.TextureResource;
import com.vych.game.renderer.SceneRenderer;

/**
 * Базовая имплементация интерфейса игрового объекта. Содержит единый для всех объектов функционал.
 * Все дополнительные классы игровых объектов следует наследовать от этого класса.
 */
public abstract class BasicGameObject implements GameObject {
    protected Long id;
    protected Rectangle bounds;
    protected TextureResource textureResource;
    protected SceneRenderer renderer;
    protected Screen screen;

    public BasicGameObject(Long id) {
        this.id = id;
        this.bounds = new com.badlogic.gdx.math.Rectangle(0, 0, 64, 64);
        instanceCreate();
    }

    public Long getId() {
        return id;
    }

    public BasicGameObject setId(Long id) {
        this.id = id;
        return this;
    }

    @Override
    public Rectangle getBounds() {
        return bounds;
    }

    public BasicGameObject setBounds(Rectangle bounds) {
        this.bounds = bounds;
        return this;
    }

    public BasicGameObject setBounds(int x, int y, int width, int height) {
        this.bounds = new Rectangle(x, y, width, height);
        return this;
    }

    @Override
    public TextureResource getTextureResource() {
        return textureResource;
    }

    @Override
    public BasicGameObject setTextureResource(TextureResource textureResource) {
        this.textureResource = textureResource;
        return this;
    }

    @Override
    public void selfDestruct() {
        GameObjectsManager.getInstance().destructById(this.id);
    }

    /**
     * При необходимости более сложного рендеринга, следует переопределять данный метод.
     */
    @Override
    public void instanceRender(Batch batch) {
        batch.draw(textureResource.getContentCasted(), bounds.x, bounds.y);
    }

    @Override
    public void linkToRenderer(SceneRenderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public SceneRenderer getLinkedRenderer() {
        return this.renderer;
    }

    @Override
    public void linkToScene(Screen screen) {
        this.screen = screen;
    }

    @Override
    public Screen getLinkedScene() {
        return this.screen;
    }
}
