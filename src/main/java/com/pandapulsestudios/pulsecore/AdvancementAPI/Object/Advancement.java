package com.pandapulsestudios.pulsecore.AdvancementAPI.Object;

import com.pandapulsestudios.pulsecore.AdvancementAPI.Enum.Style;
import com.pandapulsestudios.pulsecore.PulseCore;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Advancement {
    private NamespacedKey key = new NamespacedKey(PulseCore.PulseCore, UUID.randomUUID().toString());
    private String icon = "icon";
    private String message = "message";
    private Style style = Style.GOAL;

    public void GivePlayerAdvancement(Player player){
        CreateAdvancement();
        player.getAdvancementProgress(Bukkit.getAdvancement(key)).awardCriteria("trigger");
        Bukkit.getScheduler().runTaskLater(PulseCore.PulseCore, () -> {
            player.getAdvancementProgress(Bukkit.getAdvancement(key)).revokeCriteria("trigger");
        }, 10);
    }

    private void CreateAdvancement() {
        Bukkit.getUnsafe().loadAdvancement(key, "{\n" +
                "    \"criteria\": {\n" +
                "        \"trigger\": {\n" +
                "            \"trigger\": \"minecraft:impossible\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"display\": {\n" +
                "        \"icon\": {\n" +
                "            \"item\": \"minecraft:" + icon + "\"\n" +
                "        },\n" +
                "        \"title\": {\n" +
                "            \"text\": \"" + message.replace("|", "\n") + "\"\n" +
                "        },\n" +
                "        \"description\": {\n" +
                "            \"text\": \"\"\n" +
                "        },\n" +
                "        \"background\": \"minecraft:textures/gui/advancements/backgrounds/adventure.png\",\n" +
                "        \"frame\": \"" + style.toString().toLowerCase() + "\",\n" +
                "        \"announce_to_chat\": false,\n" +
                "        \"show_toast\": true,\n" +
                "        \"hidden\": true\n" +
                "    },\n" +
                "    \"requirements\": [\n" +
                "        [\n" +
                "            \"trigger\"\n" +
                "        ]\n" +
                "    ]\n" +
                "}");
    }


    public static AdvancementBuilder AdvancementBuilder(){return new AdvancementBuilder();}
    public static class AdvancementBuilder{
        private NamespacedKey key = new NamespacedKey(PulseCore.PulseCore, UUID.randomUUID().toString());
        private String icon = "icon";
        private String message = "message";
        private Style style = Style.GOAL;

        public AdvancementBuilder key(NamespacedKey key){
            this.key = key;
            return this;
        }

        public AdvancementBuilder icon(String icon){
            this.icon = icon;
            return this;
        }

        public AdvancementBuilder message(String message){
            this.message = message;
            return this;
        }

        public AdvancementBuilder style(Style style){
            this.style = style;
            return this;
        }

        public Advancement Build(){
            var advancement = new Advancement();
            advancement.key = key;
            advancement.icon = icon;
            advancement.message = message;
            advancement.style = style;
            PulseCore.Advancements.put(advancement.key, advancement);
            return advancement;
        }
    }
}
