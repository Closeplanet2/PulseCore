package com.pandapulsestudios.pulsecore.EntityAPI.API;

import org.bukkit.*;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Display;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.TextDisplay;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;

public class ItemDisplayAPI {

    public static ItemDisplayBuilder ItemDisplayBuilder(){return new ItemDisplayBuilder();}
    public static class ItemDisplayBuilder{
        private World world = Bukkit.getWorld("world");
        private Location location = world.getSpawnLocation();
        private double itemScale = 1.0;
        private Vector3f leftRotation = new Vector3f(0f, 0f, 0f);
        private float viewRange = 0.1f;
        private float shadowRadius = 0.3f;
        private float shadowStrength = 5f;
        private float displayWidth = 50f;
        private float displayHeight = 50f;
        private Display.Billboard billboard = Display.Billboard.CENTER;
        private Color itemGlowColor = Color.RED;
        private Display.Brightness itemBrightness = new Display.Brightness(15, 15);


        /**
         * World ti display in!
         * @param world
         * @return
         */
        public ItemDisplayBuilder world(World world){
            this.world = world;
            return this;
        }

        /**
         * Location to spawn at
         * @param location
         * @return
         */
        public ItemDisplayBuilder location(Location location){
            this.location = location;
            return this;
        }

        /**
         * Item display scale
         * @param itemScale
         * @return
         */
        public ItemDisplayBuilder itemScale(double itemScale){
            this.itemScale = itemScale;
            return this;
        }

        /**
         * x: 1 to -1, forward/backward lay
         * y: 1 to -1, horizontal rotation
         * z: 1 to -1, right/left tilt
         * @param leftRotation
         * @return
         */
        public ItemDisplayBuilder leftRotation(Vector3f leftRotation){
            this.leftRotation = leftRotation;
            return this;
        }

        /**
         * 0.1 = 16 blocks
         * @param viewRange
         * @return
         */
        public ItemDisplayBuilder viewRange(float viewRange){
            this.viewRange = viewRange;
            return this;
        }

        /**
         *  1 = 1 block
         * @param shadowRadius
         * @return
         */
        public ItemDisplayBuilder shadowRadius(float shadowRadius){
            this.shadowRadius = shadowRadius;
            return this;
        }

        /**
         * >= 5F = "black hole"
         * @param shadowStrength
         * @return
         */
        public ItemDisplayBuilder shadowStrength(float shadowStrength){
            this.shadowStrength = shadowStrength;
            return this;
        }

        public ItemDisplayBuilder displayHeight(float displayHeight){
            this.displayHeight = displayHeight;
            return this;
        }

        public ItemDisplayBuilder displayWidth(float displayWidth){
            this.displayWidth = displayWidth;
            return this;
        }

        public ItemDisplayBuilder billboard(Display.Billboard billboard){
            this.billboard = billboard;
            return this;
        }

        public ItemDisplayBuilder itemGlowColor(Color itemGlowColor){
            this.itemGlowColor = itemGlowColor;
            return this;
        }

        /**
         * 0-15, will override auto brightness
         * @param itemBrightness
         * @return
         */
        public ItemDisplayBuilder itemBrightness(Display.Brightness itemBrightness){
            this.itemBrightness = itemBrightness;
            return this;
        }

        public ItemDisplay Spawn(ItemStack itemStack){
            var itemDisplay = world.spawn(location, ItemDisplay.class);
            itemDisplay.setItemStack(itemStack);

            var transformation = itemDisplay.getTransformation();
            transformation.getScale().set(itemScale);
            transformation.getLeftRotation().x = leftRotation.x;
            transformation.getLeftRotation().y = leftRotation.y;
            transformation.getLeftRotation().z = leftRotation.z;
            itemDisplay.setTransformation(transformation);

            itemDisplay.setViewRange(viewRange);
            itemDisplay.setShadowStrength(shadowRadius);
            itemDisplay.setShadowStrength(shadowStrength);
            itemDisplay.setDisplayHeight(displayHeight);
            itemDisplay.setDisplayWidth(displayWidth);
            itemDisplay.setBillboard(billboard);
            itemDisplay.setGlowColorOverride(itemGlowColor);
            itemDisplay.setBrightness(itemBrightness);

            return itemDisplay;
        }
    }
}
