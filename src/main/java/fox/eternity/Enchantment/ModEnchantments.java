package fox.eternity.Enchantment;

import fox.eternity.Enchantment.enchatments.ArcaneEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModEnchantments {
    public static final Enchantment ARCANE_DAMAGE = Registry.register(
            Registry.ENCHANTMENT,
            new Identifier("coven", "arcane"),
            new ArcaneEnchantment()
    );
    public static void init() {

    }
}
