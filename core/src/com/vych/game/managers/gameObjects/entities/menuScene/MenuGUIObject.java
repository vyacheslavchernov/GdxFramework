package com.vych.game.managers.gameObjects.entities.menuScene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Align;
import com.vych.game.managers.gameObjects.entities.core.BasicGameObject;
import com.vych.game.managers.resources.ResourcesManager;
import com.vych.game.managers.resources.entities.TextureResource;
import com.vych.game.managers.scenes.SceneManager;
import com.vych.game.renderer.core.gui.GUIHandler;
import com.vych.game.renderer.core.gui.GUIListener;
import com.vych.game.renderer.core.gui.components.GUIButtonComponent;
import com.vych.game.renderer.core.gui.components.GUITextComponent;
import com.vych.game.scenes.GameScene;
import com.vych.game.scenes.core.BasicScene;

public class MenuGUIObject extends BasicGameObject {

    private GUIHandler guiHandler;

    public MenuGUIObject(Long id, BasicScene scene) {
        super(id, scene);
    }

    @Override
    public void instanceCreate() {
        super.instanceCreate();
        this.guiHandler = new GUIHandler();
        ResourcesManager rm = ResourcesManager.getInstance();

        this.guiHandler.addComponent(
                new GUITextComponent("welcomeText", this.scene)
                        .setHalign(Align.center)
                        .setFontName("defaultFont")
                        .setText("PRESS ANY KEY TO PLAY\nOR")
                        .setX(800f / 2)
                        .setY(480f / 2)
        );

        this.guiHandler.addComponent(
                new GUIButtonComponent("startButton", this.scene)
                        .setIdleTexture(rm.getByName("buttonIdleImage", TextureResource.class))
                        .setHoverTexture(rm.getByName("buttonHoverImage", TextureResource.class))
                        .setClickTexture(rm.getByName("buttonClickImage", TextureResource.class))
                        .setX(300)
                        .setY(100)
                        .setWidth(200)
                        .setHeight(32)
                        .setOnClick(new GUIListener() {
                            @Override
                            public void onEvent(BasicScene scene) {
                                SceneManager sm = SceneManager.getInstance();
                                sm.switchScene(sm.loadScene("gameScene", GameScene.class), false);
                            }
                        })
                        .setText("PLAY")
                        .setFontName("defaultFont")
        );

        this.guiHandler.addComponent(
                new GUIButtonComponent("exitButton", this.scene)
                        .setIdleTexture(rm.getByName("buttonIdleImage", TextureResource.class))
                        .setHoverTexture(rm.getByName("buttonHoverImage", TextureResource.class))
                        .setClickTexture(rm.getByName("buttonClickImage", TextureResource.class))
                        .setX(300)
                        .setY(55)
                        .setWidth(200)
                        .setHeight(32)
                        .setOnClick(new GUIListener() {
                            @Override
                            public void onEvent(BasicScene scene) {
                                Gdx.app.exit();
                            }
                        })
                        .setText("EXIT")
                        .setFontName("defaultFont")
        );
    }

    @Override
    public void instanceStep() {
        if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
            SceneManager sm = SceneManager.getInstance();
            sm.switchScene(sm.loadScene("gameScene", GameScene.class), false);
        }
    }

    @Override
    public void instanceRenderGUI(Batch batch) {
        this.guiHandler.renderComponents(batch);
    }
}
