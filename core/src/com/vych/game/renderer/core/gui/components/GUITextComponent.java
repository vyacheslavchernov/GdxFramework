package com.vych.game.renderer.core.gui.components;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Align;
import com.vych.game.managers.resources.ResourcesManager;
import com.vych.game.managers.resources.entities.FontResource;
import com.vych.game.renderer.core.gui.BasicGUIComponent;
import com.vych.game.scenes.core.BasicScene;

public class GUITextComponent extends BasicGUIComponent {
    private int halign;
    private boolean textWrap;

    public GUITextComponent(String name, BasicScene scene) {
        super(name, scene);

        this.halign = Align.left;
        this.textWrap = false;
    }

    public int getHalign() {
        return this.halign;
    }

    public GUITextComponent setHalign(int halign) {
        this.halign = halign;
        return this;
    }

    public boolean isTextWrap() {
        return this.textWrap;
    }

    public GUITextComponent setTextWrap(boolean textWrap) {
        this.textWrap = textWrap;
        return this;
    }

    @Override
    public void render(Batch batch) {
        BitmapFont font = ResourcesManager.getInstance().getByName(fontName, FontResource.class).getContentCasted();
        font.draw(batch, this.text, this.x, this.y, 0, this.halign, this.textWrap);
    }
}
