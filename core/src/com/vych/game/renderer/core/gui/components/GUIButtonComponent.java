package com.vych.game.renderer.core.gui.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Align;
import com.vych.game.context.ComponentsContext;
import com.vych.game.managers.resources.ResourcesManager;
import com.vych.game.managers.resources.entities.FontResource;
import com.vych.game.managers.resources.entities.TextureResource;
import com.vych.game.renderer.core.gui.BasicGUIComponent;
import com.vych.game.renderer.core.gui.ComponentState;
import com.vych.game.renderer.core.gui.GUIListener;
import com.vych.game.scenes.core.BasicScene;
import com.vych.game.utils.CameraUtils;

public class GUIButtonComponent extends BasicGUIComponent {
    private ComponentState state;
    private TextureResource idleTexture;
    private TextureResource hoverTexture;
    private TextureResource clickTexture;
    private Rectangle bounds;
    private float width;
    private float height;
    private GUIListener onClick;

    public GUIButtonComponent(String name, BasicScene scene) {
        super(name, scene);
        this.state = ComponentState.IDLE;
        this.idleTexture = null;
        this.hoverTexture = null;
        this.clickTexture = null;
        this.bounds = new Rectangle();
        this.width = 4;
        this.height = 4;
    }

    public ComponentState getState() {
        return state;
    }

    public GUIButtonComponent setState(ComponentState state) {
        this.state = state;
        return this;
    }

    public TextureResource getIdleTexture() {
        return idleTexture;
    }

    public GUIButtonComponent setIdleTexture(TextureResource idleTexture) {
        this.idleTexture = idleTexture;
        return this;
    }

    public TextureResource getHoverTexture() {
        return hoverTexture;
    }

    public GUIButtonComponent setHoverTexture(TextureResource hoverTexture) {
        this.hoverTexture = hoverTexture;
        return this;
    }

    public TextureResource getClickTexture() {
        return clickTexture;
    }

    public GUIButtonComponent setClickTexture(TextureResource clickTexture) {
        this.clickTexture = clickTexture;
        return this;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public GUIButtonComponent setBounds(Rectangle bounds) {
        this.bounds = bounds;
        return this;
    }

    public float getWidth() {
        return width;
    }

    public GUIButtonComponent setWidth(float width) {
        this.width = width;
        this.bounds.setWidth(width);
        return this;
    }

    public float getHeight() {
        return height;
    }

    public GUIButtonComponent setHeight(float height) {
        this.height = height;
        this.bounds.setHeight(height);
        return this;
    }

    public GUIButtonComponent setOnClick(GUIListener onClick) {
        this.onClick = onClick;
        return this;
    }

    @Override
    public GUIButtonComponent setX(float x) {
        super.setX(x);
        this.bounds.setX(x);
        return this;
    }

    @Override
    public GUIButtonComponent setY(float y) {
        super.setY(y);
        this.bounds.setY(y);
        return this;
    }

    @Override
    public void update() {
        Vector3 mousePos = CameraUtils.unprojectToCamera(this.scene.getCamera(), Gdx.input.getX(), Gdx.input.getY());
        if (this.bounds.contains(mousePos.x, mousePos.y) || this.state == ComponentState.CLICK) {
            if (Gdx.input.isTouched()) {
                this.state = ComponentState.CLICK;
            } else {
                if (this.state == ComponentState.CLICK) {
                    if (this.bounds.contains(mousePos.x, mousePos.y)) {
                        this.state = ComponentState.RELEASE;
                    } else {
                        this.state = ComponentState.IDLE;
                    }
                } else {
                    this.state = ComponentState.HOVER;
                }
            }
        } else {
            this.state = ComponentState.IDLE;
        }

        if (this.state == ComponentState.RELEASE) {
            this.onClick.onEvent(this.scene);
        }
    }

    @Override
    public void render(Batch batch) {
        batch.draw(getTextureByState().getContentCasted(), this.x, this.y, this.width, this.height);
        BitmapFont font = ComponentsContext
                .getComponent(ResourcesManager.class)
                .getByName(fontName, FontResource.class)
                .getContentCasted();
        font.draw(batch, this.text, this.x + width / 2, this.y + height / 2 + font.getXHeight() / 2, 0, Align.center, false);
    }

    private TextureResource getTextureByState() {
        switch (this.state) {
            case HOVER:
                return this.hoverTexture;

            case CLICK:
                return this.clickTexture;

            default:
                return this.idleTexture;
        }
    }
}
