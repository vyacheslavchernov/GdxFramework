package com.vych.game.managers.resources.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class TextureResource extends BasicResource{
    @Override
    public Texture getContentCasted() {
        return (Texture) this.content;
    }

    @Override
    public GameResource load() {
        checkResourceType(ResourceType.TEXTURE);
        this.content = new Texture(Gdx.files.internal(this.path));
        loaded = true;
        return this;
    }
}
