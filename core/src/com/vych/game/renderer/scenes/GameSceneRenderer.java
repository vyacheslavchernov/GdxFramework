package com.vych.game.renderer.scenes;

import com.vych.game.managers.resources.entities.core.ResourceType;
import com.vych.game.renderer.core.BasicSceneRenderer;
import com.vych.game.renderer.core.SceneAsset;

import java.util.ArrayList;
import java.util.List;

public class GameSceneRenderer extends BasicSceneRenderer {
    public GameSceneRenderer() {
        super();
        assets = new ArrayList<>();
        assets.addAll(List.of(
                        new SceneAsset()
                                .setResourceName("dropImage")
                                .setResourceInternalPath("img/droplet.png")
                                .setResourceType(ResourceType.TEXTURE),

                        new SceneAsset()
                                .setResourceName("bucketImage")
                                .setResourceInternalPath("img/bucket.png")
                                .setResourceType(ResourceType.TEXTURE),

                        new SceneAsset()
                                .setResourceName("dropSound")
                                .setResourceInternalPath("snd/drop.wav")
                                .setResourceType(ResourceType.SOUND),

                        new SceneAsset()
                                .setResourceName("rainMusic")
                                .setResourceInternalPath("snd/rain.mp3")
                                .setResourceType(ResourceType.MUSIC),

                        new SceneAsset()
                                .setResourceName("defaultFont")
                                .setResourceInternalPath("fonts/font.otf")
                                .setResourceType(ResourceType.FONT)
                )
        );
    }
}
