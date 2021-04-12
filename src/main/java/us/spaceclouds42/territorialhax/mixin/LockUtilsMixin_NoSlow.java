package us.spaceclouds42.territorialhax.mixin;

import io.github.profjb58.territorial.util.LockUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import us.spaceclouds42.territorialhax.config.Config;

@Mixin(LockUtils.class)
abstract class LockUtilsMixin_NoSlow {
    @Mixin(LockUtils.Calculations.class)
    private static class Calculations {
        @Inject(
                method = "getLockFatigueMultiplier",
                at = @At(
                       value = "HEAD"
                ),
                cancellable = true,
                remap = false
        )
        private static void speed(float amplifier, CallbackInfoReturnable<Float> cir) {
            if (Config.INSTANCE.getOptions().getNoSlow()) {
                cir.setReturnValue(1.0F);
            }
        }
    }
}
