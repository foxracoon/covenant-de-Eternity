package fox.eternity.Enchantment.enchatments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.item.ToolItem;

public class ArcaneEnchantment extends Enchantment {
    public ArcaneEnchantment() {
        super(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[] { EquipmentSlot.MAINHAND });
    }
    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof ToolItem || stack.getItem() instanceof RangedWeaponItem;
    }
    @Override
    public boolean isTreasure() {
        return true;
    }
    // Optional: mark as incompatible with books if you want
    @Override
    public boolean isAvailableForEnchantedBookOffer() {
        return false; // or false if you want to exclude from villager trades
    }
    @Override
    public boolean isAvailableForRandomSelection() {
        return true; // include in loot table/random selection
    }
}

