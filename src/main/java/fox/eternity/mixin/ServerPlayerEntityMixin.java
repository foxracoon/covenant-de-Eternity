package fox.eternity.mixin;

import com.mojang.authlib.GameProfile;
import fox.eternity.CovenantDeEternity;
import fox.eternity.Item.Items.TrappedState;
import fox.eternity.effect.ModEffects;
import fox.eternity.entity.ModEntities;
import fox.eternity.entity.entities.ChainsEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends PlayerEntity implements TrappedState {
    public ServerPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile gameProfile) {
        super(world, pos, yaw, gameProfile,null);
    }

    @Unique
    private ChainsEntity trappedChains;

    @Override
    public ChainsEntity getTrappedChains() {
        return this.trappedChains;
    }

    @Override
    public void setTrappedChains(ChainsEntity chains) {
        this.trappedChains = chains;
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void cancelMovementWhenFrozen(CallbackInfo ci) {
        if (this.hasStatusEffect(ModEffects.ChainedEffect)) {
            StatusEffectInstance chained = this.getStatusEffect(ModEffects.ChainedEffect);
            World serverWorld = this.getWorld();
            ChainsEntity chains = new ChainsEntity(ModEntities.ChainsEntity, serverWorld);
            if (chained != null) {
                this.setVelocity(Vec3d.ZERO);
                chains.setVelocity(Vec3d.ZERO);
                if (!this.isTrapped()) {
                    this.setTrapped(true);
                    CovenantDeEternity.LOGGER.info("Player " + this.getName().getString() + " is now trapped.");
                    Vec3d pos = this.getPos();
                    chains.refreshPositionAndAngles(pos.x, pos.y, pos.z, 0, 0);
                    chains.addStatusEffect(new StatusEffectInstance(ModEffects.ChainedEffect, 200000000, 1, false, false, false));
                    serverWorld.spawnEntity(chains);
                    chains.setPlayerUuid(this.getUuid());
                    this.velocityModified = true;
                    chains.velocityModified = true;
                    this.trappedChains = chains;
                }
            }
        }
    }
}

