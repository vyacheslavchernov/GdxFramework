package com.vych.game.managers.gameObjects.entities.core;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.vych.game.context.ComponentsContext;
import com.vych.game.managers.gameObjects.GameObjectsManager;
import com.vych.game.managers.resources.entities.TextureResource;
import com.vych.game.scenes.core.BasicScene;

/**
 * Базовая имплементация интерфейса игрового объекта.
 * Содержит единый для всех объектов функционал.
 * Все дополнительные классы игровых объектов
 * следует наследовать от этого класса.
 */
public abstract class BasicGameObject implements GameObject {
    protected Long id;
    protected Rectangle bounds;
    protected TextureResource textureResource = null;
    protected BasicScene scene;

    public BasicGameObject(Long id, BasicScene scene) {
        this.id = id;
        this.scene = scene;
        this.textureResource = null;
        this.bounds = new Rectangle(0, 0, 64, 64);
        instanceCreate();
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public BasicGameObject setId(Long id) {
        this.id = id;
        return this;
    }

    @Override
    public Rectangle getBounds() {
        return this.bounds;
    }

    @Override
    public BasicGameObject setBounds(Rectangle bounds) {
        this.bounds = bounds;
        return this;
    }

    @Override
    public BasicGameObject setBounds(int x, int y, int width, int height) {
        this.bounds = new Rectangle(x, y, width, height);
        return this;
    }

    @Override
    public TextureResource getTextureResource() {
        return this.textureResource;
    }

    @Override
    public BasicGameObject setTextureResource(TextureResource textureResource) {
        this.textureResource = textureResource;
        return this;
    }

    @Override
    public BasicScene getLinkedScene() {
        return this.scene;
    }

    @Override
    public void selfDestruct() {
        ComponentsContext.getComponent(GameObjectsManager.class).destructById(this.id);
    }

    /**
     * При необходимости более сложного рендеринга,
     * следует переопределять данный метод.
     */
    @Override
    public void instanceRender(Batch batch) {
        if (this.textureResource != null) {
            batch.draw(
                    this.textureResource.getContentCasted(),
                    this.bounds.x,
                    this.bounds.y
            );
        }
    }

    /**
     * При необходимости отрисовки поверх всего остального,
     * следует переопределять данный метод.
     */
    @Override
    public void instanceRenderGUI(Batch batch) {

    }

    /**
     * При необходимости наличия в объекте логики,
     * следует переопределять данный метод.
     */
    @Override
    public void instanceStep() {

    }

    /**
     * При необходимости выполнения каких-либо
     * операций в ходе создания объекта, следует переопределять данный метод.
     */
    @Override
    public void instanceCreate() {

    }
}
