package com.vych.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.vych.game.managers.gameObjects.entities.BucketObject;
import com.vych.game.managers.gameObjects.entities.DropObject;
import com.vych.game.managers.gameObjects.GameObjectsManager;
import com.vych.game.managers.gameObjects.entities.GameObject;
import com.vych.game.managers.resources.ResourcesManager;
import com.vych.game.managers.resources.entities.MusicResource;
import com.vych.game.managers.resources.entities.ResourceType;
import com.vych.game.managers.resources.entities.SoundResource;
import com.vych.game.managers.resources.entities.TextureResource;
import com.vych.game.managers.resources.exceptions.CannotLoadResource;
import com.vych.game.managers.resources.exceptions.CannotUnloadResource;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class MyFirstGdxGame extends ApplicationAdapter {
	private OrthographicCamera camera;
	private SpriteBatch batch;

	@Override
	public void create () {
		ResourcesManager resourcesManager = ResourcesManager.getInstance();
		try {
			resourcesManager.loadResource("dropImage", "droplet.png", ResourceType.TEXTURE);
			resourcesManager.loadResource("bucketImage", "bucket.png", ResourceType.TEXTURE);
			resourcesManager.loadResource("dropSound", "drop.wav", ResourceType.SOUND);
			resourcesManager.loadResource("rainMusic", "rain.mp3", ResourceType.MUSIC);
		} catch (CannotLoadResource e) {
			throw new RuntimeException(e);
		}

		Music rainMusic = resourcesManager.getByName("rainMusic", MusicResource.class).getContentCasted();
		rainMusic.setLooping(true);
		rainMusic.play();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);

		batch = new SpriteBatch();

		GameObjectsManager gameObjectsManager = GameObjectsManager.getInstance();
		try {
			BucketObject bucketObject = gameObjectsManager.instantiateGameObject(BucketObject.class);
			bucketObject.setCamera(camera);

			gameObjectsManager.instantiateGameObject(DropObject.class);
		} catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void render () {
		GameObjectsManager gameObjectsManager = GameObjectsManager.getInstance();
		List<GameObject> allObjects = new ArrayList<>(gameObjectsManager.getObjectsAll().values());

		ScreenUtils.clear(0, 0, 0.2f, 1);
		camera.update();

		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		for (GameObject obj : allObjects) {
			obj.instanceRender(batch);
			obj.instanceStep();
		}
		batch.end();
	}
	
	@Override
	public void dispose () {
		ResourcesManager resourcesManager = ResourcesManager.getInstance();
		try {
			resourcesManager.unloadResource("dropImage");
			resourcesManager.unloadResource("bucketImage");
			resourcesManager.unloadResource("dropSound");
			resourcesManager.unloadResource("rainMusic");
		} catch (CannotUnloadResource e) {
			throw new RuntimeException(e);
		}

		batch.dispose();
	}
}
