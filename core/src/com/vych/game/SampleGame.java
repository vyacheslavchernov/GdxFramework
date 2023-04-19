package com.vych.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SampleGame extends Game {
    private SpriteBatch batch;
    private boolean pause = false;

    @Override
    public void create() {
        batch = new SpriteBatch();
        this.setScreen(new GameScreen(this));
    }

    public void render() {
        super.render();
    }

    public void dispose() {
        batch.dispose();
    }

    @Override
    public void pause() {
        super.pause();
        this.pause = true;
    }

    @Override
    public void resume() {
        super.resume();
        this.pause = false;
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public boolean isPause() {
        return pause;
    }
}
