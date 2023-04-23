package com.vych.game.managers.gameObjects.entities.gameScene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.vych.game.managers.gameObjects.GameObjectsManager;
import com.vych.game.managers.gameObjects.entities.core.BasicGameObject;
import com.vych.game.managers.resources.ResourcesManager;
import com.vych.game.managers.resources.entities.SoundResource;
import com.vych.game.managers.resources.entities.TextureResource;
import com.vych.game.scenes.core.BasicScene;
import com.vych.game.utils.Stash;

import java.lang.reflect.InvocationTargetException;

/**
 * Класс игрового объекта "Капля".
 * Капля - объект, который не управляется игроком. Логика поведения объекта заключается в постепенном перемещении
 * по оси У, что эмитирует её падение. При выходе за пределы экрана или контакте с {@link BucketObject} капля удаляется.
 * С определённым интервалом создаётся новая капля.
 */
public class DropObject extends BasicGameObject {
    public DropObject(Long id, BasicScene scene) {
        super(id, scene);
    }

    @Override
    public void instanceStep() {
        float rainSpeed = (float) Stash.get("rainSpeed");
        if (TimeUtils.nanoTime() - (Long) Stash.get("lastDropTime") > 1000000000 - rainSpeed) {
            createNewDrop();
        }

        this.bounds.y -= rainSpeed * Gdx.graphics.getDeltaTime();

        GameObjectsManager gom = GameObjectsManager.getInstance();
        BucketObject bucketObject = (BucketObject) gom.getObjectsByClass(BucketObject.class).values().toArray()[0];
        GlobalHUDObject hud = (GlobalHUDObject) gom.getObjectsByClass(GlobalHUDObject.class).values().toArray()[0];

        if (this.bounds.overlaps(bucketObject.getBounds())) {
            Sound dropSound = ResourcesManager.getInstance().getByName("dropSound", SoundResource.class).getContentCasted();
            dropSound.play();
            createNewDrop();
            hud.incrementScore();
            if (hud.getScore() % 10 == 0) {
                Stash.add("rainSpeed", rainSpeed + 20);
            }
            selfDestruct();
        }

        if (this.bounds.y + 64 < 0) {
            hud.incrementLooses();
            createNewDrop();
            selfDestruct();
        }
    }

    @Override
    public void instanceCreate() {
        this.bounds.x = MathUtils.random(0, 800 - 64);
        this.bounds.y = 480;
        this.bounds.width = 64;
        this.bounds.height = 64;
        Stash.add("lastDropTime", TimeUtils.nanoTime());

        if (Stash.get("rainSpeed") == null) {
            Stash.add("rainSpeed", 200f);
        }

        this.textureResource = ResourcesManager.getInstance().getByName("dropImage", TextureResource.class);
    }

    private void createNewDrop() {
        try {
            GameObjectsManager.getInstance().instantiateGameObject(DropObject.class, this.scene);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
