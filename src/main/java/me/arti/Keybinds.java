package me.arti;

import me.arti.utils.EntityUtils;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import me.arti.utils.playerlook.GetTargt;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;


public class Keybinds
{
    private boolean aimbotToggled = false;
    private boolean tracersToggled = false;

    @SideOnly(Side.CLIENT)
    @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
    public void onEvent(InputEvent.KeyInputEvent event) {

        //tracers
//        KeyBinding hotkey = Inits.hotkey;
//        if (hotkey.isPressed()) {
//            //System.out.println("Keybind pressed");
//            StartTracers();
//        }

        //aimbot
        KeyBinding hotkey = Inits.hotkey;
        if (hotkey.isPressed()) {
            //System.out.println("Keybind pressed");
            StartAimbot();
        }

        KeyBinding hotkey2 = Inits.hotkey2;
        if (hotkey2.isPressed()) {
            fullBright();
        }
    }

    //tracers code
    private void StartTracers(){
        if(!tracersToggled){
            tracersToggled = true;
        }
        else{
            tracersToggled = false;
        }
    }





    //aim locking code

    //target
    private Entity target;

    //main aimbot function
    public void StartAimbot() {
        //target = GetTargt.getTarget(1.0f, 100);
        target = GetTargt.getTargetNew();
        if(target != null && !aimbotToggled) {
            aimbotToggled = true;
        }
        else {
            aimbotToggled = false;
        }
    }

    //updates every render tick for smoothness
    @SideOnly(Side.CLIENT)
    @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
    public void onTick(TickEvent.RenderTickEvent event) {

        //if toggled and can see the target
        if(aimbotToggled){
            if(target != null){
                if(Minecraft.getMinecraft().thePlayer.canEntityBeSeen(target) && target.isEntityAlive()){
                    aimAtTarget();
                }
            }
        }
    }

    //aims at target
    public void aimAtTarget() {
        if (target == null) {
            aimbotToggled = false;
            return;
        }
        //checks target is a living entity and that its within the angle limit
        if (target instanceof EntityLivingBase && (GetTargt.CheckSnap())) {
            EntityUtils.faceEntityClient((EntityLivingBase) target);
        }
        //unlocks if angle exceeds maximum
        else{
            target = null;
            aimbotToggled = false;
        }
    }

    static final Minecraft mc = Minecraft.getMinecraft();

    private static float initialgamma = -1.0f;

    private static float maxgamma = 15.0f;

    public void fullBright() {
        GameSettings settings = mc.gameSettings;
        if (initialgamma < 0.0f)
            if (settings.gammaSetting >= 1.0f) {
                initialgamma = 1.0f;
                settings.gammaSetting = 1.0f;
            } else {
                initialgamma = settings.gammaSetting;
            }
        boolean gomax = false;
        if (settings.gammaSetting != initialgamma && settings.gammaSetting != maxgamma) {
            initialgamma = settings.gammaSetting;
            gomax = true;
        }
        if (settings.gammaSetting == initialgamma || gomax) {
            settings.gammaSetting = maxgamma;
        } else {
            settings.gammaSetting = initialgamma;
        }
    }
}