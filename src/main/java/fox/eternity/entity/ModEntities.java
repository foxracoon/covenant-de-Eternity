package fox.eternity.entity;

import fox.eternity.CovenantDeEternity;
import fox.eternity.entity.entities.ChainsEntity;
import fox.eternity.entity.entities.MagicMissileEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;;

public class ModEntities {
    public static final EntityType<ChainsEntity> ChainsEntity = Registry.register(Registry.ENTITY_TYPE,
            new Identifier(CovenantDeEternity.MOD_ID, "chains"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, ChainsEntity::new)
                    .dimensions(EntityDimensions.fixed(1f,1f)).build());

    public static final EntityType<MagicMissileEntity> MAGIC_MISSILE = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(CovenantDeEternity.MOD_ID, "magic_missile"),
            FabricEntityTypeBuilder.<MagicMissileEntity>create(SpawnGroup.MISC, MagicMissileEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5f, 0.5f))
                    .build()
    );

    public static void register() {
    }
}

