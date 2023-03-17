package com.vych.game.managers.resources.exceptions;

import java.io.IOException;

public class CannotLoadResource extends IOException {
    public CannotLoadResource(String message) {
        super(message);
    }
}
