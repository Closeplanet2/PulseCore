package com.pandapulsestudios.pulsecore.EntityAPI.API;

import org.bukkit.*;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Display;
import org.joml.Vector3f;

public class BlockDisplayAPI {
    public static BlockDisplayBuilder BlockDisplayBuilder(){return new BlockDisplayBuilder();}
    public static class BlockDisplayBuilder{
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
        public BlockDisplayBuilder world(World world){
            this.world = world;
            return this;
        }

        /**
         * Location to spawn at
         * @param location
         * @return
         */
        public BlockDisplayBuilder location(Location location){
            this.location = location;
            return this;
        }

        /**
         * Item display scale
         * @param itemScale
         * @return
         */
        public BlockDisplayBuilder itemScale(double itemScale){
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
        public BlockDisplayBuilder leftRotation(Vector3f leftRotation){
            this.leftRotation = leftRotation;
            return this;
        }

        /**
         * 0.1 = 16 blocks
         * @param viewRange
         * @return
         */
        public BlockDisplayBuilder viewRange(float viewRange){
            this.viewRange = viewRange;
            return this;
        }

        /**
         *  1 = 1 block
         * @param shadowRadius
         * @return
         */
        public BlockDisplayBuilder shadowRadius(float shadowRadius){
            this.shadowRadius = shadowRadius;
            return this;
        }

        /**
         * >= 5F = "black hole"
         * @param shadowStrength
         * @return
         */
        public BlockDisplayBuilder shadowStrength(float shadowStrength){
            this.shadowStrength = shadowStrength;
            return this;
        }

        public BlockDisplayBuilder displayHeight(float displayHeight){
            this.displayHeight = displayHeight;
            return this;
        }

        public BlockDisplayBuilder displayWidth(float displayWidth){
            this.displayWidth = displayWidth;
            return this;
        }

        public BlockDisplayBuilder billboard(Display.Billboard billboard){
            this.billboard = billboard;
            return this;
        }

        public BlockDisplayBuilder itemGlowColor(Color itemGlowColor){
            this.itemGlowColor = itemGlowColor;
            return this;
        }

        /**
         * 0-15, will override auto brightness
         * @param itemBrightness
         * @return
         */
        public BlockDisplayBuilder itemBrightness(Display.Brightness itemBrightness){
            this.itemBrightness = itemBrightness;
            return this;
        }

        public BlockDisplay Spawn(Material material){
            var blockDisplay = world.spawn(location, BlockDisplay.class);
            blockDisplay.setBlock(Bukkit.createBlockData(material));

            var transformation = blockDisplay.getTransformation();
            transformation.getScale().set(itemScale);
            transformation.getLeftRotation().x = leftRotation.x;
            transformation.getLeftRotation().y = leftRotation.y;
            transformation.getLeftRotation().z = leftRotation.z;
            blockDisplay.setTransformation(transformation);

            blockDisplay.setViewRange(viewRange);
            blockDisplay.setShadowStrength(shadowRadius);
            blockDisplay.setShadowStrength(shadowStrength);
            blockDisplay.setDisplayHeight(displayHeight);
            blockDisplay.setDisplayWidth(displayWidth);
            blockDisplay.setBillboard(billboard);
            blockDisplay.setGlowColorOverride(itemGlowColor);
            blockDisplay.setBrightness(itemBrightness);

            return blockDisplay;
        }
    }
}
