package fox.eternity.mixin;

import fox.eternity.Enchantment.ModEnchantments;
import fox.eternity.Item.ModItems;
import fox.eternity.effect.ModEffects;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private void onLethalDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (!((Object) this instanceof ServerPlayerEntity killed)) return;
        float health = killed.getHealth();
        if (health > amount) return;
        Entity attacker = source.getAttacker();
        if (!(attacker instanceof ServerPlayerEntity killer)) return;
        ItemStack weapon = killer.getMainHandStack();
        if (EnchantmentHelper.getLevel(ModEnchantments.ARCANE_DAMAGE, weapon) > 0) {
            Text customMessage = Text.translatable(
                    "death.attack.arcane.item",                         // translation key
                    killed.getDisplayName(),                              // %1$s = victim name
                    killer.getDisplayName(),                              // %2$s = killer name
                    weapon.getName()                                      // %3$s = item name
            );
            killer.getServer().getPlayerManager().broadcast(customMessage, false);
        }
        if (hasCameraItem(killer)) {
            cir.setReturnValue(false); // cancel death
            triggerTotemEffect(killed); // heal and freeze logic
        }
    }
    private boolean hasCameraItem(ServerPlayerEntity player) {
        for (ItemStack stack : player.getInventory().main) {
            if (stack.isOf(ModItems.CAMERA_OF_THE_OTHERSIDE)) {
                return true;
            }
        }
        return false;
    }
    private void triggerTotemEffect(ServerPlayerEntity killed) {
        killed.setHealth(20.0F);
        killed.addStatusEffect(new StatusEffectInstance(ModEffects.ChainedEffect, 200000000, 1, false, false, false));
        killed.setVelocity(Vec3d.ZERO);
        killed.velocityModified = true;
        //Play kill sound
        killed.getWorld().playSound(
                null, killed.getBlockPos(), SoundEvents.BLOCK_BELL_RESONATE,
                SoundCategory.PLAYERS, 15.0F, 1.0F
        );
    }

    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private void redirectToMagicDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (!(source.getAttacker() instanceof LivingEntity attacker)) return;
        ItemStack stack = attacker.getMainHandStack();
        if (EnchantmentHelper.getLevel(ModEnchantments.ARCANE_DAMAGE, stack) <= 0) return;
        LivingEntity target = (LivingEntity) (Object) this;
        cir.setReturnValue(false);
        cir.cancel();
        DamageSource sources = target.getRecentDamageSource();
        target.damage(DamageSource.MAGIC, amount);
    }
}
