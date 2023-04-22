package com.vych.game.utils;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

public class CameraUtils {
    public static Vector3 unprojectToCamera(OrthographicCamera camera, float x, float y) {
        return camera.unproject(new Vector3(x, y, 0));
    }
}
