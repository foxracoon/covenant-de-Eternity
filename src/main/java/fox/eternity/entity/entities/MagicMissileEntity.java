package fox.eternity.entity.entities;

import fox.eternity.entity.ModEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class MagicMissileEntity extends ArrowEntity {


    public MagicMissileEntity(EntityType<? extends MagicMissileEntity> type, World world) {
        super(type, world);
    }

    public MagicMissileEntity(World world, LivingEntity owner) {
        super(ModEntities.MAGIC_MISSILE, world);
        this.setOwner(owner);
    }

    @Override
    public void tick() {
        super.tick();

        if (this.world instanceof ServerWorld serverWorld) {
            for (int i = 0; i < 2; i++) {
                double offsetX = (this.random.nextDouble() - 0.5) * 0.2;
                double offsetY = (this.random.nextDouble() - 0.5) * 0.2;
                double offsetZ = (this.random.nextDouble() - 0.5) * 0.2;

                serverWorld.spawnParticles(
                        ParticleTypes.END_ROD,
                        this.getX() + offsetX,
                        this.getY() + 0.5 + offsetY,
                        this.getZ() + offsetZ,
                        1,
                        0, 0, 0,
                        0
                );
            }
        }
    }


    @Override
    protected ItemStack asItemStack() {
        return new ItemStack(ItemStack.EMPTY.getItem());
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);
        this.remove(RemovalReason.DISCARDED);
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        if (!this.world.isClient) {
            //If you want, you can make a custom explosion, but I just used the normal mc one

            world.createExplosion(
                    this,
                    this.getX(),
                    this.getY(),
                    this.getZ(),
                    1.0f,
                    false,
                    Explosion.DestructionType.NONE
            );
        }
    }

    @Override
    protected SoundEvent getHitSound() {
        return SoundEvents.BLOCK_GLASS_BREAK;
    }
}

