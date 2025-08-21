package fox.eternity.Item.Items;

import fox.eternity.Enchantment.ModEnchantments;
import fox.eternity.entity.entities.MagicMissileEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class Guitar extends SwordItem {
    private static final int MAX_AMMO = 32;
    private static final int RELOAD_TIME = 60;


    public Guitar(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (hasMagicMissile(stack)) {
            if (!stack.getOrCreateNbt().contains("ammo")) {
                setAmmo(stack, MAX_AMMO);
            }
            user.setCurrentHand(hand);
            return TypedActionResult.consume(stack);
        }
        user.setCurrentHand(hand);
        return TypedActionResult.success(stack, world.isClient());
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.NONE;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 72000;
    }

    private boolean hasMagicMissile(ItemStack stack) {
        return EnchantmentHelper.getLevel(ModEnchantments.MAGIC_MISSILE, stack) > 0;
    }

    private int getAmmo(ItemStack stack) {
        return stack.getOrCreateNbt().getInt("ammo");
    }

    private void setAmmo(ItemStack stack, int value) {
        stack.getOrCreateNbt().putInt("ammo", value);
    }

    private void reload(ItemStack stack) {
        setAmmo(stack, MAX_AMMO);
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        if (!(user instanceof PlayerEntity player)) return;
        if (hasMagicMissile(stack)) {
            if (!player.getItemCooldownManager().isCoolingDown(this) && world.getTime() % 4 == 0) {
                world.playSound(player, player.getBlockPos(), SoundEvents.ENTITY_BLAZE_SHOOT, SoundCategory.PLAYERS, 0.5f, 1.0f);
                shoot(world, player, stack);
            }
        }
    }

    private void shoot(World world, PlayerEntity user, ItemStack stack) {
        int ammo = getAmmo(stack);
        if (ammo - 1 <= 0 && !user.getItemCooldownManager().isCoolingDown(this)) {
            user.getItemCooldownManager().set(this, RELOAD_TIME);
            reload(stack);
        }
        if (ammo > 0 && !user.getItemCooldownManager().isCoolingDown(this)){
            if (!world.isClient) {
                MagicMissileEntity missile = new MagicMissileEntity(world, user);

                Vec3d lookVec = user.getRotationVec(1.0F);

                Vec3d spawnPos = user.getPos().add(lookVec.multiply(1.5));
                missile.setPos(spawnPos.x, spawnPos.y + user.getStandingEyeHeight(), spawnPos.z);
                missile.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 2.0F, 1.0F);

                missile.setOwner(user);
                world.spawnEntity(missile);
            }
            setAmmo(stack, ammo - 1);
        }
    }


    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return hasMagicMissile(stack);
    }

    @Override
    public int getItemBarStep(ItemStack stack) {
        if (!hasMagicMissile(stack)) return 0;
        int ammo = getAmmo(stack);
        float ratio = MathHelper.clamp((float) ammo / (float) MAX_AMMO, 0.0F, 1.0F);
        return Math.round(13.0F * ratio);
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        if (!hasMagicMissile(stack)) return super.getItemBarColor(stack);
        return 0xFFD700;
    }
}
