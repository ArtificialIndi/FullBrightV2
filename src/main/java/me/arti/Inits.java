package me.arti;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.client.settings.KeyBinding;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "wcli", name = "warClient", version = "0.1")

public class Inits {

    public static KeyBinding hotkey;
    public static KeyBinding hotkey2;
    //public static KeyBinding hotkey2;


    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        //Item/Block init and registering
        //Config handling
    }

    @EventHandler
    public void Init(FMLInitializationEvent event) {
        //Proxy, TileEntity, entity, GUI and Packet registering
//        hotkey2 = new KeyBinding("Make Fullbright Brighter", 29, "key.categories.misc");
//        ClientRegistry.registerKeyBinding(hotkey2);
        hotkey = new KeyBinding("Toggle Fullbright", 29, "Fullbright Keybinds");
        hotkey2 = new KeyBinding("Toggle Fullbright But Makes Aim Gooder", 29, "Fullbright Keybinds");
        ClientRegistry.registerKeyBinding(hotkey);
        ClientRegistry.registerKeyBinding(hotkey2);
        FMLCommonHandler.instance().bus().register(new Keybinds());
        //enables fullbright
    }

    @EventHandler
    public void PostInit(FMLPostInitializationEvent event) {
        //
        //System.out.println("Aimbot mod loaded, credit to arti :D");
    }
}