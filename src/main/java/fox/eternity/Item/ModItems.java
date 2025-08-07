package fox.eternity.Item;

import fox.eternity.CovenantDeEternity;
import fox.eternity.Item.Items.CameraOfTheOthersideItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {

    // Heir's Tomfoolery:

    public static final Item CAMERA_OF_THE_OTHERSIDE = registerItem("camera_of_the_otherside",
            new CameraOfTheOthersideItem(new FabricItemSettings().maxCount(1).fireproof()));


    private static Item registerItem(String name, Item CamaraOfTheOtherSideItem) {
        return Registry.register(Registry.ITEM, new Identifier(CovenantDeEternity.MOD_ID, name), CamaraOfTheOtherSideItem);
    }

    public static void registerModItems() {
        CovenantDeEternity.LOGGER.debug("Registering Mod Items for " + CovenantDeEternity.MOD_ID);
    }
}


