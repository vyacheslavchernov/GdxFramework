package com.vych.game.managers.gameObjects.entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.vych.game.managers.resources.entities.TextureResource;

public interface GameObject {
    public Long getId();

    public GameObject setId(Long id);

    public Rectangle getBounds();

    public GameObject setBounds(Rectangle bounds);

    public GameObject setBounds(int x, int y, int width, int height);

    public TextureResource getTextureResource();

    public GameObject setTextureResource(TextureResource textureResource);

    public void updateInstance();

    public void instanceRender(Batch batch);

    public void instanceStep();

    public void instanceCreate();

    public void selfDestruct();
}
