package us.spaceclouds42.territorialhax.mixin;

import io.github.profjb58.territorial.block.entity.LockableBlockEntity;
import io.github.profjb58.territorial.event.UseBlockHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import us.spaceclouds42.territorialhax.config.Config;


@Mixin(UseBlockHandler.class)
abstract class UseBlockHandlerMixin_MasterKey {
    @Inject(
            method = "interact",
            at = @At(
                    value = "HEAD"
            ),
            cancellable = true,
            remap = false
    )
    private void bypassKeyRequirement(PlayerEntity player, World world, Hand hand, BlockHitResult hitResult, CallbackInfoReturnable<ActionResult> cir) {
        if (Config.INSTANCE.getOptions().getMasterKey()) {
            cir.setReturnValue(ActionResult.PASS);
        }
    }
}
