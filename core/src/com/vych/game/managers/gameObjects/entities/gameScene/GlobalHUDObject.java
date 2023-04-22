package com.vych.game.managers.gameObjects.entities.gameScene;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Align;
import com.vych.game.managers.gameObjects.entities.core.BasicGameObject;
import com.vych.game.renderer.core.gui.GUIHandler;
import com.vych.game.renderer.core.gui.components.GUITextComponent;
import com.vych.game.scenes.core.BasicScene;

public class GlobalHUDObject extends BasicGameObject {
    private GUIHandler guiHandler;

    private int score = 0;

    public GlobalHUDObject(Long id, BasicScene scene) {
        super(id, scene);
    }

    @Override
    public void instanceCreate() {
        super.instanceCreate();
        this.guiHandler = new GUIHandler();
        this.guiHandler.addComponent(
                new GUITextComponent("scoreText", this.scene)
                        .setHalign(Align.center)
                        .setFontName("defaultFont")
                        .setText("Score")
                        .setX(400)
                        .setY(460)
        );
    }

    @Override
    public void instanceStep() {
        this.guiHandler.getComponentCasted("scoreText", GUITextComponent.class)
                .setText(String.format("Score: %d", this.score));
    }

    @Override
    public void instanceRenderGUI(Batch batch) {
        this.guiHandler.renderComponents(batch);
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void incrementScore() {
        this.score++;
    }
}
