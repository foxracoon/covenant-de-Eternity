package fox.eternity.Enchantment;

import fox.eternity.CovenantDeEternity;
import fox.eternity.Enchantment.enchatments.ArcaneEnchantment;
import fox.eternity.Enchantment.enchatments.MagicMissileEnchantment;
import fox.eternity.Enchantment.enchatments.NoteBoomEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModEnchantments {
    public static final Enchantment ARCANE_DAMAGE = Registry.register(
            Registry.ENCHANTMENT,
            new Identifier(CovenantDeEternity.MOD_ID, "arcane"),
            new ArcaneEnchantment()
    );
    public static final Enchantment MAGIC_MISSILE = Registry.register(
            Registry.ENCHANTMENT,
            new Identifier(CovenantDeEternity.MOD_ID, "magic_missile"),
            new MagicMissileEnchantment()
    );
    public static final Enchantment NOTE_BOOM = Registry.register(
            Registry.ENCHANTMENT,
            new Identifier(CovenantDeEternity.MOD_ID, "note_boom"),
            new NoteBoomEnchantment()
    );



    public static void init() {

    }
}
