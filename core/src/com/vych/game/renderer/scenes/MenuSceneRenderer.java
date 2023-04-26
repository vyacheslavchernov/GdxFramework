package com.vych.game.renderer.scenes;

import com.vych.game.managers.resources.entities.core.ResourceType;
import com.vych.game.renderer.core.BasicSceneRenderer;
import com.vych.game.renderer.core.SceneAsset;

public class MenuSceneRenderer extends BasicSceneRenderer {
    private final SceneAsset[] assets = {
            new SceneAsset()
                    .setResourceName("defaultFont")
                    .setResourceInternalPath("fonts/font.otf")
                    .setResourceType(ResourceType.FONT),
            new SceneAsset()
                    .setResourceName("buttonIdleImage")
                    .setResourceInternalPath("img/button.bmp")
                    .setResourceType(ResourceType.TEXTURE),
            new SceneAsset()
                    .setResourceName("buttonHoverImage")
                    .setResourceInternalPath("img/button_hover.bmp")
                    .setResourceType(ResourceType.TEXTURE),
            new SceneAsset()
                    .setResourceName("buttonClickImage")
                    .setResourceInternalPath("img/button_click.bmp")
                    .setResourceType(ResourceType.TEXTURE)
    };

    @Override
    public void loadAssets() {
        loadAssets(this.assets);
    }

    @Override
    public void unloadAssets() {
        unloadAssets(this.assets);
    }
}
