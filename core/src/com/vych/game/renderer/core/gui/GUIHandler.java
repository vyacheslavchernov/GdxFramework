package com.vych.game.renderer.core.gui;

import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class GUIHandler {
    private Map<String, GUIComponent> componentsMap = new HashMap<>();

    public void addComponent(GUIComponent component) {
        this.componentsMap.put(component.getName(), component);
    }

    public void deleteComponent(GUIComponent component) {
        this.componentsMap.remove(component.getName());
    }

    public void deleteComponent(String name) {
        this.componentsMap.remove(name);
    }

    public GUIComponent getComponent(String name) {
        return this.componentsMap.get(name);
    }

    public <T extends GUIComponent> T getComponentCasted(String name, Class<T> castTo) {
        return castTo.cast(this.componentsMap.get(name));
    }

    public void renderComponents(Batch batch) {
        Collection<GUIComponent> components = new ArrayList<>(this.componentsMap.values());
        for (GUIComponent component : components) {
            component.update();
            component.render(batch);
        }
    }
}
