package com.vych.game.managers.gameObjects.entities.menuScene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Align;
import com.vych.game.managers.gameObjects.entities.core.BasicGameObject;
import com.vych.game.renderer.core.gui.GUIHandler;
import com.vych.game.renderer.core.gui.components.GUITextComponent;
import com.vych.game.screens.GameScene;

public class MenuGUIObject extends BasicGameObject {

    private GUIHandler guiHandler;

    public MenuGUIObject(Long id) {
        super(id);
    }

    @Override
    public void instanceCreate() {
        super.instanceCreate();
        this.guiHandler = new GUIHandler();

        this.guiHandler.addComponent(
                new GUITextComponent("welcomeText", this.scene)
                        .setHalign(Align.center)
                        .setFontName("defaultFont")
                        .setText("PRESS ANY KEY TO PLAY")
                        .setX(800f / 2)
                        .setY(480f / 2)
        );
    }

    @Override
    public void instanceStep() {
        this.guiHandler.updateComponents();

        if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
            this.scene.getGame().setScreen(new GameScene(this.scene.getGame()));
        }
    }

    @Override
    public void instanceRenderGUI(Batch batch) {
        this.guiHandler.renderComponents(batch);
    }
}
