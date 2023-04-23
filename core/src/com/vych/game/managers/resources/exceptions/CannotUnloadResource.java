package com.vych.game.managers.resources.exceptions;

import java.io.IOException;

public class CannotUnloadResource extends IOException {
    public CannotUnloadResource(String message) {
        super(message);
    }
}
