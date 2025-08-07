package fox.eternity.render;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.Window;

public class FlashOverlayRenderer {
    private static long flashStartTime = 0;
    private static final long FLASH_DURATION_MS = 500;

    public static void init() {
        HudRenderCallback.EVENT.register((matrix, delta) -> {
            if (!isFlashing()) return;
            float progress = (System.currentTimeMillis() - flashStartTime) / (float) FLASH_DURATION_MS;
            float alpha = 1.0f - progress;
            if (alpha < 0f) alpha = 0f;

            Window window = MinecraftClient.getInstance().getWindow();

            DrawableHelper.fill(matrix, 0, 0, window.getScaledWidth(), window.getScaledHeight(), ((int)(alpha * 190) << 24) | 0x8AFDFF);
        });
    }
    public static void triggerFlash() {
        flashStartTime = System.currentTimeMillis();
    }
    private static boolean isFlashing() {
        return System.currentTimeMillis() - flashStartTime < 500;
    }
}
