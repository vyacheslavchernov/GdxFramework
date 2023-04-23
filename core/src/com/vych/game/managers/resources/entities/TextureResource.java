package com.vych.game.managers.resources.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.vych.game.managers.resources.entities.core.BasicResource;
import com.vych.game.managers.resources.entities.core.GameResource;
import com.vych.game.managers.resources.entities.core.ResourceType;

public class TextureResource extends BasicResource {
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
