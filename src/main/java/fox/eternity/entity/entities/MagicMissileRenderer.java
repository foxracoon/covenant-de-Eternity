package fox.eternity.entity.entities;

import fox.eternity.CovenantDeEternity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class MagicMissileRenderer extends ProjectileEntityRenderer<MagicMissileEntity> {

    public MagicMissileRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public void render(MagicMissileEntity entity, float yaw, float tickDelta, MatrixStack matrices,
                       VertexConsumerProvider vertexConsumers, int light) {
        int fullBright = 0xF000F0;

        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, fullBright);
    }

    @Override
    public Identifier getTexture(MagicMissileEntity arrowEntity) {
        return Identifier.of(CovenantDeEternity.MOD_ID, "textures/entity/magic_missile.png");
    }
}



