package us.spaceclouds42.territorialhax

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.minecraft.client.MinecraftClient
import net.minecraft.client.options.KeyBinding
import net.minecraft.client.util.InputUtil
import net.minecraft.entity.player.PlayerEntity
import org.lwjgl.glfw.GLFW
import us.spaceclouds42.ekho.ekho
import us.spaceclouds42.territorialhax.config.MenuScreen

object Common : ClientModInitializer {
    lateinit var CLIENT: MinecraftClient

    private val guiKey = KeyBindingHelper.registerKeyBinding(
        KeyBinding(
            "Open Menu",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_RIGHT_SHIFT,
            "TerritorialHax"
        )
    )

    override fun onInitializeClient() {
        println("Good luck, profjb")

        ClientTickEvents.END_CLIENT_TICK.register {
            CLIENT = it
            while (guiKey.wasPressed()) {
                openMenu(it)
            }
        }

        ClientCommandManager.DISPATCHER.root.addChild(
            ClientCommandManager
                .literal("")
                .executes { 1 }
                .build()
        )
    }

    private fun openMenu(client: MinecraftClient) {
        client.openScreen(MenuScreen)
    }
}

