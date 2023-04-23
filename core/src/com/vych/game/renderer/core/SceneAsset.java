package com.vych.game.renderer.core;

import com.vych.game.managers.resources.entities.core.ResourceType;

public class SceneAsset {
    private String resourceName;
    private String resourceInternalPath;
    private ResourceType resourceType;

    public String getResourceName() {
        return this.resourceName;
    }

    public SceneAsset setResourceName(String resourceName) {
        this.resourceName = resourceName;
        return this;
    }

    public String getResourceInternalPath() {
        return this.resourceInternalPath;
    }

    public SceneAsset setResourceInternalPath(String resourceInternalPath) {
        this.resourceInternalPath = resourceInternalPath;
        return this;
    }

    public ResourceType getResourceType() {
        return this.resourceType;
    }

    public SceneAsset setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
        return this;
    }
}
