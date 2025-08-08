package fox.eternity.entity.entities;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

public class ChainsEntityRenderer <T extends Entity> extends EntityRenderer<T> {
    public ChainsEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    //Empty rendering
    @Override
    public void render(T entity, float yaw, float tickDelta, MatrixStack matrices,
                       VertexConsumerProvider vertexConsumers, int light) {
    }

    @Override
    public Identifier getTexture(T entity) {
        return null;
    }
}
