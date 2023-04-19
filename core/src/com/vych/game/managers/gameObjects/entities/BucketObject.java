package com.vych.game.managers.gameObjects.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.vych.game.managers.gameObjects.entities.core.BasicGameObject;
import com.vych.game.managers.resources.ResourcesManager;
import com.vych.game.managers.resources.entities.TextureResource;

/**
 * Класс игрового объекта "Ведро".
 * Ведро - управляемый игроком объект. Перемещается игроком влево-вправо. Имеет взаимодействие с {@link DropObject},
 * логика которого описана внутри объекта капли.
 */
public class BucketObject extends BasicGameObject {
    private OrthographicCamera camera;

    public BucketObject(Long id) {
        super(id);
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public BucketObject setCamera(OrthographicCamera camera) {
        this.camera = camera;
        return this;
    }

    @Override
    public void instanceStep() {
        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            bounds.x = touchPos.x - 64f / 2;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) bounds.x -= 200 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) bounds.x += 200 * Gdx.graphics.getDeltaTime();
        if (bounds.x < 0) bounds.x = 0;
        if (bounds.x > 800 - 64) bounds.x = 800 - 64;
    }

    @Override
    public void instanceCreate() {
        bounds.x = 800f / 2 - 64f / 2;
        bounds.y = 20;
        bounds.width = 64;
        bounds.height = 64;

        textureResource = ResourcesManager.getInstance().getByName("bucketImage", TextureResource.class);
    }
}
