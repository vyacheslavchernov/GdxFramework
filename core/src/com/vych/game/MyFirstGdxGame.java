package com.vych.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.vych.game.managers.resources.ResourcesManager;
import com.vych.game.managers.resources.entities.MusicResource;
import com.vych.game.managers.resources.entities.ResourceType;
import com.vych.game.managers.resources.entities.SoundResource;
import com.vych.game.managers.resources.entities.TextureResource;
import com.vych.game.managers.resources.exceptions.CannotLoadResource;
import com.vych.game.managers.resources.exceptions.CannotUnloadResource;

import java.util.Iterator;

public class MyFirstGdxGame extends ApplicationAdapter {
	private OrthographicCamera camera;
	private SpriteBatch batch;

	private Rectangle bucket;
	private Array<Rectangle> raindrops;
	private long lastDropTime;

	private void spawnRaindrop() {
		Rectangle raindrop = new Rectangle();
		raindrop.x = MathUtils.random(0, 800-64);
		raindrop.y = 480;
		raindrop.width = 64;
		raindrop.height = 64;
		raindrops.add(raindrop);
		lastDropTime = TimeUtils.nanoTime();
	}

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


		bucket = new Rectangle();
		bucket.x = 800f / 2 - 64f / 2;
		bucket.y = 20;
		bucket.width = 64;
		bucket.height = 64;

		raindrops = new Array<Rectangle>();
		spawnRaindrop();
	}

	@Override
	public void render () {
		ResourcesManager resourcesManager = ResourcesManager.getInstance();
		ScreenUtils.clear(0, 0, 0.2f, 1);
		camera.update();

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(resourcesManager.getByName("bucketImage", TextureResource.class).getContentCasted(), bucket.x, bucket.y);
		for(Rectangle raindrop: raindrops) {
			batch.draw(resourcesManager.getByName("dropImage", TextureResource.class).getContentCasted(), raindrop.x, raindrop.y);
		}
		batch.end();

		if(Gdx.input.isTouched()) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			bucket.x = touchPos.x - 64f / 2;
		}

		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) bucket.x -= 200 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) bucket.x += 200 * Gdx.graphics.getDeltaTime();
		if(bucket.x < 0) bucket.x = 0;
		if(bucket.x > 800 - 64) bucket.x = 800 - 64;

		if(TimeUtils.nanoTime() - lastDropTime > 1000000000) spawnRaindrop();

		for (Iterator<Rectangle> iter = raindrops.iterator(); iter.hasNext(); ) {
			Rectangle raindrop = iter.next();
			raindrop.y -= 200 * Gdx.graphics.getDeltaTime();

			if(raindrop.overlaps(bucket)) {
				Sound dropSound = resourcesManager.getByName("dropSound", SoundResource.class).getContentCasted();
				dropSound.play();
				iter.remove();
			}

			if(raindrop.y + 64 < 0) iter.remove();
		}
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
