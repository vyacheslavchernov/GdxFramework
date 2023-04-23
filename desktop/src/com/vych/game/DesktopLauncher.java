package com.vych.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.vych.game.scenes.MenuScene;
import com.vych.game.scenes.core.BasicScene;
import com.vych.game.utils.PropertiesLoader;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
    public static void main(String[] arg) {
        PropertiesLoader propertiesLoader = PropertiesLoader.getInstance();

        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setForegroundFPS(propertiesLoader.getInt("game.config.desktop.targetFps"));
        config.setTitle(propertiesLoader.getString("game.config.desktop.title"));
        config.setWindowedMode(
                propertiesLoader.getInt("game.config.desktop.w"),
                propertiesLoader.getInt("game.config.desktop.h")
        );
        config.useVsync(propertiesLoader.getBool("game.config.desktop.vsync"));
        config.setWindowIcon("icon.png");

        launch(config, MenuScene.class);
    }

    public static void launch(Lwjgl3ApplicationConfiguration config, Class<? extends BasicScene> startupScene) {
        SampleGame.setStartupScene(startupScene);
        new Lwjgl3Application(SampleGame.getInstance(), config);
    }
}
