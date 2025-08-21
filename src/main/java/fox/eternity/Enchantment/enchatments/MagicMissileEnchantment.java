package fox.eternity.Enchantment.enchatments;

import fox.eternity.Item.Items.Guitar;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;

public class MagicMissileEnchantment extends Enchantment {
    public MagicMissileEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[] { EquipmentSlot.MAINHAND });
    }
    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof Guitar;
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

