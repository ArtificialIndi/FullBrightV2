package me.arti.utils.playerlook;

import java.util.ArrayList;
import java.util.List;

import me.arti.utils.EntityUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;

import static me.arti.utils.EntityUtils.getRotationsNeeded;

public class GetTargt {

    static EntityLivingBase target;

    public static EntityLivingBase getTargetNew() {

        //lists
        ArrayList<EntityLivingBase> closeEntities = new ArrayList<EntityLivingBase>();
        List<Integer> distances = new ArrayList<Integer>();

        //int for sorting
        int c = 9999;

        //gets all near entities
        closeEntities = EntityUtils.getCloseEntities(false, 100);


        //counts through entities and adds their distance to list
        for (int i = 0; i < closeEntities.size(); i++) {
            int tDistance = EntityUtils.getDistanceFromMouse(closeEntities.get(i));
            distances.add(tDistance);
            //System.out.println(closeEntities.get(i));
        }

//        //debug system for near entities
//        for(int i = 0; i < closeEntities.size(); i++){
//            System.out.println(closeEntities.get(i));
//        }
//
//        //debug system for distances
//        for(int i = 0; i < distances.size(); i++){
//            System.out.println(distances.get(i));
//        }

        //sorts list in descending order
        for (int i = 0; i < distances.size(); i++) {
            int tLimit = 9999;
            if (c == 9999 || distances.get(i) < distances.get(c)) {
                c = i;
            }
        }


//        //debug output closest entity to mouse and entity index
//        System.out.println(c);
//        System.out.println(closeEntities.get(c));

        //checks its not too far from the mouse
        if (c != 9999 && distances.get(c) > 3) {
            c = 9999;
            return null;
        }

        //filters it
        if(c == 9999){
            return null;
        }

        target = closeEntities.get(c);
        return closeEntities.get(c);
    }

    public static boolean CheckSnap() {
        //checks the player wont have to rotate too far. 15 is the max degrees

        if(getRotationsNeeded(target)[1] > 50){
            //Debug writes the rotations needed
            System.out.println(getRotationsNeeded(target)[1]);
            return false;
        }
        else{
            return true;
        }
    }
}
