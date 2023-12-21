package com.vych.game.renderer.scenes;

import com.vych.game.managers.resources.entities.core.ResourceType;
import com.vych.game.renderer.core.BasicSceneRenderer;
import com.vych.game.renderer.core.SceneAsset;

import java.util.ArrayList;
import java.util.List;

public class MenuSceneRenderer extends BasicSceneRenderer {
    public MenuSceneRenderer() {
        super();
        assets = new ArrayList<>();
        assets.addAll(List.of(
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
                )
        );
    }
}
