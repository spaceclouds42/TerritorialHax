package us.spaceclouds42.territorialhax.mixin;

import io.github.cottonmc.cotton.gui.client.CottonHud;
import io.github.cottonmc.cotton.gui.widget.WTiledSprite;
import io.github.profjb58.territorial.Territorial;
import io.github.profjb58.territorial.block.LockableBlock;
import io.github.profjb58.territorial.client.gui.LockableHud;
import io.github.profjb58.territorial.mixin.OverlayRemainingAccessor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.util.Window;
import net.minecraft.item.Item;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import us.spaceclouds42.territorialhax.config.Config;

@Mixin(LockableHud.class)
abstract class LockableHudMixin_DeObfuscate {
    @Shadow(remap=false) private boolean ignoreCycle;
    @Shadow(remap=false) abstract void reset();
    @Shadow(remap=false) private WTiledSprite lockImage;
    @Shadow(remap=false) abstract String getLockableFormattingColour(LockableBlock.LockType lockType);

    /**
     * @author hacker :P
     * @reason get hacked
     */
    @Overwrite(remap=false)
    public void showLockInfo(LockableBlock lb, ClientPlayerEntity player) {
        if(!ignoreCycle) {
            if(lb.exists()) {
                reset();

                Window window = MinecraftClient.getInstance().getWindow();
                int hudHeight = window.getScaledHeight();
                int hudWidth = window.getScaledWidth();

                String lockId, lockOwner;
                if(lb.getLockOwnerUuid().equals(player.getUuid())) {
                    lockId = lb.getLockId();
                    lockOwner = lb.getLockOwnerName();
                }
                else {
                    // look at me, i did a hack!!
                    if (Config.INSTANCE.getOptions().getDeObfuscate()) {
                        lockId = "§c" + lb.getLockId();
                        lockOwner = "§c" + lb.getLockOwnerName();
                    } else {
                        lockId = "§k" + lb.getLockId();
                        lockOwner = "§k" + lb.getLockOwnerName();
                    }
                }

                String fc = getLockableFormattingColour(lb.getLockType());
                LiteralText lockInfoText = new LiteralText(fc + "Id: §f" + lockId + "   " + fc + "Owner: §f" + lockOwner);
                player.sendMessage(lockInfoText, true);

                Item item = lb.getLockItemStack(1).getItem();
                lockImage = new WTiledSprite(32, 32, new Identifier(Territorial.MOD_ID, "textures/item/" + item.toString() + ".png"));

                CottonHud.INSTANCE.add(lockImage, (hudWidth / 2) - 16, hudHeight - 100);
            }
        }
        else {
            InGameHud inGameHud = MinecraftClient.getInstance().inGameHud;
            if(((OverlayRemainingAccessor) inGameHud).getOverlayRemaining() == 0) {
                ignoreCycle = false;
            }
        }
    }
}
