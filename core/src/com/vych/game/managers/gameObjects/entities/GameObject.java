package com.vych.game.managers.gameObjects.entities;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.vych.game.managers.resources.entities.TextureResource;
import com.vych.game.renderer.SceneRenderer;

/**
 * Интерфейс описывающий поведение игровых объектов.
 */
public interface GameObject {
    public Long getId();

    public GameObject setId(Long id);

    public Rectangle getBounds();

    public GameObject setBounds(Rectangle bounds);

    public GameObject setBounds(int x, int y, int width, int height);

    public TextureResource getTextureResource();

    public GameObject setTextureResource(TextureResource textureResource);

    public void instanceRender(Batch batch);

    public void instanceStep();

    public void instanceCreate();

    public void selfDestruct();

    public void linkToRenderer(SceneRenderer renderer);

    public SceneRenderer getLinkedRenderer();

    public void linkToScene(Screen screen);

    public Screen getLinkedScene();
}
