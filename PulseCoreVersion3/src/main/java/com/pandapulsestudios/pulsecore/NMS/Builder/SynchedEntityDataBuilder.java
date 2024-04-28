package com.pandapulsestudios.pulsecore.NMS.Builder;

public class SynchedEntityDataBuilder {

    public static builder CreateBuilder(){return new builder();}
    public static class builder{
        private boolean isGlowing;

        public builder isGlowing(boolean state){
            isGlowing = state;
            return this;
        }

        public byte Entity0Index(){
            if(isGlowing) return 0x40;
            return 0;
        }
    }
}
