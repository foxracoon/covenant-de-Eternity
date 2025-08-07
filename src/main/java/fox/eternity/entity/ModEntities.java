package fox.eternity.entity;

import fox.eternity.CovenantDeEternity;
import fox.eternity.entity.entities.ChainsEntity;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;;

public class ModEntities {
    public static final EntityType<ChainsEntity> ChainsEntity = Registry.register(Registry.ENTITY_TYPE,
            new Identifier(CovenantDeEternity.MOD_ID, "knight"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, ChainsEntity::new)
                    .dimensions(EntityDimensions.fixed(1f,1f)).build());
}

