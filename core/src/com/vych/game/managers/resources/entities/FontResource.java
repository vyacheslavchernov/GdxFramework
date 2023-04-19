package com.vych.game.managers.resources.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.vych.game.managers.resources.entities.core.BasicResource;
import com.vych.game.managers.resources.entities.core.GameResource;
import com.vych.game.managers.resources.entities.core.ResourceType;

public class FontResource extends BasicResource {
    @Override
    public BitmapFont getContentCasted() {
        return (BitmapFont) this.content;
    }

    @Override
    public GameResource load() {
        checkResourceType(ResourceType.FONT);

        if (this.path == null) {
            this.content = new BitmapFont();
        } else {
            FreeTypeFontGenerator fftGen = new FreeTypeFontGenerator(Gdx.files.internal(this.path));
            FreeTypeFontParameter fftParam = new FreeTypeFontParameter();
            fftParam.size = 32;
            this.content = fftGen.generateFont(fftParam);
            fftGen.dispose();
        }

        this.loaded = true;
        return this;
    }
}
