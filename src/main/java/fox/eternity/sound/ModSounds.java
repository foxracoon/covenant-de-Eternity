package fox.eternity.sound;

import fox.eternity.CovenantDeEternity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModSounds {
    public static SoundEvent BUTTON_CLICK;
    public static SoundEvent FLASH;
    public static SoundEvent FILM_ADVANCE_LAST;



    public static void register() {
        BUTTON_CLICK = registerSoundEvent("button_click");
        FLASH = registerSoundEvent("flash");
        FILM_ADVANCE_LAST = registerSoundEvent("film_advance_last");

    }

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = new Identifier(CovenantDeEternity.MOD_ID, name);
        return Registry.register(Registry.SOUND_EVENT, id, new SoundEvent(id));
    }
}
