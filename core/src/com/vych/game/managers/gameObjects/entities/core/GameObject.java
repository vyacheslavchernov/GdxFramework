package com.vych.game.managers.gameObjects.entities.core;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.vych.game.managers.resources.entities.TextureResource;
import com.vych.game.renderer.core.SceneRenderer;
import com.vych.game.screens.core.BasicScene;

/**
 * Интерфейс описывающий поведение игровых объектов.
 */
public interface GameObject {
    Long getId();

    GameObject setId(Long id);

    Rectangle getBounds();

    GameObject setBounds(Rectangle bounds);

    GameObject setBounds(int x, int y, int width, int height);

    TextureResource getTextureResource();

    GameObject setTextureResource(TextureResource textureResource);

    void instanceRender(Batch batch);

    void instanceRenderGUI(Batch batch);

    void instanceStep();

    void instanceCreate();

    void selfDestruct();

    void linkToRenderer(SceneRenderer renderer);

    SceneRenderer getLinkedRenderer();

    void linkToScene(BasicScene scene);

    BasicScene getLinkedScene();
}
