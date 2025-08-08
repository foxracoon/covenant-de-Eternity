package fox.eternity;

import fox.eternity.Network.FlashPacket;
import fox.eternity.entity.ModEntities;
import fox.eternity.entity.entities.ChainsEntity;
import fox.eternity.entity.entities.ChainsEntityRenderer;
import fox.eternity.render.FlashOverlayRenderer;
import fox.eternity.render.ModModelLayers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class CovenantDeEternityClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.ChainsEntity, ChainsEntityRenderer::new);

        FlashPacket.registerClientReceiver();
        FlashOverlayRenderer.init();

    }
}
