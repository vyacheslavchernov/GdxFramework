package com.vych.game.managers.resources.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.vych.game.managers.resources.entities.core.BasicResource;
import com.vych.game.managers.resources.entities.core.GameResource;
import com.vych.game.managers.resources.entities.core.ResourceType;

public class SoundResource extends BasicResource {
    @Override
    public Sound getContentCasted() {
        return (Sound) this.content;
    }

    @Override
    public GameResource load() {
        checkResourceType(ResourceType.SOUND);
        this.content = Gdx.audio.newSound(Gdx.files.internal(this.path));
        loaded = true;
        return this;
    }
}
