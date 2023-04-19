package com.vych.game.renderer.core.gui;

import com.vych.game.screens.core.BasicScene;

public abstract class BasicGUIComponent implements GUIComponent {
    protected String name;
    protected float x;
    protected float y;
    protected String fontName;
    protected String text;
    protected BasicScene scene;

    public BasicGUIComponent(String name, BasicScene scene) {
        this.name = name;
        this.scene = scene;

        this.x = 0;
        this.y = 0;
        this.fontName = null;
        this.text = "";
    }

    @Override
    public String getName() {
        return this.name;
    }

    public float getX() {
        return this.x;
    }

    public BasicGUIComponent setX(float x) {
        this.x = x;
        return this;
    }

    public float getY() {
        return this.y;
    }

    public BasicGUIComponent setY(float y) {
        this.y = y;
        return this;
    }

    public String getFontName() {
        return this.fontName;
    }

    public BasicGUIComponent setFontName(String fontName) {
        this.fontName = fontName;
        return this;
    }

    public String getText() {
        return this.text;
    }

    public BasicGUIComponent setText(String text) {
        this.text = text;
        return this;
    }

    @Override
    public void update() {

    }
}
