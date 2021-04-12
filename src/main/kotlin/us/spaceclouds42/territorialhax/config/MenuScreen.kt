package us.spaceclouds42.territorialhax.config

import me.lambdaurora.spruceui.Position
import me.lambdaurora.spruceui.option.SpruceBooleanOption
import me.lambdaurora.spruceui.screen.SpruceScreen
import me.lambdaurora.spruceui.widget.container.SpruceOptionListWidget
import us.spaceclouds42.ekho.ekho


object MenuScreen : SpruceScreen(ekho("TerritorialHax") { style { green; underline } }) {
    override fun init() {
        super.init()

        buildOptions()
    }

    private fun buildOptions() {
        val list = SpruceOptionListWidget(Position.of(0, 22), this.width, this.height - 35 - 22)

        list.addSingleOptionEntry(
            SpruceBooleanOption(
                "De-Obfuscate",
                { Config.options.deObfuscate },
                { Config.options.deObfuscate = it },
                ekho("Shows lock id and owner when hovering over locked blocks") { style { darkGreen } },
                true,
            )
        )

        list.addSingleOptionEntry(
            SpruceBooleanOption(
                "No Slow",
                { Config.options.noSlow },
                { Config.options.noSlow = it },
                ekho("Locks can't slow you down!") { style { darkAqua } },
                true,
            )
        )

        this.addChild(list)
    }
}
