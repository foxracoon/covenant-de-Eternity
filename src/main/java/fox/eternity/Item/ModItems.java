package fox.eternity.Item;

import fox.eternity.CovenantDeEternity;
import fox.eternity.Item.Items.CameraOfTheOthersideItem;
import fox.eternity.Item.Items.Guitar;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {

    // Heir's Tomfoolery:

    public static final Item CAMERA_OF_THE_OTHERSIDE = registerItem("camera_of_the_otherside",
            new CameraOfTheOthersideItem(new FabricItemSettings().maxCount(1).fireproof()));
    //CTRL-C CTRL-V the already existing camera model for this model PLEASE CHANGE THAT IF THAT CAMERA WAS MEANT TO BE USED SOMEWHERE ELSE

    public static Item FOX_GUITAR = registerItem("fox_guitar", new Guitar(ToolMaterials.DIAMOND, 4,-2.4f,
            new FabricItemSettings().group(ItemGroup.COMBAT)));


    private static Item registerItem(String name, Item CamaraOfTheOtherSideItem) {
        return Registry.register(Registry.ITEM, new Identifier(CovenantDeEternity.MOD_ID, name), CamaraOfTheOtherSideItem);
    }

    public static void registerModItems() {
        CovenantDeEternity.LOGGER.debug("Registering Mod Items for " + CovenantDeEternity.MOD_ID);
    }
}


