package fox.eternity.effect;

import fox.eternity.CovenantDeEternity;

import fox.eternity.effect.effects.ChainedEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;

public class ModEffects {

    public static StatusEffect ChainedEffect;

    public static StatusEffect registerStatusEffect(String name, StatusEffect effect) {
        return Registry.register(Registry.STATUS_EFFECT, new Identifier(CovenantDeEternity.MOD_ID, name), effect);
    }

    public static void registerEffects() {
        ChainedEffect = registerStatusEffect("soullock", new ChainedEffect());
    }
}