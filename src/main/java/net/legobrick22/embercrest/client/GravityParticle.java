package net.legobrick22.embercrest.client;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.texture.TextureAtlas;

public class GravityParticle extends SingleQuadParticle {

    public static final SingleQuadParticle.Layer GRAVITY_PARTICLE = new SingleQuadParticle.Layer(
            // Whether the particle will have textures that are not fully opaque.
            false,
            // The texture atlas used to get the sprite from.
            // This should match `TextureAtlasSprite#atlasLocation`.
            TextureAtlas.LOCATION_PARTICLES,
            // The render pipeline used to draw the particle.
            // Custom render pipelines should be based from `RenderPipelines#PARTICLE_SNIPPET`
            // to specify the available uniforms and samplers.
            RenderPipelines.WEATHER_DEPTH_WRITE
    );

    private final SpriteSet spriteSet;

    // First four parameters are self-explanatory.
    // The sprite set or atlas sprite are typically given through the provider, see below.
    // Additional parameters can be added as needed, e.g., xSpeed/ySpeed/zSpeed.
    public GravityParticle(ClientLevel level, double x, double y, double z, SpriteSet spriteSet) {
        // Initial sprite set in constructor
        super(level, x, y, z, spriteSet.first());
        this.spriteSet = spriteSet;
        this.gravity = 0; // Our particle floats in midair now, because why not.
    }

    @Override
    public void tick() {
        // Let super handle movement.
        // You may replace this with your own movement if needed.
        // You may also override move() if you only want to modify the built-in movement.
        super.tick();

        // Set the sprite for the current particle age, i.e. advance the animation.
        this.setSpriteFromAge(this.spriteSet);
    }

    @Override
    protected SingleQuadParticle.Layer getLayer() {
        // Sets the layer used to get and submit the texture.
        return Layer.OPAQUE;
    }
}