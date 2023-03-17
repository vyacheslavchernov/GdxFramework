package com.vych.game.managers.gameObjects.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;
import com.vych.game.managers.gameObjects.GameObjectsManager;
import com.vych.game.managers.resources.ResourcesManager;
import com.vych.game.managers.resources.entities.SoundResource;
import com.vych.game.managers.resources.entities.TextureResource;
import com.vych.game.utils.Stash;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;

public class DropObject extends BasicGameObject {
    public DropObject(Long id) {
        super(id);
    }

    @Override
    public void instanceStep() {
        if(TimeUtils.nanoTime() - (Long) Stash.get("lastDropTime") > 1000000000) {
            try {
                GameObjectsManager.getInstance().instantiateGameObject(DropObject.class);
            } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        bounds.y -= 200 * Gdx.graphics.getDeltaTime();

        BucketObject bucketObject = (BucketObject) GameObjectsManager.getInstance()
                .getObjectsByClass(BucketObject.class).values().toArray()[0];

        if(bounds.overlaps(bucketObject.getBounds())) {
                Sound dropSound = ResourcesManager.getInstance().getByName("dropSound", SoundResource.class).getContentCasted();
                dropSound.play();
                selfDestruct();
        }

        if(bounds.y + 64 < 0) selfDestruct();
    }

    @Override
    public void instanceCreate() {
        bounds.x = MathUtils.random(0, 800-64);
        bounds.y = 480;
        bounds.width = 64;
        bounds.height = 64;
        Stash.add("lastDropTime", TimeUtils.nanoTime());

        textureResource = ResourcesManager.getInstance().getByName("dropImage", TextureResource.class);
    }
}
